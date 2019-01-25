/**
 * Project SiteMap
 * Created by Shibkov Konstantin on 21.01.2019.
 */
package sendel.utils;

import java.io.*;
import java.util.Map;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    //проверяем строку, является ли она URL
    static public boolean isValidURL(String url) {
        return url.matches("^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    }

    //проверяем строку, является ли она URL
    static public boolean isValidForParseURL(String url) {
        return !url.matches("^(https?):\\/\\/[-a-zA-Z0-9+&@#\\/.%?_=]*(.pdf|.jpeg|.jpg|.png|.gif)+[?=_%.a-zA-Z0-9]*$");
    }

    //проверка последнего слэша в адресе и его добавления. http://site.com -> http://site.com/
    static public String addTailSlash(String url){
        return url.matches("^(https?):\\/\\/[-a-zA-Z0-9+&@#\\/.]*[^.html|^.php|^.htm|^.shtml|^\\/]$") ?
                url + "/" : url;
    }

    static public String getHost(String url) {
        String host = null;
        Pattern p = Pattern.compile("^((https?):\\/+)?\\/?([^:\\/\\s]+)((\\/\\w+)*\\/?)");
        Matcher matcher = p.matcher(url);
        if (matcher.find()) {
            host = matcher.group(1) + matcher.group(3);
        }
        return host;
    }

    static public String writeSiteMapToFile(Map<String, String> map, File file) {

        //открываем потоки записи и сохранения
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file.getPath());

            //записываем данные из набора в файл
            for (Map.Entry<String, String> str : map.entrySet()) {
                //считаем отступы
                int offset = (int) str.getKey().chars().filter(ch -> ch == '/').count() - 3;
                for (int i = 0; i < offset; i++) {
                    fos.write('\t');
                }
                fos.write(str.getKey().getBytes());
                fos.write("\n".getBytes());

            }
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка записи файла!";
        }
        return "Ссылки успешно записаны в файл: " + file.getPath();
    }
}
