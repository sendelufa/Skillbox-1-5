import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Loader {
    public static void main(String args[])
    {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        BigDecimal i = new BigDecimal(24);
        BigDecimal j = new BigDecimal(0.1);
        BigDecimal a = i.divide(j);
        System.out.println(a);
    }
}
