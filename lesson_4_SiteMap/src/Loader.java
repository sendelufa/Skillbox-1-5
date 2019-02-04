import GUI.MainFormUI;

import javax.swing.*;

/**
 * Project SiteMap.SiteMap
 * Created by Shibkov Konstantin on 18.01.2019.
 */

public class Loader {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        //создаем основную форму
        MainFormUI frame = new MainFormUI();
        //запускаем форму
        frame.InitUI();
    }
}
