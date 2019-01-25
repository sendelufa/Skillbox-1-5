/**
 * Project StopWatch
 * Created by Shibkov Konstantin on 14.01.2019.
 */
package StopWatch;

import javax.swing.*;
import java.awt.*;

public class BlinkLabel extends Thread {
    private JLabel lbl;
    private boolean isStop;

    public BlinkLabel(JLabel lbl){
        this.lbl = lbl;
    }

    @Override
    public void run() {
        System.out.println("Начало потка моргания");
        boolean isColored = true;
        isStop = false;
        while (!isStop){
            Color c = isColored ? Color.BLACK : Color.ORANGE;
            lbl.setForeground(c);
            isColored = !isColored;
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }
}
