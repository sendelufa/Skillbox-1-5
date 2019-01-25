/**
 * Project StopWatch.StopWatch
 * Created by Shibkov Konstantin on 14.01.2019.
 */
package GUI.GUI;

import GUI.Form.StopWatchForm;
import GUI.TxtConst;
import StopWatch.StopWatch;

import javax.swing.*;
import java.awt.*;

public class StopWatchUI {
    final int FRAME_WIDTH = 600; //длина X окна
    final int FRAME_HEIGHT = 200; //длина Y окна
    private JFrame frame;
    private StopWatchForm swf;


    public StopWatchUI() {
        frame = new JFrame("Секундомер");
    }

    public void InitUI() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        //UI Manager tuner
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //Run UI in Thread
        swf = new StopWatchForm();
        startStopWatch();
        SwingUtilities.invokeLater(() -> {
            frame.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
            frame.setResizable(false);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setContentPane(swf.getMainPanel());
            frame.setLocationRelativeTo(null); //выравниваем окно по центру экрана
            swf.getBtnStart().setText(TxtConst.START);
            swf.getBtnStop().setText(TxtConst.STOP);
            frame.setVisible(true);
        });
    }

    private void startStopWatch(){
        swf.setSw(new StopWatch(swf));
    }
}
