/**
 * Project grantVisa
 * Created by Shibkov Konstantin on 10.12.2018.
 */

public class Visa {
    public static void main(String[] args) {
     Integer moneyAmount = 60000;
     Boolean hasPreviousVisa = false;
     Boolean hasConviction = false;

     if ((moneyAmount > 45000 || hasPreviousVisa) && !hasConviction) {
         System.out.println("Visa granted;");
     }
     else {
         System.out.println("Visa not granted;");
     }
    }
}
