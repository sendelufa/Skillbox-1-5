import javax.swing.*;
import java.awt.*;

/**
 * Project lesson_7
 * Created by Shibkov Konstantin on 24.12.2018.
 */

public class Loader {

    static JFrame frame = new JFrame();
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        int width = 800; //длина X окна
        int height = 600; //длина Y окна


        ContactList contactListForm = new ContactList();
        SignUp signUpForm = new SignUp();
        EnterCode enterCodeForm = new EnterCode();
        //базовая панель
        JPanel rootPanel = signUpForm.getRootPanel();

        frame.setMinimumSize(new Dimension(600, 400));
        frame.setContentPane(rootPanel);
        frame.setSize(width, height);
        frame.setTitle("Javagramm");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); //выравниваем окно по центру экрана
        frame.setVisible(true);
    }
}
