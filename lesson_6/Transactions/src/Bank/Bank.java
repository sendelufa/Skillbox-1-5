package Bank;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Thread.sleep;

import Bank.Exceptions.*;
import com.sun.istack.internal.NotNull;

/**
 * Created by Danya on 18.02.2016.
 */
public class Bank {
    private final Random random = new Random();
    private final int MAX_ACCOUNT_MONEY = 1_000_000_00;
    //для достижения 5% транзакция для отправки на проверку (сумма с копейками)
    private final int MAX_MONEY_TRANSFER = 53_000_00;
    private final int MAX_MONEY_WITHOUT_FRAUDTEST = 50_000_00;
    private HashMap<String, Account> accounts = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private ArrayDeque<TransferTask> tasksTransactions = new ArrayDeque<TransferTask>();

    //организация пула потоков для генерации списка транзакци в многопоточном режиме
    private ExecutorService exGenerateTransactions = Executors.newFixedThreadPool(16);
    private List<Future<?>> tasksGenerateTransactions = new ArrayList<>();

    //организация пула потоков для перевода денег между счетами
    private ExecutorService exProcessTransactions = Executors.newFixedThreadPool(16);
    private List<Future<?>> tasksProcessTransactions = new ArrayList<>();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();

    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    public void transfer() {
        //System.out.println(tasksTransactions.size() + " --<");
        //создаем задачу для формирования списка задач для потоков
        int queueSize = tasksTransactions.size();
        for (int i = 0; i < queueSize; i++) {
            tasksProcessTransactions.add(exProcessTransactions.submit(this::processTransaction));

        }

        //проверка на выполнение всех задач в списке и продолжение выполнение кода
        boolean allTasksDone = false;
        while (!allTasksDone) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            allTasksDone = true;
            for (Future ft : tasksGenerateTransactions) {
                if (!ft.isDone()) allTasksDone = false;
            }

        }

        exProcessTransactions.shutdown();
    }

    //генерация списка транзакций в однопоточном режиме
    private void generateTransferStream(int count) {
        String[] listAccounts;

        listAccounts = accounts.keySet().toArray(new String[0]);

        for (int i = 0; i < count; i++) {
            int senderIndex, recipientIndex;
            long amount;
            lock.readLock().lock();
            try {
                senderIndex = random.nextInt(listAccounts.length);
                recipientIndex = random.nextInt(listAccounts.length);
                amount = random.nextInt(MAX_MONEY_TRANSFER);

                while (senderIndex == recipientIndex) {
                    recipientIndex = random.nextInt(listAccounts.length);
                }
            } finally {
                lock.readLock().unlock();
            }

            lock.writeLock().lock();
            try {
                tasksTransactions.addLast(new TransferTask(accounts.get(listAccounts[senderIndex]),
                        accounts.get(listAccounts[recipientIndex]), amount));
            } finally {
                lock.writeLock().unlock();
            }


        }


    }

    //генерация списка транзакций в многопоточном режиме
    public void generateTransferStreamConcurrent(int count) {
        //создаем задачу для формирования списка транзакций
        for (int i = 0; i < 10; i++) {
            tasksGenerateTransactions.add(exGenerateTransactions.submit(() -> generateTransferStream(count / 10)));

        }

        //проверка на выполнение всех задач в списке и продолжение выполнение кода
        boolean allTasksDone = false;
        while (!allTasksDone) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            allTasksDone = true;
            for (Future ft : tasksGenerateTransactions) {
                if (!ft.isDone()) allTasksDone = false;
            }
            System.out.println("all tasksGenerateTransactions \"generateTransferStreamConcurrent\" not done - sleep;");

        }
        exGenerateTransactions.shutdown();
    }

    //обработка одной транзакции
    private void processTransaction() {
        TransferTask tt;


        lock.readLock().lock();
        try {
            tt = tasksTransactions.pollFirst();
        } finally {

            lock.readLock().unlock();
        }
        //проверки входных данных транзакции
        //если вылетит исключение - заврешаем обработку транзакции
        lock.writeLock().lock();
        try {
            try {
                validateTransaction(tt);
            } catch (BankException be) {
                return;
            }

            //проверяем транзакцию у службы безопасности
            boolean isFraud = false;

            try {
                if (tt.getAmount() > MAX_MONEY_WITHOUT_FRAUDTEST) {
                    isFraud = isFraud(tt.getSender().getAccNumber(), tt.getRecipient().getAccNumber(), tt.getAmount());
                }

            } catch (InterruptedException ie) {
                System.out.println("Операция проверки безопасности прервана!");
                return;
            }

            //проверка безопасности сделки
            if (isFraud) {
                tt.getSender().lock();
                tt.getRecipient().lock();
                throw new BankTransactionIsFraud();
            }

            //процесс перевода денег
            //забираем деньги у отправителя

            tt.getSender().addMoney(-tt.getAmount());
            tt.getRecipient().addMoney(tt.getAmount());
        } catch (BankTransactionIsFraud bankTransactionIsFraud) {
            return;
        } finally {
            lock.writeLock().unlock();
        }

        System.out.println("\t\tOK=" + Thread.currentThread().getName() + " " + tt.toString());


    }

    //проверки входных данных транзакции
    private void validateTransaction(@NotNull TransferTask tt) throws BankException {
        //из очереди выдали объект null, очередь закончилась
        if (tt == null) {
            throw new BankTransactionNull();

        }//Заблокирован ли лс Отправителя или Получателя?
        if (tt.getSender().isLocked() || tt.getRecipient().isLocked()) {
            throw new BankTransactionAccountIsLocked();
        }

        //хватает ли денег у Отправителя?
        if (tt.getSender().getMoney() < tt.getAmount()) {
            throw new BankTransactionSenderNotEnoughMoney(tt);
        }
        //количество денег на отправку больше минимальной допустимой суммы?
        if (tt.getAmount() < 1) {
            throw new BankTransactionAmountBelowMinimum();
        }

    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        long money;
        lock.readLock().lock();
        try {
            money = accounts.get(accountNum).getMoney();
        } finally {
            lock.readLock().unlock();
        }
        return money;
    }


    public void generateAccounts(int counts) {
        Random random = new Random(1L);
        for (int i = 0; i < counts; i++) {
            String accNumber = String.valueOf(Math.abs(random.nextLong()));
            accounts.put(accNumber, new Account(random.nextInt(MAX_ACCOUNT_MONEY), accNumber));
        }

    }
}
