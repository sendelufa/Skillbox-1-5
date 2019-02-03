/**
 * Project Transactions
 * Created by Shibkov Konstantin on 03.02.2019.
 */
package Bank.Tests;

import Bank.Bank;
import org.junit.*;


public class BankTest {
    Bank bank;

    @Before
    public void init() {
        bank = new Bank();
        bank.generateAccounts(10);
    }

    @Test
    public void generateAccountsSizeTest() {
        Assert.assertEquals(bank.getAccounts().size(), 10);
    }

    @Test
    public void generateAccountsNumberTest() {
        boolean isAllEquals = true;
        for (String key : bank.getAccounts().keySet()) {
            if (!key.equals(bank.getAccounts().get(key).getAccNumber())) {
                isAllEquals = false;
            }
        }
        Assert.assertTrue(isAllEquals);
    }

    @Test
    public void isMoneyUpZeroTest(){
        boolean isMoneyUpZero = true;
        for (String key : bank.getAccounts().keySet()) {
            if (bank.getAccounts().get(key).getMoney() < 0) {
                isMoneyUpZero = false;
            }
        }
        Assert.assertTrue(isMoneyUpZero);
    }

}
