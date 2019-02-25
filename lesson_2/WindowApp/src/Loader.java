import javax.swing.*;
import java.awt.*;

/**
 * Project WindowApp
 * Created by Shibkov Konstantin on 20.12.2018.
 */

public class Loader {
    public static void main(String[] args) {
        int width = 800; //длина X окна
        int height = 600; //длина Y окна

        JFrame frame = new JFrame();

        //устанавливаем минимальный оазмер окна
        frame.setMinimumSize(new Dimension(600, 400));

        //добавляем список надпись - поле ввода
        MainForm rootForm = new MainForm();
        frame.setContentPane(rootForm.getRootPanel());

        frame.setSize(width, height);
        frame.setTitle("WindowApp - " + width + "x" + height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); //выравниваем окно по центру экрана
        frame.setVisible(true);


    }
}