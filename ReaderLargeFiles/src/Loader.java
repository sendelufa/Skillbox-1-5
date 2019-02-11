import UI.Form.UiHandler;

import javax.swing.*;

/**
 * Project ReaderLargeFiles
 * Created by Shibkov Konstantin on 06.02.2019.
 */

public class Loader {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        //создаем основную форму
        UiHandler ui = new UiHandler();
        //запускаем форму
        ui.InitUI();

    }
}
