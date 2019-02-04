
package Bank.Tests;

import Bank.Account;
import Bank.Exceptions.BankTransactionAddMoneyOverflow;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Project Transactions
 * Created by Shibkov Konstantin on 03.02.2019.
 */

@RunWith(Parameterized.class)
public class AccountTest {
    private long money;
    private String accNumber;
    private boolean locked, expLocked;
    private Account acc;

    public AccountTest(long money, String accNumber, boolean locked, boolean expLocked) {
        this.money = money;
        this.accNumber = accNumber;
        this.locked = locked;
        this.expLocked = expLocked;
    }

    @Parameterized.Parameters
    public static Collection values() {
        return Arrays.asList(new Object[][]{
                {1000L, "2970725011242582564", false, true},
                {2375780L, "6288771570310971427", true, true}});
    }

    @Before
    public void init() {
        acc = new Account(money, accNumber);

    }

    @Test
    public void AccountTestCreate() {
        boolean correctInit = true;
        if (acc.isLocked()) {correctInit = false;}
        if (acc.getMoney() != money) {correctInit = false;}
        if (!acc.getAccNumber().equals(accNumber)) {correctInit = false;}
        Assert.assertTrue(correctInit);
    }

    @Test
    public void toStringTest() {
        String expStr = "accNumber[false]=" + accNumber + " (" + money + ")";
        Assert.assertEquals(acc.toString(), expStr);
    }

    @Test
    public void lockTest() {
        acc.lock();
        Assert.assertTrue(acc.isLocked());
    }

    @Test
    public void getMoneyTest() {
        Assert.assertEquals(acc.getMoney(), money);
    }

    @Test
    public void isLockedTest() {
        Assert.assertFalse(acc.isLocked());
    }

    @Test
    public void getAccNumberTest() {
        Assert.assertEquals(acc.getAccNumber(), accNumber);
    }

    @Test
    public void addMoneyAddTest() throws BankTransactionAddMoneyOverflow{
        acc.addMoney(5000);
        Assert.assertEquals(acc.getMoney(), money+5000);
    }

    @Test
    public void addMoneySubTest() throws BankTransactionAddMoneyOverflow {
        acc.addMoney(-1000);
        Assert.assertEquals(acc.getMoney(), money-1000);
    }

    @Test(expected = BankTransactionAddMoneyOverflow.class)
    public void addMoneyUpTest()  throws BankTransactionAddMoneyOverflow{
        acc.addMoney(9223372036854775807L);
    }

    @Test(expected = BankTransactionAddMoneyOverflow.class)
    public void addMoneyBelowZeroTest()  throws BankTransactionAddMoneyOverflow{
        acc.addMoney(-2375781L);
    }
}
