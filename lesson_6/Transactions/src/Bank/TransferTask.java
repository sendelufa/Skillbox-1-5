/**
 * Project Transactions
 * Created by Shibkov Konstantin on 01.02.2019.
 */
package Bank;

//объект задачи в очереди отправки

public class TransferTask {
    private Account sender;
    private Account recipient;
    private Long amount;

    public TransferTask(Account sender, Account recipient, Long amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }

    public Account getSender() {
        return sender;
    }

    public Account getRecipient() {
        return recipient;
    }

    public Long getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        String str = "";
        str += sender.toString() + " -> ";
        str += recipient.toString() + " $=";
        str += amount;
        return str;
    }
}
