package Bank.Tests;

import Bank.*;
import Bank.Exceptions.*;
import org.junit.*;

/**
 * Project Transactions
 * Created by Shibkov Konstantin on 03.02.2019.
 */

public class BankTest {
    private Bank bank;

    @Before
    public void init() throws BankException {
        bank = new Bank();
        bank.generateAccounts(10);
        bank.generateTransferStreamConcurrent(10);
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

    @Test(expected = BankAccountNotExist.class)
    public void getBalanceAccountNotExistTest() throws BankException {
        bank.getBalance("44");
    }

    @Test(expected = BankTransferCountBelowOne.class)
    public void generateTransferStreamConcurrentBelowZeroTest() throws BankException {
        bank.generateTransferStreamConcurrent(0);
    }

    @Test(expected = BankTransactionAccountIsLocked.class)
    public void generateTransferAccountLockedTest() throws BankException {
        for (String key : bank.getAccounts().keySet()){
            Account acc = bank.getAccounts().get(key);
            acc.lock();
            bank.getAccounts().put(key, acc);
        }
        bank.processTransaction();
    }

    @Test(expected = BankTransactionSenderNotEnoughMoney.class)
    public void generateTransferSenderRecipientAmountBelowZero() throws BankException {
        for (String key : bank.getAccounts().keySet()){
            Account acc = bank.getAccounts().get(key);
            acc.addMoney(-acc.getMoney());
            bank.getAccounts().put(key, acc);
        }
        bank.processTransaction();
    }





}
