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

public class ChatForm {
    //inner params
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    private JPanel mainPanel;
    private JPanel panelHeadline;
    //Headline of Form
    private JPanel pnlBtnExit;
    private JPanel pnlBtnMinimize;
    private JPanel pnlMicroLogo;
    private JPanel pnlTitleBarUserPic;
    private JPanel pnlTitleBarSettings;
    private JPanel pnlChatsTitle;
    private JTextPane поискTextPane;
    private JPanel pnlContanctsId1;
    private JPanel pnlUserPhoto1;
    private JPanel pnlUserPhoto2;
    private JPanel pnlChatList;
    private JPanel pnlSearch;
    private JPanel pnlContactList;
    private JPanel pmlList;
    private JPanel pnlNewChat;
    private JPanel pnlMainCurrentChat;
    private JPanel pnlCurrentChatUser;
    private JPanel pnlCurrentChatUserPhoto;
    private JPanel pnlCurrentChatUserEdit;
    private JPanel pnlTextInLeft;
    private JPanel pnlTextInCenter;
    private JPanel pnlTextInRight;
    private JPanel pnlMsgOutTop;
    private JPanel pnlMsgOutBottom;
    private JPanel pnlMsgTip;
    private JTextPane textPane1;
    //Resources - Images
    private BufferedImage microLogo;
    private BufferedImage imgHeadClose;
    private BufferedImage imgHeadMin;
    private BufferedImage imgTitleBarUserPic;
    private BufferedImage imgTitleBarSettings;
    private BufferedImage imgChatsTitle;
    private BufferedImage imgUserPhoto1;
    private BufferedImage imgUserPhoto2;
    private BufferedImage imgNewChat;
    private BufferedImage imgCurrentChatUserEdit;
    private BufferedImage imgTextInLeft;
    private BufferedImage imgTextInCenter;
    private BufferedImage imgTextInRight;
    private BufferedImage imgMsgOutTop;
    private BufferedImage imgMsgOutBottom;
    private BufferedImage imgMsgTip;


