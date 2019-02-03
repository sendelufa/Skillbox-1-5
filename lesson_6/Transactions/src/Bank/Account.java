package Bank;

import java.util.HashMap;

/**
 * Created by Danya on 18.02.2016.
 */
public class Account {
    private long money;
    private String accNumber;
    private boolean locked;

    Account(long money, String accNumber) {
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

    public void addMoney(long money) {
        this.money += money;
    }

    public String getAccNumber() {
        return accNumber;
    }
}
