/**
 * Project StopWatch.StopWatch
 * Created by Shibkov Konstantin on 14.01.2019.
 */
package GUI.Form;

import StopWatch.StopWatch;
import javax.swing.*;

public class StopWatchForm {
    private JPanel mainPanel;
    private JLabel lblDigits;
    private JButton btnStart;
    private JButton btnStop;
    //Not GUI properties
    private StopWatch sw;


    public StopWatchForm() {
        btnStart.addActionListener(e -> {
            //если поток не запущен - запускаем
            if (!sw.isAlive()) {
                sw = new StopWatch(sw.getForm());
                sw.start();
            } else {
                //если поток работает, ставим и снимаем с паузы
                if (sw.isPaused()) sw.setPaused(false);
                else sw.setPaused(true);
                               }
        });

        btnStop.addActionListener(e -> sw.exit());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JLabel getLblDigits() {
        return lblDigits;
    }

    public JButton getBtnStart() {
        return btnStart;
    }

    public void setSw(StopWatch sw) {
        this.sw = sw;
    }

    public JButton getBtnStop() {
        return btnStop;
    }
}
