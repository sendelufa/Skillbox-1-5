package SiteMap;

import GUI.Forms.MainForm;
import javafx.concurrent.Task;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sendel.utils.Utils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Project SiteMap.SiteMap
 * Created by Shibkov Konstantin on 18.01.2019.
 * <p>
 * для работы с threads
 */

public class LinkParser extends Thread {
    //стартовый URL с чего начинаем обход
    private static String startURL;
    private static String host;
    //Хранимлище найденнх ссылок - выбран HashSet за счет быстрого сравнения contains
    private static TreeSet<String> linksSet = new TreeSet<>();
    private static MainForm mainForm;

    //результат записан в Map <ссылка, откуда не нее ссылаются>
    private static Map<String, String> linksMap;

//    private static ArrayList<String> tasks = new ArrayList<>();
    private static Queue<String> taskQueue = new ConcurrentLinkedQueue<>();
//    volatile private static int workingThreads;

    //флаги для остановки и паузы
    volatile private static boolean isStop;
    volatile private static boolean isPause;
    private static boolean isRegularStop;

    private boolean isWorking;

    public LinkParser() {

    }

    //чистим массив и присваиваем переменные
    static public void resetParser(String su, String h, MainForm mf) {
        linksMap = new ConcurrentHashMap<>();
        linksMap.clear();
        isStop = false;
        isPause = false;
        isRegularStop = false;
        taskQueue.clear();
//        workingThreads = 0;
        startURL = su;
        host = h;
        mainForm = mf;
        //хост всегда добавлен изначально
//        linksSet.add(startURL);
//        tasks.add(startURL);
        taskQueue.add(startURL);
        mainForm.writeLog("=============\n Start make sitemap of " + startURL + "\n=============");
    }


    //меняем флаг isStop для остановки всех потоков
    public static void endAllThreads() {
        isStop = true;
        mainForm.writeNumberLinks("Выполнение прервано, файл не записан!");
        mainForm.writeLog("========================================");
        mainForm.writeLog("  Выполнение прервано, файл не записан!");
        mainForm.writeLog("========================================");
    }

    //меняем флаг isStop для штатного завершения
    public static void endAllThreadsRegular(boolean regularStop) {
        isRegularStop = true;
        isStop = regularStop;
    }

    //все потоки на паузу
    public static void AllPause() {
        isPause = true;
    }

    //все потоки продолжают работать
    public static void AllPlay() {
        isPause = false;
    }

    //возврат флага на остановку потоков
    public static boolean isStop() {
        return isStop;
    }

    private boolean checkStop(){
        if (isStop()) {
        isWorking = false;
        }
        return isStop();
    }

    //возврат флага на постановку на паузу
    public static boolean isPause() {
        return isPause;
    }

    //возращаем коллекцию ссылок
//    public static TreeSet<String> getLinksSet() {
//        return linksSet;
//    }

    public static Map<String, String> getLinksMap() {
        return linksMap;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public static boolean isRegularStop() {
        return isRegularStop;
    }

    @Override
    public void run() {
        try {
            long startTime = System.currentTimeMillis();
            parseLink(startURL);
            long timeSpent = System.currentTimeMillis() - startTime;
            System.out.println("программа выполнялась " + timeSpent + " миллисекунд" + currentThread().getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseLink(String link) throws IOException {
        //ставим на паузу
        while (isPause) {
            System.out.println(currentThread().getName() + " засыпает!");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(currentThread().getName() + " выходит из сна!");
                return;
            }
        }
        //если флаг остановки true - завершаем все threads
        if (checkStop()) return;

        while (!isStop) {
            link = getTask();

            if (link == null) {isWorking = false;continue;}
            else {isWorking = true;}

            int statusCode = 0;
            Connection.Response response = null;
            //пытаемся получить доступ к странице
            try {
                //устанавливаем соединение
                response = Jsoup.connect(link)
                        .userAgent("Sendel Sitemap parser")
                        .timeout(50000)
                        .execute();
                //ответ сервера на запрос URL
                statusCode = response.statusCode();
            } catch (Exception e) {
                //вывод информации об исключении
                e.getStackTrace();
            }
            //не отправляем на парсинг если статус код не 200 и в ссылке содержится якорь
            if (statusCode == 200 && link.indexOf('#') == -1) {
                //парсим HTML страницы, выбираем все ссылки
                Elements elements = response.parse().select("a[href]");
                //обходим все найденные ссылки
                for (Element element : elements) {
                    String foundHrefLink = element.attr("abs:href");
                    //проверяем принадлежит ссылка текущему хосту (внутренняя ссылка)
                    if (foundHrefLink.indexOf(host) == 0 && foundHrefLink.indexOf('#') == -1) {
                        //если URL нет в наборе - добавляем и запускаем парсинг
                        if (checkStop()) return;
                        if (addToSet(foundHrefLink, link)) {
                            if (Utils.isValidForParseURL(foundHrefLink)) {
                                //Добавляем задание
                                addTask(foundHrefLink);
                            }

                        }
                    }
                }

            }
        }
    }

     private synchronized void addTask(String link) {
        taskQueue.add(link);
    }

    private synchronized String getTask() {

        return taskQueue.poll();
    }

    //возращаем коллекцию ссылок (версия без рекурсии)

    private synchronized boolean addToSet(String link, String ParentLink) {
        boolean isContainKey = linksMap.containsKey(link);
        if (!isContainKey) {
            linksMap.put(link, ParentLink);
            mainForm.writeLog(currentThread().getName() + " " + link);
            mainForm.writeNumberLinks("Найдено ссылок: " + linksMap.size() + " шт.");

        }
        if (isStop) mainForm.writeNumberLinks("Выполнение прервано, файл не записан!");


        return !isContainKey;
    }
}
