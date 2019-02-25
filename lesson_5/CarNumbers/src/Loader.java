import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project CarNumbers
 * Created by Shibkov Konstantin on 18.12.2018.
 */

public class Loader {
    public static void main(String[] args) throws IOException {
      /* TODO - Написать программу, которая будет выдавать имя владельца автомобиля по его номеру. Программа должна быть
            умной: если у неё в памяти номера нет, она должна попросить ввести его имя и запомнить.*/

        String input;
        Pattern pattern = Pattern.compile("^[АВЕКМНОРСТУХ][0-9]{2}[1-9][АВЕКМНОРСТУХ]{2}[0-9]{2,3}$");


        TreeMap<String, String> carNumbers = new TreeMap<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (; ; ) {
            System.out.println("\nВведите номер автомобиля в формате А111АА02:");
            input = reader.readLine().trim().toUpperCase();
            //Проверяем правильность формата номера авто
            if (checkInput(input, pattern)) {
                //Проверяем наличие номера в БД, если есть выводим ФИО соответсвующее номеру авто
                if (carNumbers.containsKey(input)) {
                    System.out.println(carNumbers.get(input));
                }
                //Если нет номера в базе - запрашиваем ФИО владельца и записываем в TreeMap
                else {
                    System.out.println("Введите ФИО владельца автомобиля с номером:" + input);
                    carNumbers.put(input, reader.readLine().trim());
                }
            }

        }
   /*
           Для теста выводим список всех номеров в TreeMap
           for (String item: carNumbers.keySet()){
                System.out.println(item + "  - " + carNumbers.get(item));
            }*/
    }

    private static boolean checkInput(String input, Pattern pattern) {
        Matcher match = pattern.matcher(input);
        if (match.find()) {
            return true;
        } else {
            System.out.println("номер введен неверно!");
            return false;
        }
    }
}
