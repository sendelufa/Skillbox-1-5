/**
 * Project lesson_5
 * Created by Shibkov Konstantin on 05.01.2019.
 */
package sendel;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsolePathReader {

    public static String getDirPathFromConsole(String invitation) throws IOException {
        for (; ; ) {
            System.out.println(invitation);
            BufferedReader dirPath = new BufferedReader(new InputStreamReader((System.in)));
            String read = dirPath.readLine().trim();

            if (new File(read).isDirectory()) return read;
            else System.out.println(" -- Неверный путь к папке");
        }
    }
}
