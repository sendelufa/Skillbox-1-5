/**
 * Project Javagram
 * Created by Shibkov Konstantin on 03.01.2019.
 */

import forms.MyGlassPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//create BlackGlass panel without content
//Boxlayout with transparent Headline in NORTH
abstract public class LayeredPaneBlackGlass {
    MyGlassPanel panelBg; //skeleton
    JPanel panel;
    JPanel mainContent = new JPanel(); //content with forms
    private JLabel lblTitle = new JLabel(); //title of content
    private BufferedImage imgBlackGlass;

    public LayeredPaneBlackGlass(Dimension frameDim, String title) throws IOException, FontFormatException {

        //Bg panel for glass effect
        panelBg = new MyGlassPanel();
        panelBg.setSize(frameDim);
        panelBg.setOpaque(false);

        //Panel with content
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);
        panel.setSize(frameDim); // Size is needed here, as there is no layout in lp


        //Headline
        JPanel panelNorthTransp = new JPanel();
        panelNorthTransp.setLayout(new FlowLayout());
        panelNorthTransp.setPreferredSize(new Dimension(800, 130));
        panelNorthTransp.setOpaque(false);

        //Title
        lblTitle.setPreferredSize(new Dimension((int)frameDim.getWidth(), 100));
        lblTitle.setSize(new Dimension((int)frameDim.getWidth(), 100));
        lblTitle.setFont(Loader.getMainFont(36));
        lblTitle.setText(title);
        lblTitle.setVerticalAlignment(JLabel.BOTTOM);
        lblTitle.setHorizontalTextPosition(JLabel.CENTER);
        lblTitle.setOpaque(false);
        System.setProperty("TitleColor", "0X00b5ea");
        lblTitle.setForeground(Color.getColor("TitleColor"));

        mainContent.setOpaque(false);//set layout for content
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        //mainContent.setAlignmentX(0.5F);
        //mainContent.add(lblTitle);

        panelNorthTransp.add(lblTitle);

        //construct layouts in skeleton panel
        panel.add(panelNorthTransp, BorderLayout.NORTH);
        panel.add(mainContent, BorderLayout.CENTER);


    }

    public JPanel getForm() {
        return panel;
    }

    public JPanel getBgPanel() {
        return panelBg;
    }
}
