package Bank;

import java.util.HashMap;

/**
 * Created by Danya on 18.02.2016.
 */
public class Account {
    private long money;
    private String accNumber;

    Account(long money, String accNumber) {
        this.accNumber = accNumber;
        this.money = money;
    }

    @Override
    public String toString() {
        return "accNumber=" + accNumber + " (" + money + ")";
    }

    public long getMoney() {
        return money;
    }
}
