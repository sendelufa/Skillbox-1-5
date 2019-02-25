/**
 * Project Hibernate
 * Created by Shibkov Konstantin on 12.01.2019.
 */
package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public  class SendelUtils {
    //выравниваем ячейки добавляя пробелы
    public static String addSpaces(String lineIn) {
        String lines[] = lineIn.split(";");
        String lineFinal = "";
        int j = 0;
        for (String line : lines) {
            line = line.trim();
            j++;
            lineFinal += line;
            int lengthOfRow = 25;
            if (line.matches("[0-9]{1,4}|№ отп|№")) {
                lengthOfRow = 5;
            }
            for (int i = 0; i < (lengthOfRow - line.length()); i++) {
                lineFinal += " ";
            }
            if (lines.length != j) lineFinal += " | ";
        }
        return lineFinal;
    }

    public static int getDepartmentIdFromConsole(String invitation) throws IOException {
        for (; ; ) {
            System.out.println(invitation);
            BufferedReader dirPath = new BufferedReader(new InputStreamReader((System.in)));
            String read = dirPath.readLine().trim();

            try{return Integer.parseInt(read);}
            catch (Exception e) {
                continue;
            }
            //else System.out.println(" -- Неверный путь к папке");
        }
    }
}
