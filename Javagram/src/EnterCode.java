/**
 * Project Javagram
 * Created by Shibkov Konstantin on 24.12.2018.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EnterCode {
    private JPanel mainPanel;
    private JButton btnMinimize;
    private JButton btnExit;
    private JPanel panelLogo;
    private JTextPane lbpDescPhone;
    private JButton btnSendCode;
    private JPanel pnlBtnSend;
    private JPanel panelHeadline;
    //Headline of Form
    private JPanel pnlBtnExit;
    private JPanel pnlBtnMinimize;
    private JPanel pnlIcoPhone;
    private JTextPane txtCode;
    private JLabel lblBtnSend;
    private JLabel lblDescription;
    private JLabel lblPhoneNumber;
    //Resources - Images
    private BufferedImage bg;
    private BufferedImage logo;
    private BufferedImage imgBtn;
    private BufferedImage imgHeadClose;
    private BufferedImage imgHeadMin;
    private BufferedImage imgIcoPhone;
    //inner params


    public EnterCode() {
        try {
            bg = ImageIO.read(new File("res/img/background.jpg"));
            logo = ImageIO.read(new File("res/img/logo.png"));
            imgBtn = ImageIO.read(new File("res/img/button-background.png"));
            imgHeadClose = ImageIO.read(new File("res/img/icon-close.png"));
            imgHeadMin = ImageIO.read(new File("res/img/icon-hide.png"));
            imgIcoPhone = ImageIO.read(new File("res/img/icon-lock.png"));
        } catch (IOException e) {
            System.err.println("Неудалось загрузить картинки!");
            e.printStackTrace();
        }

        //add Adapter to Drag window over the screen
        Loader.MouseAdapterDrag(getPanelHeadline());

        //Set Fonts
        try {
            lblDescription.setFont(Loader.getMainFont(14));
            lblBtnSend.setFont(Loader.getMainFont(30));
            lblPhoneNumber.setFont(Loader.getMainFont(40));
        } catch (FontFormatException e) {

        } catch (IOException e) {

        }

        //change Layout to mainPanel fo Y axis position
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        //Add Listener to Headline Buttons
        pnlBtnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.exit(1);
            }
        });
        pnlBtnMinimize.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Loader.minimizeFrame();
            }
        });

        //temp
        lblBtnSend.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Loader.frameSetContent(new SetUserInfo().getMainPanel());

            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    //Custom UI components create
    private void createUIComponents() {


        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Draw Image on panel
                g.drawImage(bg, 0, 0, 800, 600, null);
            }
        };

        panelLogo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(logo, 0, 0, null);
            }
        };

        btnSendCode = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imgBtn, 0, 0, null);
            }
        };

        pnlBtnSend = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imgBtn, 0, 3, null);
            }
        };

        pnlBtnExit = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imgHeadClose, 0, 0, null);
            }
        };

        pnlBtnMinimize = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imgHeadMin, 0, 0, null);
            }
        };

        pnlIcoPhone = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imgIcoPhone, 0, 0, null);
            }
        };


    }


    public JPanel getPanelHeadline() {
        return panelHeadline;
    }
}
