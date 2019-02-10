/**
 * Project ReaderLargeFiles
 * Created by Shibkov Konstantin on 06.02.2019.
 */
package File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;


public class FileHandling {
    private RandomAccessFile file;
    //с этой позиции начинаем читать
    public long startPointer = 0L;
    //курсор для четения
    private long readPointer = 0L;


    public FileHandling(String path) throws FileNotFoundException {
        //open file to read
        file = new RandomAccessFile(path, "r");
    }

    public long getFileLenght() throws IOException {
        return file.length();
    }

    //place pointer in file
    public void movePointer(long num) {
        startPointer = num;
    }

    // One Screen = 26 lines
    public String readLinesFromCurrentPointer(int lineNumber) throws IOException {
        file.seek(startPointer);
        int symbol = -100000;
        long localPointer = startPointer;
        if (lineNumber > 0){

            while(symbol != 10 && symbol !=-1){
                symbol = file.read();
               System.out.println(symbol + "=" + (char) symbol);
            }
            //file.read();
            localPointer = file.getFilePointer();

        }



            startPointer = localPointer;
            return readFromPointer(localPointer);
    }

    //read file from pointer position
    private String readFromPointer(long numSymbol) throws IOException {
        StringBuilder result = new StringBuilder();
        System.out.println(numSymbol);
        //set pointer in file
        file.seek(numSymbol);
        for (int i = 0; i < 26; i++) {
            String line = file.readLine();
            if (line == null) {break;}
            line = new String(line.getBytes("ISO-8859-1"), "UTF8");
            result.append(line);
            result.append("\n");
        }

        return result.toString();

    }


    public long getStartPointer() {
        return startPointer;
    }
}
