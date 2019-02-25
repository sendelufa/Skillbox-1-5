/**
 * Project WindowApp
 * Created by Shibkov Konstantin on 21.12.2018.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MainForm {
    private JPanel rootPanel;
    private JLabel lblSurname;
    private JTextField txfSurname;
    private JLabel lblName;
    private JTextField txfName;
    private JLabel lblPatron;
    private JTextField txfPatron;
    private JButton btnSend;


    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JButton getBtnSend() {
        return btnSend;
    }

    public JTextField getTxfName() {
        return txfName;
    }

    public JTextField getTxfSurname() {
        return txfSurname;
    }

    public JTextField getTxfPatron() {
        return txfPatron;
    }
}
