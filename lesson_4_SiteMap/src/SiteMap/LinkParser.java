package SiteMap;

import GUI.Forms.MainForm;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
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
    private static String host;
    private static MainForm mainForm;
    //результат записан в Map <ссылка, откуда не нее ссылаются>
    private static Map<String, String> linksMap;
    //очередь задач
    private static Queue<String> taskQueue = new ConcurrentLinkedQueue<>();

    //флаги для остановки и паузы
    volatile private static boolean isStop;
    volatile private static boolean isPause;
    private static boolean isRegularStop;
    private static boolean connectionErrorTimeoutLog;
    //флаг работающего потока
    private volatile boolean isWorking;
    //ответ полученный при запросе через JSoup
    private Connection.Response response;
    private boolean statePause = false;

    public LinkParser() {

    }

    //чистим массив и присваиваем переменные
    static public void resetParser(String su, String h, MainForm mf) {
        linksMap = new ConcurrentHashMap<>();
        linksMap.clear();
        isStop = false;
        isPause = false;
        isRegularStop = false;
        connectionErrorTimeoutLog = false;
        taskQueue.clear();
        host = h;
        mainForm = mf;
        taskQueue.add(su);
        mainForm.writeLog("=============\n Start make sitemap of " + su + "\n=============");
    }

    //меняем флаг isStop для остановки всех потоков
    public static void endAllThreads() {
        //запускаем все потоки, для их безаварийной остановки, без этого не работает остановка при паузе
        AllPlay();
        isStop = true;
        mainForm.writeNumberLinks("Выполнение прервано, файл не записан!");
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

    //возврат флага на постановку на паузу
    public static boolean isPause() {
        return isPause;
    }

    public static Map<String, String> getLinksMap() {
        return linksMap;
    }

    public static boolean isRegularStop() {
        return isRegularStop;
    }

    private boolean checkStop() {
        if (isStop()) {
            isWorking = false;
        }
        return isStop();
    }

    public boolean isWorking() {
        return isWorking;
    }

    @Override
    public void run() {
        isWorking = true;
        parseLink();
        isWorking = false;

    }

    private void parseLink() {
        String link;
        //если флаг остановки true - завершаем все threads
        if (checkStop()) return;

        while (!isStop) {
            link = getTask();

            if (link == null) {
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isWorking = false;
                continue;

            }
            //проверяем надо ли поставить на паузу
            checkPause();

            if (checkStop()) return;

            //пытаемся получить доступ к странице
            requestResponse(link);

            if (checkStop()) return;

            //не парсим ссылку если от нее нет ответа или ответ ошибочный
            if (response == null) continue;
            //не отправляем на парсинг если статус код не 200 и в ссылке содержится якорь
            if (response.statusCode() == 200 && link.indexOf('#') == -1) {
                //парсим HTML страницы, выбираем все ссылки a[href]
                //если IOException пропускаем ссылку
                Elements elements;
                try {
                    elements = getAnchorsWithHref();
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }

                //обходим все найденные ссылки
                for (Element element : elements) {
                    String foundHrefLink = element.attr("abs:href");
                    //проверяем принадлежит ссылка текущему хосту и не является внутренней ссылкой
                    if (foundHrefLink.indexOf(host) == 0 && foundHrefLink.indexOf('#') == -1) {
                        if (addToSet(foundHrefLink, link)) {
                            if (checkStop()) return;
                            addTask(foundHrefLink);
                            mainForm.writeLog(currentThread().getName() + " " + foundHrefLink);
                        }
                    }
                }

            }

            //поток засыпает для того чтобы сайт не закрыл к себе доступ
            long sleepTime = (long) (Math.random() * (150 - 100) + 100);
            try {
                sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }

        isWorking = false;
    }

    private synchronized void addTask(String link) {
        if (Utils.isValidForParseURL(link)) {
            taskQueue.add(link);
        }
    }

    private synchronized String getTask() {
        return taskQueue.poll();
    }

    private synchronized boolean addToSet(String link, String ParentLink) {
        boolean isContainKey = linksMap.containsKey(link);
        if (!isContainKey) {
            String lll = String.valueOf(link);
            link = convertSpaces(link);
            if (!lll.equals(link)) {
                System.out.println(link);
            }
            linksMap.put(link, ParentLink);
            if (checkStop()) return false;
            mainForm.writeNumberLinks("Найдено ссылок: " + linksMap.size() + " шт.");
        }
        if (isStop) mainForm.writeNumberLinks("Выполнение прервано, файл не записан!");
        return !isContainKey;
    }

    private void requestResponse(String link) {
        //пытаемся получить доступ к странице
        boolean validJSoupConnection = true;
        response = null;
        int httpErrorCount = 0;
        while (validJSoupConnection) {
            try {
                //устанавливаем соединение
                response = Jsoup.connect(link)
                        .userAgent("Sendel Sitemap parser")
                        .timeout(50000)
                        .execute();
                //ответ сервера на запрос URL
                validJSoupConnection = false;
            } catch (HttpStatusException e) {
                if (checkStop()) return;
                System.out.println(e.getStatusCode() + " " + link);
                if (e.getStatusCode() != 200) return;
            } catch (UnsupportedMimeTypeException mime) {
                return;
            } catch (Exception e) {
                //вывод информации об исключении
                if (!connectionErrorTimeoutLog) {
                    if (checkStop()) return;
                    mainForm.writeLog(currentThread().getName() + " - нет доступа к \n\t" + link + "\n," +
                            " новая попытка через 1 сек... [httpErrorCount=" + httpErrorCount + "]");
                    System.out.println(e.toString());
                }
                try {
                    sleep(1000);
                    httpErrorCount++;
                    connectionErrorTimeoutLog = false;
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                if (httpErrorCount > 2) {
                    return;
                }
            }
        }

    }

    private void checkPause() {
        while (isPause) {
            statePause = true;
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(currentThread().getName() + " выходит из сна!");
            }
        }
        statePause = false;
    }

    private Elements getAnchorsWithHref() throws IOException {
        return response.parse().select("a[href]");
    }

    //Для правильного парсинга ссылок с пробелами
    private String convertSpaces(String link) {
        return link.replaceAll("\\s", "%20");
    }

    public boolean isStatePause() {
        return statePause;
    }
}
