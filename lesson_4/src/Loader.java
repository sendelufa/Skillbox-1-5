import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Project lesson_4
 * Created by Shibkov Konstantin on 05.01.2019.
 */

public class Loader {
    //исходящая папка без / на конце
    private static String folderOutput = "res/images";

    public static void main(String[] args) throws IOException, URISyntaxException {
        //Загружаем и парсим изображения
        String siteUrl = "https://i-foto-graf.com/photo/";
        Document doc = Jsoup.connect(siteUrl).get();
        Elements elements = doc.select("img[src]");

        //создаем папку с timestamp и формируем пути к папке
        long folderStamp = System.nanoTime();

        URL url = new URL(siteUrl);
        String newFolder = folderOutput + "/" + url.getHost() + "_" + folderStamp;
        folderOutput = newFolder + "/";

        //создаем папку для сохранения
        try {
            new File(newFolder).mkdirs();
            System.out.println("Создана новая папка:" + newFolder);
        } catch (Exception e) {
            System.out.println("Ошибка при создании папки! " + e.toString());
        }

        //обходим все объекты изображений
        for (Element elem : elements) {
            //получаем абсолютный путь к изображению и отправляем на запись
            String absURL = elem.attr("abs:src");

            //проверка на существование картинки и вызов метода записи файла
            if (!getFileNameFromURL(absURL).equals("")) {
                saveFileToDisk(absURL, folderOutput + getFileNameFromURL(absURL));
            }
        }

        System.out.println("\nВсе файлы изображений скачены успешно!");
    }

    //функция формирования названия файла на диске вида [хост]_[порт]_[папки]_[название файла]
    private static String getFileNameFromURL(String absURL) throws MalformedURLException {
        URL url = new URL(absURL);
        //храним название файла на выходе
        String filename = "";

        //регулярное выражение для поиска имени файла
        Pattern pattern = Pattern.compile("[^/\\&\\?]+\\.\\w{3,4}(?=([\\?&].*$|$))");
        Matcher matcher = pattern.matcher(absURL);

        //получаем структуру папки для формирование названия файла на диске
        String folder = absURL.substring(absURL.lastIndexOf(url.getHost()) + url.getHost().length(), absURL.lastIndexOf("/"));
        folder = folder.replace("/", "_");

        //вытаскиваем название изображения и собираем название файла вида [хост]_[порт]_[папки]_[название файла]
        if (matcher.find()) {
            filename = url.getProtocol() + "_" +url.getHost() + "_" + url.getPort() + folder + "_" + matcher.group();
        }
        return filename;
    }

    //загружаем файл изображения и записываем на диск
    private static void saveFileToDisk(String absURL, String filename) throws IOException {
        URL url = new URL(absURL);

        //открываем потоки записи и сохранения
        InputStream stream = url.openStream();
        FileOutputStream fos = new FileOutputStream(filename);

        for (; ; ) {
            //читаем
            int code = stream.read();
            if (code < 0) break;

            //записываем
            fos.write(code);
        }
        fos.flush();
        fos.close();

        System.out.println("Файл сохранен успешно: " + filename);
    }
}
