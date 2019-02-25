import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Project ParseName
 * Created by Shibkov Konstantin on 15.12.2018.
 */

public class Loader {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader((System.in)));
        System.out.println("Введите ФИО через пробелы:");
        String txt = reader.readLine().trim();

        char separator = ' '; //разделитель строки

        //Фамимлия - фрагмент от начала строки до первого пробела
        String Surname = txt.substring(0, txt.indexOf(separator)).trim();

        //Имя - фрагмент от первого пробела до последнего пробела строки
        String Name = txt.substring(txt.indexOf(separator), txt.lastIndexOf(separator)).trim();

        //Отчество - фрагмент от последнего пробела до конца строки
        String Patronymic = txt.substring(txt.lastIndexOf(separator)).trim();

        //вывод в консоль
        System.out.println("Фамилия: " + Surname);
        System.out.println("Имя: " + Name);
        System.out.println("Отчество: " + Patronymic);
    }
}
