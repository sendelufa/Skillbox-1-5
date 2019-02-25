import java.io.*;
/**
 * Project work
 * Created by Shibkov Konstantin on 03.01.2019.
 */

public class Loader {
    static String path;

    public static void main(String[] args) throws IOException {
        System.out.println("== Программа форматирования файла Probabilities ==");
        //Получаем путь к файлу и форматируем
        path = getPathFromConsole();
        writeFile(path, formatFile(path));
    }

    //получаем из консоли путь к файлу
    //тестовый файл res\Probabilites.txt
    private static String getPathFromConsole() throws IOException {
        for (; ; ) {
            System.out.println("Введите путь к файлу:");
            BufferedReader dirPath = new BufferedReader(new InputStreamReader((System.in)));
            String read = dirPath.readLine().trim();

            if (new File(read).isFile()) {
                return read;
            } else {
                System.out.println(" -- Неверный путь к файлу");
            }

        }

    }

    //читаем файл и форматируем его
    private static String formatFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String txtFileContent = "";
        for (; ; ) {
            String line = reader.readLine();
            if (line == null) break;
            String lines[] = line.split("\t");
            //формируем строку с использованием переноса строки зависимым от ОС
            txtFileContent += addSpaces(lines) + System.lineSeparator();
        }
        return txtFileContent;
    }

    //выравниваем ячейки добавляя пробелы
    private static String addSpaces(String lines[]) {
        String lineFinal = "";
        int j = 0;
        for (String line : lines) {
            j++;
            lineFinal += line;
            for (int i = 0; i < (11 - line.length()); i++) {
                lineFinal += " ";
            }
            if (lines.length != j) lineFinal += "\t|\t";
        }
        return lineFinal;
    }

    //метод записи файла с изменением названия файла, добавляем -formatted
    private static void writeFile(String path, String content) {
        String newPath = path.substring(0, path.lastIndexOf(".")) + "-formatted.txt";
        try(OutputStream  os = new FileOutputStream(newPath, false))
        {
            Writer writer = new OutputStreamWriter(os);
            // запись всего текста сразу
            writer.write(content);
            // запись окончания файла
            writer.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
