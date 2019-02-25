import javax.swing.*;
import java.awt.*;

/**
 * Project Javagram
 * Created by Shibkov Konstantin on 02.01.2019.
 */

public class JLayeredPaneTest extends JFrame
{
    private static final long serialVersionUID = 1L;

    public JLayeredPaneTest(String title)
    {
        // создание окна
        super(title);
        // выход при закрытии окна
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // определение многослойной панели
        JLayeredPane lp = getLayeredPane();
        // создание трех фигур
        Figure figure1 = new Figure(Color.red , 0, "Figure popup");
        Figure figure2 = new Figure(Color.blue, 0, "Figure 1");
        Figure figure3 = new Figure(Color.cyan, 1, "Figure 2");
        // определение местоположения фигур в окне
        figure1.setBounds(10, 40, 120, 120);
        figure2.setBounds(60, 120, 160, 180);
        figure3.setBounds(90, 55, 250, 180);
        // добавление фигур в различные слои
        lp.add(figure1, JLayeredPane.POPUP_LAYER  );
        lp.add(figure2, JLayeredPane.PALETTE_LAYER);
        lp.add(figure3, JLayeredPane.PALETTE_LAYER);
        // смена позиции одной из фигур
        lp.setPosition(figure3, 0);
        // определение размера и открытие окна
        setSize(280, 250);
        setUndecorated(true);
        setVisible(true);

    }}

class Figure extends JComponent
{
    private static final long serialVersionUID = 1L;
    private Color color;
    private int type;
    private String text;
    // параметры: цвет и тип фигуры
    Figure(Color color, int type, String text) {
        this.color = color;
        this.type = type;
        this.text = text;
        setOpaque(false);
    }
    public void paintComponent(Graphics g) {
        // прорисовка фигуры
        g.setColor(color);
        switch (type) {
            case 0: g.fillOval(0, 0, 90, 90); break;
            case 1: g.fillRect(0, 0, 130, 80); break;
        }
        g.setColor(Color.yellow);
        g.drawString(text, 10, 35);
    }
}
