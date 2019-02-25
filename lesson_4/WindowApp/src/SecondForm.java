/**
 * Project WindowApp
 * Created by Shibkov Konstantin on 21.12.2018.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SecondForm {
    public final int MAX_CHARS_IN_NSP = 25;
    private JPanel rootPanel;
    private JLabel lblNameSurPatron;
    private JTextField txfNameSurPatron;
    private JButton btnSend;
    private JProgressBar prBarSNP;
    private JLabel lblWarn;

    public SecondForm() {
        //первоночальное состояние формы
        lblWarn.setVisible(false);
        prBarSNP.setValue((int) (( ((double)getSNPLenght() / MAX_CHARS_IN_NSP) * 100)));


        //слушаем нажатия на клавиши
        txfNameSurPatron.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                checkSNP_size();

            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JButton getBtnSend() {
        return btnSend;
    }

    public JTextField getTxfNameSurPatron() {
        return txfNameSurPatron;
    }

    //возвращаем длину текста в текстовом поле
    public int getSNPLenght() {
        return txfNameSurPatron.getText().length() + 1;
    }

    public JProgressBar getPrBarSNP() {
        return prBarSNP;
    }

    //проверка превышения длины текста
    public boolean isMaxLenght(){
        return getSNPLenght() > MAX_CHARS_IN_NSP;
    }

    //меняем состояние элементов при вводе текста и используем для перехода на эту форму

    public void checkSNP_size(){
        //меняем прогресс бара в зависимости от заполнения
        prBarSNP.setValue((int) (( ((double)getSNPLenght() / MAX_CHARS_IN_NSP) * 100)));
        Color prBarColor = isMaxLenght() ? java.awt.Color.RED : java.awt.Color.GREEN;
        prBarSNP.setForeground(prBarColor);

        prBarSNP.setString(getSNPLenght() + "/" + MAX_CHARS_IN_NSP + " символа");
        //пишем предупреждение если превышено кол-во знаков
        boolean isWarnTextLenght = isMaxLenght() ? true : false;
        if (isWarnTextLenght) {
            //показываем предупреждение и блокируем кнопку Отправить
            lblWarn.setText("Вы ввели " + (getSNPLenght() - MAX_CHARS_IN_NSP) + " лишних символа");

            btnSend.setEnabled(false);
            lblWarn.setVisible(true);
        } else {
            //отключаем предупреждение и разблокируем кнопку Отправить
            btnSend.setEnabled(true);
            lblWarn.setVisible(false);
        }
    }

}
