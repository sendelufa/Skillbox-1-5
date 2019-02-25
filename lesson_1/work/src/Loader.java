import java.io.*;

/**
 * Project work
 * Created by Shibkov Konstantin on 03.01.2019.
 */

public class Loader {
    static String path;

    public static void main(String[] args) throws IOException {
        System.out.println("== Программа сканировании директории ==");
        //Получаем путь к директории
        path = getPathFromConsole();
        //Обходим все директории с начальным отступом 0 (количество символов "-" впереди строки)
        printListOfFiles(new File(path), 0);
    }

    //получаем из консоли путь к директории
    private static String getPathFromConsole() throws IOException {
        for (; ; ) {
            System.out.println("Введите путь к директории:");
            BufferedReader dirPath = new BufferedReader(new InputStreamReader((System.in)));
            String read = dirPath.readLine().trim();

            if (new File(read).isDirectory()) {
                return read;
            } else {
                System.out.println(" -- Неверный путь к папке");
            }

        }

    }

    //основная функция - обходит директорию и выводит список файлов,
    //как только обнаруживает директорую снова запускает рекурсивно саму себя
    private static void printListOfFiles(File dirFile, int indent) {
        //создаем графические отступы для разных уровней
        String strDirIndent = new String(new char[indent]).replace("\0", " ") + "f--v";
        String strFileIndent = new String(new char[indent]).replace("\0", " ") + "   |-->";

        //выводим имя директории перед обходом
        System.out.println(strDirIndent + " " + dirFile.getName());

        //Обходим директорию с файлами
        File fileList[] = dirFile.listFiles();
        for (File file : fileList) {
            if (file.isFile()) {
                //если файл  - выводим название и размер в консоль
                System.out.println(strFileIndent + " " + file.getName() + " (" + file.length() + ")");
            } else {
                //запускаем еще один экземпляр printListOfFiles рекурсивно
                printListOfFiles(file.getAbsoluteFile(), indent + 1);
            }
        }
    }
}
