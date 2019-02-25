import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project Airport
 * Created by Shibkov Konstantin on 18.12.2018.
 */

 /* TODO Реализовать эмулятор стоянки самолётов  стэк. Размер стоянки  5 мест. Работать должно быть следующим образом:
     вводим в консоль бортовые номера самолётов, и программа их запоминает (“ставит” их на стоянку). При вводе команды
     “exitAll” программа должна распечатать номера самолётов в порядке покидания стоянки (и удалить их все из памяти).
     При вводе “exitLast”  должна распечатать и удалить из памяти только тот самолёт, который сейчас выезжает.
     Если стоянка заполнена полностью, следующему самолёту должно быть отказано во въезде.
 */

public class Loader {
    public static void main(String[] args) throws IOException {

        //создаем стэк
        Stack<String> runway = new Stack<>();
        //команды для управление полосой
        final String COMMexitAll = "exitAll";
        final String COMMexitLast = "exitLast";
        final String PREFIX = "PA-";


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //паттерн бортового номера, формат 00001-99999 + проверка ввода команды
        Pattern pattern = Pattern.compile("^\\d{4}[1-9]$|^(" + COMMexitAll + ")$|^(" + COMMexitLast + ")$");

        for (; ; ) {
            System.out.println("Введите бортовой номер самолета РА-[XXXXX] (только цифры)/ exitAll - все самолеты покидают стоянку / exitLast - отправка последнего в полет:");
            String input = reader.readLine().trim();

            Matcher match = pattern.matcher(input);

            int inStack;

            //проверяем ввод номера, требуем нового ввода если не соотв паттерну
            if (match.find()) {
                inStack = runway.search(PREFIX + input);
            } else {
                System.out.println("  ===  неверный формат бортового номера или команды!");
                continue;
            }
            // обработка команды exitAll
            if (input.equals(COMMexitAll)) {
                if (runway.empty()) {
                    System.out.println("ПРЕДУПРЕЖДЕНИЕ! Нет самолетов на взлетной полосе!");
                    continue;
                }
                System.out.println("ВНИМАНИЕ! Освобождается взлетная полоса в следующем порядке:");
                int i = 1;
                while (!runway.empty()) System.out.println(i + ". " + runway.pop());
                continue;
            }
            // обработка команды exitLast
            else if (input.equals(COMMexitLast)) {
                if (runway.empty()) {
                    System.out.println("ПРЕДУПРЕЖДЕНИЕ! Нет самолетов на взлетной полосе!");
                    continue;
                } else {
                    System.out.println(runway.pop());
                }
            }

            //если inStack > 0 значит такой номер в стэке существует
            else if (inStack > 0) {
                System.out.println("Такой бортовой номер уже на полосе!");
                continue;
            } else if (inStack == -1 && runway.size() < 5) {
                runway.push(PREFIX + input);
                System.out.println("Бортовой номер " + PREFIX + input + " поставлен в очередь!");
            } else if (runway.size() > 4) {
                System.out.println("Все 5 мест заполнены! Мест нет!");
            }


        }
    }
}
