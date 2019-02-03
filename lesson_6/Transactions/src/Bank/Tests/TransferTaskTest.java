/**
 * Project Transactions
 * Created by Shibkov Konstantin on 04.02.2019.
 */
package Bank.Tests;

import Bank.Account;
import Bank.TransferTask;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TransferTaskTest {
    private Account sender;
    private Account recipient;
    private Long amount;
    private TransferTask tt;

    @Parameterized.Parameters
    public static Collection values() {
        return Arrays.asList(new Object[][]{
                {new Account(50000L, "2970725011242582564"), new Account(100000L, "2970725011242582562"), 100000L},
                {new Account(5000L, "5670725011242582564"), new Account(1L, "2970725011242582599"), 100000L}});
    }

    public TransferTaskTest(Account sender, Account recipient, Long amount) {
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
    }

    @Before
    public void init() {
        tt = new TransferTask(sender, recipient, amount);

    }

    @Test
    public void TransferTaskTest(){
        boolean correctInit = true;
        if (!tt.getSender().equals(sender)) {correctInit = false;}
        if (!tt.getRecipient().equals(recipient)) {correctInit = false;}
        if (!tt.getAmount().equals(amount)) {correctInit = false;}
        Assert.assertTrue(correctInit);
    }

    @Test
    public void getAmountTest(){
        Assert.assertEquals(tt.getAmount(), amount);
    }

    @Test
    public void getRecipientTest(){
        Assert.assertEquals(tt.getRecipient(), recipient);
    }

    @Test
    public void getSenderTest(){
        Assert.assertEquals(tt.getSender(), sender);
    }

    @Test
    public void toStringTest() {
        String str = "";
        str += sender.toString() + " -> ";
        str += recipient.toString() + " $=";
        str += amount;
        Assert.assertEquals(tt.toString(),str);
    }
}
