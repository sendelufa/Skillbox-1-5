package StopWatch;

import GUI.Form.StopWatchForm;
import GUI.TxtConst;

import javax.swing.*;
import java.awt.*;

/**
 * Project StopWatch.StopWatch
 * Created by Shibkov Konstantin on 14.01.2019.
 */

public class StopWatch extends Thread {
    private JButton btnStart;
    private JLabel lblDigits;
    private StopWatchForm form;
    private boolean isPaused;
    private boolean quickExitBlink;
    private boolean exit;


    public StopWatch(StopWatchForm form) {
        this.form = form;
        this.btnStart = form.getBtnStart();
        this.lblDigits = form.getLblDigits();
    }

    @Override
    public void run() {
        boolean isColored = true;
        quickExitBlink = false;
        exit = false;
        setPaused(false);
        btnStart.setText(TxtConst.PAUSE);
        for (int i = 0; ; i++) {

            if (exit) break;
            lblDigits.setText(formatDigits(i));
            try {
                sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Завершение потока");
            }
            if (isPaused()) {
                i--;
                //моргаем цветом цифр пока стоит на паузе
                Color c = isColored ? Color.BLACK : Color.ORANGE;
                isColored = !isColored;
                lblDigits.setForeground(c);
                try {
                    sleep(300);
                } catch (InterruptedException e) {
                    //устранение задержки после снятие с паузы
                    if (quickExitBlink) {
                        System.out.println("Снятие потока с паузы во время моргания");
                        quickExitBlink = false;
                        continue;
                    }
                }
            } else {
                isColored = true;
                lblDigits.setForeground(Color.BLACK);
            }
        }
        //обнуляем интерфейс при выходе из потока
        lblDigits.setText("00:00.00");
        btnStart.setText(TxtConst.START);
        lblDigits.setForeground(Color.BLACK);
    }

    //формат секундомера, на входе миллисекунды
    private String formatDigits(int ms) {
        String strMs = String.valueOf(ms);
        int lengthStrMs = strMs.length();

        //в списке храним результат
        String[] arrDigits = new String[]{"00", "00", "00"};

        //проверяем у нас строка длинее 3, если не длинее значит у нас только миллисекунды
        if (lengthStrMs < 3) {
            arrDigits[0] = strMs;
        } else {
            arrDigits[0] = strMs.substring(lengthStrMs - 2); //сантисекунды
            int secAll = Integer.parseInt(strMs.substring(0, lengthStrMs - 2));
            arrDigits[1] = String.valueOf(secAll % 60); //секунды
            arrDigits[2] = String.valueOf(secAll / 60); //минуты
        }

        //дополняем нулями если число короче 2 по длине строки
        for (int i = 0; i < arrDigits.length; i++) {
            if (arrDigits[i].length() < 2) arrDigits[i] = "0" + arrDigits[i];
        }

        return arrDigits[2] + ":" + arrDigits[1] + "." + arrDigits[0];

    }


    public StopWatchForm getForm() {
        return form;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
        if (isPaused) {
            form.getBtnStart().setText(TxtConst.CONTINUE);
        } else {
            form.getBtnStart().setText(TxtConst.PAUSE);
            exitOnBlink();
        }
    }

    //устранение задержки после снятие с паузы
    private void exitOnBlink() {
        quickExitBlink = true;
        interrupt();
    }

    //выход из потока сменой флага
    public void exit() {
        exit = true;
    }
}
