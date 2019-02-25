/**
 * Project Loop
 * Created by Shibkov Konstantin on 10.12.2018.
 */

public class Loader {
    public static void main(String[] args) {
        Integer numTicket = 200000;
        do  {
            System.out.println("Ticket #" + numTicket);
            numTicket++;
        } while (numTicket <= 210000);
    }
}
