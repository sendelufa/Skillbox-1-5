import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Danya on 28.10.2015.
 */
public class Form {
    private JPanel rootPanel;
    private JPanel buttons;
    private JButton startButton;
    private JProgressBar progressBar;
    private JButton stopButton;
    private Processor processor;

    public Form() {
        Form form = this;
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                processor = new Processor(form);
                processor.start();
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processor.interrupt();
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public JButton getStartButton() {
        return startButton;
    }
}
