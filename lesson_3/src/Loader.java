import GUI.GUI.StopWatchUI;

import javax.swing.*;

/**
 * Project StopWatch.StopWatch
 * Created by Shibkov Konstantin on 14.01.2019.
 */

public class Loader {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        StopWatchUI frame = new StopWatchUI();
        frame.InitUI();
    }
}
