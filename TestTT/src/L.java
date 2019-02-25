/**
 * Project TestTT
 * Created by Shibkov Konstantin on 25.12.2018.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.Random;

public class L extends JFrame {

    private static final long serialVersionUID = 1L;
    static JFrame frame = new JFrame("simpleApp");

    public L() {


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024,768);

        frame.setVisible(true);

    }


}

class Panel extends JPanel {


    public void paintComponent(Graphics g) {

        Random r = new Random();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.drawLine(r.nextInt(1024), r.nextInt(768), r.nextInt(1024)+400, r.nextInt(768)+400);

    }

}

class Action implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        Panel p = new Panel();
        L.frame.add(p);
        p.revalidate();
        p.repaint();



    }



}