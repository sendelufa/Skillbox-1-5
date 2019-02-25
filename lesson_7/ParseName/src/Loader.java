import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Project ParseName
 * Created by Shibkov Konstantin on 15.12.2018.
 */

public class Loader {
    public static void main(String[] args) throws IOException {

        /*Исправьте программу, написанную в уроке 4 таким образом, чтобы вводимая строка с фамилией, именем и
         отчеством разбивались с использованием регулярного выражения. Приложить zipархив проекта.
          */

        BufferedReader reader = new BufferedReader(new InputStreamReader((System.in)));
        String txt;
        //разделитель строки
        String separator = " ";

        //регулярное выражение для проверки фамилии
        String pattern = "^[A-ЯЁ]{1}[а-яё]+ [A-ЯЁ]{1}[а-яё]+ [A-ЯЁ]{1}[а-яё]+$";


        //требуем вводить ФИО, до того момента пока не будет введена корректная строка
        do {
            System.out.println("Введите ФИО через пробелы:");
            txt = reader.readLine().trim();

        } while (!txt.matches(pattern));



        //Разбиваем строку на фрагменты использую разделитель и метод split
        String person[] = txt.split(separator);

        //вывод в консоль
        System.out.println("Фамилия: " + person[0]);
        System.out.println("Имя: " + person[1]);
        System.out.println("Отчество: " + person[2]);


    }
}
