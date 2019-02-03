/**
 * Project Transactions
 * Created by Shibkov Konstantin on 03.02.2019.
 */
package Bank.Exceptions;

public class BankTransactionAmountBelowMinimum extends BankException {
    public BankTransactionAmountBelowMinimum(){
        System.out.println("сумма для перевода ниже минимальной допустимой");
    }
}
