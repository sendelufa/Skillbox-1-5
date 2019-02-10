import File.FileHandling;
import UI.Form.UiHandler;

import javax.swing.*;
import java.io.IOException;

/**
 * Project ReaderLargeFiles
 * Created by Shibkov Konstantin on 06.02.2019.
 */

public class Loader {

    private static FileHandling worker;

    public static void main(String[] args) throws IOException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        //создаем основную форму
        UiHandler ui = new UiHandler();
        //запускаем форму
        ui.InitUI();

    }
}