    public ChatForm() {
        try {
            imgHeadClose = ImageIO.read(new File("res/img/icon-close.png"));
            imgHeadMin = ImageIO.read(new File("res/img/icon-hide.png"));
            microLogo = ImageIO.read(new File("res/img/logo-micro.png"));
            imgTitleBarUserPic = ImageIO.read(new File("res/img/mask-blue-mini.png"));
            imgTitleBarSettings = ImageIO.read(new File("res/img/icon-settings.png"));
            imgChatsTitle = ImageIO.read(new File("res/img/icon-search.png"));
            imgUserPhoto1 = ImageIO.read(new File("res/img/mask-white-online.png"));
            imgUserPhoto2 = ImageIO.read(new File("res/img/mask-gray-online.png"));
            imgNewChat = ImageIO.read(new File("res/img/icon-plus.png"));
            imgCurrentChatUserEdit = ImageIO.read(new File("res/img/icon-edit.png"));
            imgTextInLeft = ImageIO.read(new File("res/img/text_in_left.png"));
            imgTextInCenter = ImageIO.read(new File("res/img/text_in_center.png"));
            imgTextInRight = ImageIO.read(new File("res/img/button-send.png"));
            imgMsgOutTop = ImageIO.read(new File("res/img/message-out-top.png"));
            imgMsgOutBottom = ImageIO.read(new File("res/img/message-out-bottom.png"));
            imgMsgTip = ImageIO.read(new File("res/img/message-out-right.png"));
        } catch (IOException e) {
            System.err.println("Неудалось загрузить картинки!");
            e.printStackTrace();
        }

        //add Adapter to Drag window over the screen
        Loader.MouseAdapterDrag(getPanelHeadline());


        // Configure the scroll bar policy

        //Set Fonts
       /* try {
            lblDescription.setFont(Loader.getMainFont(14));
            lblBtnSend.setFont(Loader.getMainFont(30));
            txtSurname.setFont(Loader.getMainFont(36));
            txtName.setFont(Loader.getMainFont(36));
        } catch (FontFormatException e) {

        } catch (IOException e) {

        }*/

        //make Frame resizable from borders with class ComponentResizer
        Loader.makeFrameResizable();

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
        pnlTitleBarSettings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                try {
                    ProfileChangeData GlassPanel = new ProfileChangeData(Loader.getFrameSize(), "Настройки профиля");
                    Loader.frameSetLayered(GlassPanel.getForm(), GlassPanel.getBgPanel());
                    Loader.repaintFrame();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (FontFormatException e1) {
                    e1.printStackTrace();
                }
            }
        });

        pnlNewChat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                try {
                    ProfileAddContact GlassPanel = new ProfileAddContact(Loader.getFrameSize(), "Добавить пользователя");
                    Loader.frameSetLayered(GlassPanel.getForm(), GlassPanel.getBgPanel());
                    Loader.repaintFrame();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (FontFormatException e1) {
                    e1.printStackTrace();
                }
            }
        });

        pnlCurrentChatUserEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                try {
                    ProfileChangeContactData GlassPanel = new ProfileChangeContactData(Loader.getFrameSize(), "Изменить пользователя");
                    Loader.frameSetLayered(GlassPanel.getForm(), GlassPanel.getBgPanel());
                    Loader.repaintFrame();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (FontFormatException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public static JPanel addComponentsToPane(Container pane) {
        JPanel panel;
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        if (shouldFill) {
            // натуральная высота, максимальная ширина
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        if (shouldWeightX) {
            c.weightx = 0.5;
        }


        panel = new JPanel();
        //panel.setPreferredSize(new Dimension(500));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAutoscrolls(true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       // установить первоночальный размер кнопки
        c.weighty = 1.0;   // установить отступ
        c.anchor = GridBagConstraints.PAGE_END; // установить кнопку в конец окна
        c.insets = new Insets(10, 0, 0, 0);  // поставить заглушку
        c.gridx = 1;       // выравнять компонент по Button 2
        c.gridwidth = 2;   // установить в 2 колонку
        c.gridy = 2;       // и 3 столбец
        pane.add(panel, c);
        return panel;

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    //Custom UI components create
    private void createUIComponents() {

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

        pnlMicroLogo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Draw Image on panel
                g.drawImage(microLogo, 5, 5, null);
            }
        };

        pnlTitleBarUserPic = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Draw Image on panel
                g.drawImage(imgTitleBarUserPic, 0, 10, null);
            }
        };

        pnlTitleBarSettings = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Draw Image on panel
                g.drawImage(imgTitleBarSettings, 0, 14, null);
            }
        };

        pnlChatsTitle = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Draw Image on panel
                g.drawImage(imgChatsTitle, 10, 10, null);
            }
        };

        pnlUserPhoto1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Draw Image on panel
                g.drawImage(imgUserPhoto1, 9, 14, null);
            }
        };

        pnlUserPhoto2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Draw Image on panel
                g.drawImage(imgUserPhoto2, 9, 14, null);
            }
        };

        pnlNewChat = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Draw Image on panel
                g.drawImage(imgNewChat, 20, 0, null);
            }
        };

        pnlCurrentChatUserPhoto = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Draw Image on panel
                g.drawImage(imgUserPhoto1, 0, 0, 35, 35, null);
            }
        };

        pnlCurrentChatUserEdit = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Draw Image on panel
                g.drawImage(imgCurrentChatUserEdit, 0, 0, null);
            }
        };

        pnlTextInLeft = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Draw Image on panel
                g.drawImage(imgTextInLeft, 27, 5, null);
            }
        };

        pnlTextInCenter = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Draw Image on panel
                g.drawImage(imgTextInCenter, 0, 5, 1920, 45, null);
            }
        };

        pnlTextInRight = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Draw Image on panel
                g.drawImage(imgTextInRight, 0, 5, null);
            }
        };

        pnlMsgOutTop = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Draw Image on panel
                g.drawImage(imgMsgOutTop, 0, 0, null);
            }
        };

        pnlMsgOutBottom = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Draw Image on panel
                g.drawImage(imgMsgOutBottom, 0, 0, null);
            }
        };

        pnlMsgTip = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                //Draw Image on panel
                g.drawImage(imgMsgTip, 0, 20, null);
            }
        };
    }

    public JPanel getPanelHeadline() {
        return panelHeadline;
    }
}
