package Bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.lang.Thread.sleep;

/**
 * Created by Danya on 18.02.2016.
 */
public class Bank {
    private final Random random = new Random();
    private final int MAX_ACCOUNT_MONEY = 10_000_000;
    //для достижения 5% транзакция для отправки на проверку (сумма с копейками)
    private final int MAX_MONEY_TRANSFER = 53_000_00;
    private HashMap<String, Account> accounts = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private ArrayList<TransferTask> tasksTransactions = new ArrayList<>();

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
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {

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
                tasksTransactions.add(new TransferTask(accounts.get(listAccounts[senderIndex]),
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
            tasksGenerateTransactions.add(exGenerateTransactions.submit(() -> {
                generateTransferStream(count / 10);
            }));

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
    }

    //запуск транзакций
    public void processTransactions() {

        for (TransferTask tt: tasksTransactions){
            
        }

    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
         return accounts.get(accountNum).getMoney();
    }


    public void generateAccounts(int counts) {
        Random random = new Random(1L);
        for (int i = 0; i < counts; i++) {
            String accNumber = String.valueOf(Math.abs(random.nextLong()));
            accounts.put(accNumber, new Account(random.nextInt(MAX_ACCOUNT_MONEY), accNumber));
        }

    }

    public ArrayList<TransferTask> getTasksTransactions() {
        return tasksTransactions;
    }
}
