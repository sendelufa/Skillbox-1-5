/**
 * Project lesson_7
 * Created by Shibkov Konstantin on 24.12.2018.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterCode {
    private JTextPane lblPhone;
    private JTextField txtPhone;
    private JLabel lblTitle;
    private JButton btnSignIn;
    private JPanel panelCode;

    public EnterCode() {
        btnSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loader.frame.setContentPane(new ContactList().getPanel1());
                Loader.frame.setVisible(true);
            }
        });
    }

    public JPanel getPanelCode() {
        return panelCode;
    }
}
