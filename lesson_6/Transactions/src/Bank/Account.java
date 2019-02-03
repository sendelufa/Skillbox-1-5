package Bank;


import Bank.Exceptions.*;

/**
 * Created by Danya on 18.02.2016.
 */
public class Account {
    private long money;
    private String accNumber;
    private boolean locked;

    public Account(long money, String accNumber) {
        this.accNumber = accNumber;
        this.money = money;
        locked = false;
    }

    @Override
    public String toString() {
        return "accNumber[" + locked + "]=" + accNumber + " (" + money + ")";
    }

    //блокировка аккаунта
    public void lock(){
        locked = true;
    }

    public long getMoney() {
        return money;
    }

    public boolean isLocked() {
        return locked;
    }

    public void addMoney(long money) throws BankTransactionAddMoneyOverflow {
        if (this.money+money < 0) {
                throw new BankTransactionAddMoneyOverflow();
        }
        this.money += money;
    }

    public String getAccNumber() {
        return accNumber;
    }
}
