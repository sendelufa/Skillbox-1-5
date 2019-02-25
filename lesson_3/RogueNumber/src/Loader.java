import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Project RogueNumber
 * Created by Shibkov Konstantin on 17.12.2018.
 */

public class Loader {
    static ArrayList<String> NumbersArrayList = new ArrayList<>();
    static ArrayList<String> NumbersSortedArrayList;
    static HashSet<String> NumbersHashSet = new HashSet<>();
    static TreeSet<String> NumbersTreeSet = new TreeSet<>();;

    static String txtYes = "true";
    static String txtNo = "false";

    public static void main(String[] args) throws IOException {
        //TODO - Сделать детектор блатных номеров для ГИБДД (все регионы): сгенерировать список номеров и сделать метод,
        // который будет проверять наличие номера в списке. Программа должна работать через консоль: запрашивать номер,
        // проверять, выдавать результат проверки и снова запрашивать номер. Результат должен выдаваться в таком
        // формате: false (26 ms).

        //Записываем время начала выполнения метода
        long timestampStart = System.nanoTime();

        //Генерируем блатные номера "xyNNNz", "xy00Nz", "xyN00z", "xyN0Nz"
        generateRogueNumbers();



        //NumbersTreeSet = new TreeSet<>(NumbersArrayList);
        //NumbersSortedArrayList = new ArrayList<>(NumbersArrayList);
        //Collections.sort(NumbersSortedArrayList);
//Записываем время окончания выполнения метода и выводим в консоль
        long timestampFinish = System.nanoTime() - timestampStart;
        System.out.print("Затраченное время: " + (double) timestampFinish / 1000000000 + " с.\n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Запускаем бесконечный цикл
        for (; ; ) {
            //Производим поиск номера
            searchNumber(reader);
        }
    }

    private static void generateRogueNumbers() {
        //массив регионов
        String[] region = {"01", "02", "102", "03", "04", "05", "06", "07", "08",
                "09", "10", "11", "12", "13", "113", "14", "15", "16", "116", "716", "17", "18",
                "19", "20", "95", "21", "121", "22", "23", "93", "123", "24", "84", "88", "124",
                "25", "125", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36",
                "136", "37", "38", "85", "39", "91", "40", "41", "82", "42", "142", "43", "44",
                "45", "46", "47", "48", "49", "50", "90", "150", "190", "51", "52", "152", "53",
                "54", "154", "55", "56", "57", "58", "59", "81", "159", "60", "61", "161", "62", "63",
                "163", "64", "164", "65", "66", "96", "67", "68", "69", "70", "71", "72", "73",
                "173", "74", "174", "75", "80", "76", "77", "97", "99", "177", "199", "197",
                "78", "98", "178", "79", "83", "86", "87", "89"};

        //String[] region = {"01"};
        //массив букв используемых в номерах
        String[] letters = {"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};
        System.out.println("Генерируем блатные номера формата \"xyNNNz\", \"xy00Nz\", \"xyN00z\", \"xyN0Nz\"");

        for (String reg : region) {
            for (int f = 0; f < 10; f++) {
                for (String letter1 : letters) {
                    for (String letter2 : letters) {
                        for (String letter3 : letters) {
                            String xyNNNz = letter1 + f + f + f + letter2 + letter3 + reg;
                            String xy00Nz = letter1 + "00" + f + letter2 + letter3 + reg;
                            String xyN00z = letter1 + f + "00" + letter2 + letter3 + reg;
                            String xyN0Nz = letter1 + f + "0" + f + letter2 + letter3 + reg;
                            if (!NumbersHashSet.contains(xyNNNz)){
                                NumbersArrayList.add(xyNNNz);
                                NumbersHashSet.add(xyNNNz);
                                NumbersTreeSet.add(xyNNNz);
                            }

                            if (!NumbersHashSet.contains(xy00Nz)){
                                NumbersArrayList.add(xy00Nz);
                                NumbersHashSet.add(xy00Nz);
                                NumbersTreeSet.add(xy00Nz);
                            }

                            if (!NumbersHashSet.contains(xyN00z)){
                                NumbersArrayList.add(xyN00z);
                                NumbersHashSet.add(xyN00z);
                                NumbersTreeSet.add(xyN00z);
                            }

                            if (!NumbersHashSet.contains(xyN0Nz)){
                                NumbersArrayList.add(xyN0Nz);
                                NumbersHashSet.add(xyN0Nz);
                                NumbersTreeSet.add(xyN0Nz);
                            }

                        }
                    }

                }
            }
        }

        System.out.println("Cгенерировано: " + NumbersArrayList.size() + " блатных номеров");
        System.out.println("Номера записаны в HashSet, TreeSet, ArrayList");
        System.out.println("NumbersArrayList=" + NumbersArrayList.size());
        System.out.println("NumbersTreeSet=" + NumbersTreeSet.size());
        System.out.println("NumbersHashSet=" + NumbersHashSet.size());

    }

    private static void searchNumber(BufferedReader reader) throws IOException {
        //Запрашиваем номер в консоли
        System.out.println("Введите номер для проверки вида А000АА102:");
        //Получаем строку, обрезаем пробелы и переводим в вверхний регистр, для более мягких требований к вводу
        String txt = reader.readLine().trim().toUpperCase();

        searchNumbersArrayList(txt);
        searchNumbersHashSet(txt);
        //searchNumbersSortedArrayList(txt);
        searchNumbersTreeSet(txt);
    }

    protected static void searchNumbersArrayList(String txt) {
        //Записываем время начала выполнения метода
        long timestampStart = System.nanoTime();
        //метка вхождения номера в список блатных
        boolean isFind;
        //цикл перебора всех блатных номер и сравнения с введеным
        for (int i = 0; i < NumbersArrayList.size(); i++) {
            //Если номер найден меняем метку isFind на true
            isFind = txt.equals(NumbersArrayList.get(i)) == true ? true : false;
            if (isFind == true) {
                //Записываем время окончания выполнения метода и выводим в консоль
                System.out.println(txtYes + " ArrayList (" + (int) (System.nanoTime() - timestampStart) / 1000000 + " ms)");
                return;
            }
        }
        //Если цикл пройден и не вышли через return, значит номер не найден
        // Записываем время окончания выполнения метода и выводим в консоль
        System.out.println(txtNo + " ArrayList (" + (int) (System.nanoTime() - timestampStart) / 1000000 + " ms)");
    }

    protected static void searchNumbersSortedArrayList(String txt) {
        //SortedArrayList
        long timestampStart = System.nanoTime();
        boolean isFind;
        isFind = Collections.binarySearch(NumbersSortedArrayList, txt) > 0 ? true : false;
        if (isFind == true) {
            System.out.println(txtYes + " SortedArrayList (" + (int) (System.nanoTime() - timestampStart) / 1000000 + " ms)");
            return;
        } else {
            System.out.println(txtNo + " SortedArrayList (" + (int) (System.nanoTime() - timestampStart) / 1000000 + " ms)");
        }


    }

    protected static void searchNumbersHashSet(String txt) {
        //HashSet
        long timestampStart = System.nanoTime();
        boolean isFind;
        for (String item : NumbersHashSet) {
            isFind = txt.equals(item) == true ? true : false;
            if (isFind == true) {
                System.out.println(txtYes + " HashSet (" + (int) (System.nanoTime() - timestampStart) / 1000000 + " ms)");
                return;
            }
        }
        System.out.println(txtNo + " HashSet (" + (int) (System.nanoTime() - timestampStart) / 1000000 + " ms)");
    }

    protected static void searchNumbersTreeSet(String txt) {
        //SortedArrayList
        long timestampStart = System.nanoTime();
        boolean isFind;
        isFind = NumbersTreeSet.contains(txt) ? true : false;
        if (isFind == true) {
            System.out.println(txtYes + " TreeSet (" + (int) (System.nanoTime() - timestampStart) / 1000000 + " ms)");
            return;
        } else {
            System.out.println(txtNo + " TreeSet (" + (int) (System.nanoTime() - timestampStart) / 1000000 + " ms)");
        }


    }

}
