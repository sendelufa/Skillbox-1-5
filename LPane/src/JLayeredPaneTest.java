/**
 * Project LPane
 * Created by Shibkov Konstantin on 02.01.2019.
 */

import javax.swing.*;
import java.awt.*;

public class JLayeredPaneTest {
    static JFrame frame = new JFrame();
    public static void main(String[] args) {
        int width = 800; //длина X окна
        int height = 600; //длина Y окна
        frame.setMinimumSize(new Dimension(600, 400));

        JLayeredPane lp = frame.getLayeredPane();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JTextArea(), BorderLayout.CENTER);
        panel.add(new JButton("Submit"), BorderLayout.SOUTH);
        panel.setSize(300, 150); // Size is needed here, as there is no layout in lp

        JPanel glass = new JPanel();
        glass.setOpaque(true); // Set to true to see it
        glass.setBackground(Color.GREEN);
        glass.setSize(300, 150);
        glass.setLocation(10, 10);

        lp.add(panel, Integer.valueOf(1));
        lp.add(glass, Integer.valueOf(2));

        frame.setTitle("WindowApp - " + width + "x" + height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); //выравниваем окно по центру экрана
        frame.setVisible(true);
    }
}

