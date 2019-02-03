/**
 * Project SiteMap
 * Created by Shibkov Konstantin on 22.01.2019.
 */
package sendel.utils;

import GUI.Forms.MainForm;

//класс для подсчета врермя выполнения в отдельном потоке
public class TimerElapsed extends Thread {
    long startTime;
    MainForm mainForm;
    long timeElapsed;
    boolean isStop = false;
    boolean isPause = false;

    public TimerElapsed(MainForm mainForm) {
        startTime = System.currentTimeMillis();
        this.mainForm = mainForm;
    }

    @Override
    public void run() {
        currentThread().setName("TimerElapsed");
        for (; ; ) {
            //обработка завершения потока
            if (isStop) break;
            //пройденное время
            timeElapsed = System.currentTimeMillis() - startTime;
            if (isPause) {
                startTime += 100;
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                isStop = true;
            }

            int seconds = (int) (timeElapsed / 1000) % 60;
            String sec = seconds < 10 ? "0" + seconds : String.valueOf(seconds);
            int minutes = (int) ((timeElapsed / (1000 * 60)) % 60);
            mainForm.writeTimeElapsed(minutes + ":" + sec);
        }
        System.out.println("Завершение потока таймера = " + currentThread().getName());
    }

    public void Pause(){
        isPause = true;
    }
    public void Play(){
        isPause = false;
    }
}
