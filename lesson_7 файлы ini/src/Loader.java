import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Project lesson_7
 * Created by Shibkov Konstantin on 05.01.2019.
 */

public class Loader {
    public static void main(String[] args) throws IOException {
        String path = "src/settings.ini";

        Properties props = new Properties();
        props.setProperty("first", "on");
        props.setProperty("show", "100");

        props.store(new FileOutputStream(path), "comments\ncc");
    }
}
