import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project Drugs
 * Created by Shibkov Konstantin on 18.12.2018.
 */

 /* TODO Написать программу, в которую можно добавлять через консоль и хранить перечень лекарств,
         а также распечатывать весь их список командой LIST.
 */

public class Loader {
    public static void main(String[] args) throws IOException {

        //создаем TreeSet
        TreeSet<String> drugsSet = new TreeSet<>();
        //команды для управление TreeSet
        final String COMM_LIST = "LIST";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (; ; ) {
            System.out.println("\nВведите название лекарства или команду LIST для вывода списка:");
            String input = reader.readLine().trim();

            if (input.equals(COMM_LIST)) {
                System.out.println("\nСписок лекарств:");
                for (String item : drugsSet)
                {
                    System.out.println(item);
                }
                continue;
            }
            else
            {
                drugsSet.add(input);
                System.out.print(" ^ - отправлен в список");
            }


        }
    }
}
