import Bank.*;

/**
 * Project Transactions
 * Created by Shibkov Konstantin on 23.01.2019.
 */

public class Loader {
    public static void main(String[] args){


        //количество аккаунтов в банке
        int AccountsNumber = 100;
        int TransactionNumber = 2000;

        Bank bank = new Bank();
        //создаем лицевые счета с суммами денег в банке
        bank.generateAccounts(AccountsNumber);
        //создается список транзакций для передачи
        bank.generateTransferStreamConcurrent(TransactionNumber);
        //запуск обработки транзакций
        bank.transfer();


    }
}
