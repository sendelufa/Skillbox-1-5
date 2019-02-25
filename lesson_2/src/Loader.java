import com.sun.istack.internal.NotNull;

import java.util.Calendar;

/**
 * Project Rainbow
 * Created by Shibkov Konstantin on 16.12.2018.
 */

public class Loader {
    public static void main(String[] args) {

        //TODO Повторить создание массива и заполнение цветами радуги, как показано в видео, а затем написать код,
        // переворачивающий этот массив.
        rainbow();

        //TODO Создать и распечатать массив серий паспортов гражданина РФ.
        //регионы в серии
        String[] region = {"79", "84", "80"};
        //Генерация массива паспортов, аргумент - количество паспортов
        getPassport(region, 1997);

        //TODO Создать массив массивов клеток на шахматной доске и распечатать так, чтобы в консоли названия клеток
        // были напечатаны в форме шахматной доски.
        printChessBoard();

    }

    //Задание Радуга
    protected static void rainbow() {


        String[] rainbow = {"Red", "Orange", "Yellow", "Green", "Cyan", "Blue", "Violet"};

        System.out.println("\"Перевернутая\" радуга, с помощью обратного цикла:");
        for (int i = rainbow.length - 1; i >= 0; i--) {
            System.out.println(rainbow[i]);
        }

        //переворачиваем массив вверх ногами с помощью временной переменной
        for (int i = 0; i < (rainbow.length / 2); i++) {
            String temp = rainbow[i];
            rainbow[i] = rainbow[rainbow.length - i - 1];
            rainbow[rainbow.length - i - 1] = temp;

        }
        System.out.println("\n\"Перевернутая\" радуга, с помощью пересторения массива в обратном порядке:");
        for (int i = 0; i < rainbow.length; i++) {
            System.out.println(rainbow[i]);
        }
    }

    //Задание Генерация серий паспортов
    protected static void getPassport(String[] regions, int startYear) {

        Calendar todayDate = Calendar.getInstance();

        //конечный год
        int finishYear = todayDate.get(Calendar.YEAR);

        //массив серий паспортов, размер расчитывается по кол-ву регионов и годов
        String[] pass = new String[regions.length * (finishYear - startYear)];
        System.out.println("\nСерии Паспортов РФ:");
        for (int i = 0; i < regions.length; i++) {
            for (int j = startYear; j <= finishYear; j++) {
                pass[i] = regions[i] + String.valueOf(j).substring(2);
                System.out.println(pass[i]);
            }


        }
    }

    //Задание Печать шахматной доски
    protected static void printChessBoard() {
        String[][] ChessBoard = {
                {"A", "B", "C", "D", "E", "F", "G", "H"},
                {"1", "2", "3", "4", "5", "6", "7", "8"}};

        System.out.println("\nВывод названий клеток шахматной доски:");
        for (int j = 0; j < ChessBoard[1].length; j++) {
            for (int i = ChessBoard[0].length - 1; i >= 0; i--) {
                System.out.print(ChessBoard[0][ChessBoard[0].length - i - 1] + ChessBoard[1][ChessBoard[1].length - j - 1] + " ");
            }
            System.out.print("\n");
        }


    }

}


