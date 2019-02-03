import Bank.*;

import static java.lang.Thread.sleep;

/**
 * Project Transactions
 * Created by Shibkov Konstantin on 23.01.2019.
 */

public class Loader {
    public static void main(String[] args) {


        //количество аккаунтов в банке
        int AccountsNumber = 10;

        Bank bank = new Bank();
        //создаем лицевые счета с суммами денег в банке
        bank.generateAccounts(AccountsNumber);
        //создается список транзакций для передачи
        bank.generateTransferStreamConcurrent(100);
        //пошла обработка транзакций
        bank.processTransactions();
        for (TransferTask tt : bank.getTasksTransactions()) {
            System.out.println(tt.toString());
        }


        //executor.shutdown();

    }
}
