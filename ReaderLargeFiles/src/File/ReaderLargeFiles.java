/**
 * Project ReaderLargeFiles
 * Created by Shibkov Konstantin on 09.02.2019.
 */
package File;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ReaderLargeFiles {
    private FileHandling fileHand;
    private JTextArea textArea;
    private JScrollBar scrollBar;
    private String textToView;

    public ReaderLargeFiles(File f, JTextArea txtFileView, JScrollBar scrollBar) throws IOException {
        fileHand = new FileHandling(f.getPath());
        textArea = txtFileView;
        this.scrollBar = scrollBar;
    }

    public void displayTextToForm(String str) {
        textArea.setText(str);
    }

    public void readFileLines(int lines) {
        try {
            String str = fileHand.readLinesFromCurrentPointer(lines);
            displayTextToForm(str);
            scrollBar.setValue((int) fileHand.getStartPointer());

        } catch (IOException e) {
            e.printStackTrace();
            displayTextToForm("===\nОшибка чтения файла!!!\n===");
        }


    }

    public long getFileLength() throws IOException {
        return fileHand.getFileLenght();
    }

    public void movePointer(long pointer) {
        fileHand.movePointer(pointer);
    }
    public FileHandling getFile(){
        return fileHand;
    }


}
