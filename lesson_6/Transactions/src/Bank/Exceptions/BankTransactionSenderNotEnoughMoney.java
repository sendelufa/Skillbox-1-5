/**
 * Project Transactions
 * Created by Shibkov Konstantin on 03.02.2019.
 */
package Bank.Exceptions;

import Bank.TransferTask;

public class BankTransactionSenderNotEnoughMoney extends BankException {
    public BankTransactionSenderNotEnoughMoney(TransferTask tt){
        System.out.println("BankTransactionSenderNotEnoughMoney -> На лс отправителя (" + tt.getSender().toString() + ")  не хватает суммы для перевода:" + tt.getAmount());
    }

    public BankTransactionSenderNotEnoughMoney(){
        System.out.println("BankTransactionSenderNotEnoughMoney");

    }
}
