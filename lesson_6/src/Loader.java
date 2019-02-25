import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Project lesson_6
 * Created by Shibkov Konstantin on 05.01.2019.
 */

public class Loader {


    public static void main(String[] args) throws IOException {


        URL url = new URL("http://sendel.ru");
        InputStream stream = url.openStream();
        FileOutputStream fos = new FileOutputStream("E:\\Skillbox\\Java\\M10\\1.html");

        for (; ; ) {
            int code = stream.read();
            if (code < 0) break;

            fos.write(code);
            char c = (char) code;
            System.out.print(c);

    }
        fos.flush();
        fos.close();

    }
}
