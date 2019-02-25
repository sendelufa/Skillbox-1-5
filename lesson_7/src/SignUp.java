/**
 * Project lesson_7
 * Created by Shibkov Konstantin on 24.12.2018.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp {
    private JTextPane lblPhone;
    private JTextField txtPhone;
    private JLabel lblTitle;
    private JButton btnSignIn;
    private JPanel rootPanel;

    public SignUp() {
        btnSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loader.frame.setContentPane(new EnterCode().getPanelCode());
                Loader.frame.setVisible(true);
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
