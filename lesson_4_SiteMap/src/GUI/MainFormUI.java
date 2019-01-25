/**
 * Project StopWatch.StopWatch
 * Created by Shibkov Konstantin on 18.01.2019.
 */
package GUI;

import GUI.Forms.MainForm;

import javax.swing.*;
import java.awt.*;


public class MainFormUI {
    final int FRAME_WIDTH = 600; //длина X окна
    final int FRAME_HEIGHT = 600; //длина Y окна
    private JFrame frame;
    private MainForm mainForm;

    public MainFormUI() {
        frame = new JFrame("Sitemap");
    }

    public void InitUI() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        //UI Manager tuner
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //Run UI in Thread


        SwingUtilities.invokeLater(() -> {
            mainForm = new MainForm();
            frame.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
            frame.setResizable(false);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setContentPane(mainForm.getMainPanel());
            frame.setLocationRelativeTo(null); //выравниваем окно по центру экрана
            frame.setVisible(true);

            mainForm.resetForm();
            mainForm.getBtnChooseOutputFile().requestFocus();
        });
    }






}
