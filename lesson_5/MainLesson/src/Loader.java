/**
 * Project lesson_5
 * Created by Shibkov Konstantin on 15.12.2018.
 */

public class Loader {
    public static void main(String[] args) {
        char ch;
        for (int i = 0; i <= 512; i++) {
            ch = (char) i;
            System.out.println(i + " = " + ch);
        }


       /* В таблицу UTF-8 в диапазоне 0-512 нет символов русского алфавита
        буквы русского алфавита а-Я имеют коды от 1040 до 1103 включительно, буквы ё - 1105, Ё - 1025 */

    }
}
