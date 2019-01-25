import javax.swing.*;
import java.util.Calendar;

/**
 * Project GUIMultithreading
 * Created by Shibkov Konstantin on 13.01.2019.
 */

public class Processor extends Thread {
    private JProgressBar progressBar;
    private JButton startButton;
    private JButton stopButton;

    public Processor(Form form){
        this.progressBar = form.getProgressBar();
        this.startButton = form.getStartButton();
    }
    @Override
    public void run() {
        int count = 1_000_000_000;
        startButton.setEnabled(false);
        for(int i = 0; i < count; i++)
        {
            if (isInterrupted()) {
                progressBar.setValue(0);
                break;
            }
            progressBar.setValue((int) Math.round(i*100./count));
        }
        startButton.setEnabled(true);
    }
}
