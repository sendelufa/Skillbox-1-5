import java.io.*;
import java.nio.file.Files;
import java.util.regex.Pattern;


/**
 * Project work
 * Created by Shibkov Konstantin on 05.01.2019.
 */

public class Loader {
    static String pathDirSource;
    static String pathDirDestination;

    public static void main(String[] args) throws IOException {
        System.out.println("== Программа копирование директории ==");
        //Получаем путь к директориям
        pathDirSource = getPathFromConsole("Введите путь к исходной папке:");

        //запрос на ввод папки назначения с проверкой на корректность
        do {
            pathDirDestination = getPathFromConsole("Введите путь папки в которую требуется скопировать:");
        } while (checkDestinationInSource(pathDirSource, pathDirDestination));

        System.out.println("копируется папка " + pathDirSource + " в " + pathDirDestination + "\n");
        copyListOfFiles(new File(pathDirSource), new File(pathDirDestination));
    }

    //получаем из консоли путь к директории
    private static String getPathFromConsole(String invitation) throws IOException {
        for (; ; ) {
            System.out.println(invitation);
            BufferedReader dirPath = new BufferedReader(new InputStreamReader((System.in)));
            String read = dirPath.readLine().trim();
            //преобразуем строку для работы в Win/MacOS/Unix
            read = replaceSlash(read);
            System.out.println(read);
            if (new File(read).isDirectory()) return read;
            else System.out.println(" -- Неверный путь к папке");
        }
    }

    //Проверка на копирование папки внутри самой себя или саму себя
    private static boolean checkDestinationInSource(String sourceDir, String destanationDir) {
        boolean result = destanationDir.contains(sourceDir);
        //проверяем что пытаются скопировать папку в саму себя (пр. C:\Folder\1 -> C:\Folder)
        int lastFolderStartIndex = sourceDir.lastIndexOf("\\") == -1 ? sourceDir.lastIndexOf("/") : sourceDir.lastIndexOf("\\");
        String sourceBody = sourceDir.substring(0, lastFolderStartIndex);
        return sourceBody.contains(destanationDir);
    }

    //основная функция - обходит директорию и копирует папки,
    //как только обнаруживает директорую снова запускает рекурсивно саму себя
    private static void copyListOfFiles(File sourceFile, File destFile) throws IOException {
        File fileList[] = sourceFile.listFiles();
        for (File file : fileList) {
            //если файл  - выводим лог копирования и копируем файл
            if (file.isFile()) {
                String newFilePath = destFile.getPath() + "/" + file.getName();
                System.out.println("start copy " + file.getPath() + " -> " + newFilePath);
                //копирование файла
                try {
                    Files.copy(new File(file.getPath()).toPath(), new File(newFilePath).toPath());
                }
                //если файл существует - не перезаписывать, только выдать предупреждение
                catch (java.nio.file.FileAlreadyExistsException e) {
                    System.out.println("   !!!   " + newFilePath + " существует, файл не скопирован!");
                }
            } else {
                //проверка на рекурсивную запись в одну папку бесконечное количество раз
                //проверка позволяет пропускать цикл самозаписи и записывать папку в саму себя
                if (replaceSlash(sourceFile.getPath()).equals(pathDirDestination)) {
                    return;
                }
                //запускаем еще один экземпляр copyListOfFiles рекурсивно при нахождении директории
                //абсолютный путь к новой директории
                String newFolder = pathDirDestination + file.getPath().substring(pathDirSource.length());
                newFolder = replaceSlash(newFolder);
                //лог в консоль
                System.out.println("Создание папки:" + newFolder);
                //создание папки
                new File(newFolder).mkdirs();
                //вызываем функцию рекурсивно
                String filename = replaceSlash(file.getPath());
                copyListOfFiles(new File(filename), new File(newFolder));
            }
        }
    }

    //метод приведения путей к Unix формату, для работы во всех ОС
    private static String replaceSlash(String path){
        //исправление количества слешей в пути
        path = path.replace("\\", "/");
        path = path.replaceAll("([/]+)", "/");
        return path;
    }
}
