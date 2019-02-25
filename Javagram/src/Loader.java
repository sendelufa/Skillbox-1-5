import forms.MyGlassPanel;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


/**
 * Project Javagram
 * Created by Shibkov Konstantin on 24.12.2018.
 */

public class Loader {
    final static int FRAME_WIDTH = 800; //длина X окна
    final static int FRAME_HEIGHT = 600; //длина Y окна
    private static JFrame frame = new JFrame("Javagram");
    private static Point point = new Point();
    private static File f = new File("res/font/OpenSansRegular.ttf");
    private static JLayeredPane lp = frame.getLayeredPane();
    private static JPanel MainC;
    private static UndecoratedResize ur;

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        //UI Manager tuner
        System.setProperty("awt.useSystemAAFontSettings", "on");
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        //FONT
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initFrame(FRAME_WIDTH, FRAME_HEIGHT);
            }
        });

    }

    public static void minimizeFrame() {
        frame.setState(JFrame.ICONIFIED);
    }

    //add to Headline Jpanel MouseAdapter to drag Windows over the screen
    static void MouseAdapterDrag(JPanel panelDrag) {
        // The mouse listener and mouse motion listener we add here is to simply
        // make our frame dragable.
        panelDrag.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                point.x = e.getX();
                point.y = e.getY();
            }
        });
        panelDrag.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point p = frame.getLocation();
                frame.setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
            }
        });
    }

    public static Font getMainFont(int size) throws FontFormatException, IOException {
        return Font.createFont(Font.TRUETYPE_FONT, f).deriveFont((float) size);
    }

    private static void initFrame(int FRAME_WIDTH, int FRAME_HEIGHT) {
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null); //выравниваем окно по центру экрана

        SignIn mainForm = new SignIn();

        MainC = mainForm.getMainPanel();

       frameSetContent(MainC);


        //frame.pack();
        //frame.repaint();
        frame.setVisible(true);
    }


    public static void makeFrameResizable() {
        ur = new UndecoratedResize();
        frame.add(ur.createAnsShowGui(frame, (short) 800, (short) 600));
    }

    public static void resetFrame() {
        Container c = frame.getContentPane();
        lp.removeAll();
        frame.setContentPane(c);
    }
    public static void repaintFrame() {
    frame.repaint();    }

    public static void frameSetContent(JPanel p) {
        lp.removeAll();
        frame.setContentPane(p);
        frame.repaint();
        frame.setVisible(true);
    }

    public static void frameSetContent(JPanel content, JPanel layered) {
        //frame.removeAll();
        System.out.println(layered.toString());
        lp.removeAll();
        frame.setContentPane(content);

        lp.add(layered, Integer.valueOf(2));
        frame.repaint();
        frame.setVisible(true);
    }

    //set layered above content Pane, ONLY ONE PANEL!
    public static void frameSetLayered(JPanel layered, JPanel layeredBg) {
        Container c = frame.getContentPane();
        lp.removeAll();
        frame.setContentPane(c);
        lp.add(layeredBg, Integer.valueOf(2));
        lp.add(layered, Integer.valueOf(3));

        System.out.println(lp.getIndexOf(layered));
        System.out.println(lp.getIndexOf(layeredBg));

        frame.repaint();
        frame.setVisible(true);
    }

    //get layered Pane
    public static Dimension getFrameSize() {
        return frame.getSize();
    }
}