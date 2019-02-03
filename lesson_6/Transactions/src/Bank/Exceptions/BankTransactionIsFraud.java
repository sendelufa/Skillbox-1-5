/**
 * Project Transactions
 * Created by Shibkov Konstantin on 03.02.2019.
 */
package Bank.Exceptions;

public class BankTransactionIsFraud extends BankException {
    public BankTransactionIsFraud(){
        System.out.println("BankTransactionIsFraud -> Операция отклонена Службой безопасности - ЛС Отправителя и Получателя заблокированы");
    }
}
