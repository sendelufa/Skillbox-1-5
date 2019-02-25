/**
 * Project Javagram
 * Created by Shibkov Konstantin on 03.01.2019.
 */
package forms;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyGlassPanel extends JPanel {
    private BufferedImage imgBlackGlass;
    public MyGlassPanel(){
        super();
        try {
            imgBlackGlass = ImageIO.read(new File("res/img/bg_blackglass.png"));
        } catch (IOException e) {
            System.err.println("Неудалось загрузить картинки для LayeredPaneBlackGlass!");
            e.printStackTrace();
        }
    }
    public void paint(Graphics g){
        super.paintComponent(g);
        g.drawImage(imgBlackGlass,0,30,2560,1440,this);
    }
}
