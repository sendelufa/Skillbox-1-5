import javax.swing.*;

public class Loader {
    public static void main(String[] args) {
        int width = 800; //длина X окна
        int height = 600; //длина Y окна

        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setTitle("WindowApp - " + width + "x" + height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); //выравниваем окно по центру экрана
        frame.setVisible(true);


    }
}