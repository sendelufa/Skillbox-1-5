package sendel.utils;

import GUI.Forms.MainForm;

/**
 * Project SiteMap
 * Created by Shibkov Konstantin on 22.01.2019.
 */

//класс для подсчета врермя выполнения в отдельном потоке
public class TimerElapsed extends Thread {
    private long startTime;
    private MainForm mainForm;
    private long timeElapsed;
    private boolean isStop = false;
    private boolean isPause = false;

    public TimerElapsed(MainForm mainForm) {
        startTime = System.currentTimeMillis();
        this.mainForm = mainForm;
    }

    @Override
    public void run() {
        currentThread().setName("TimerElapsed");
        while (!isStop){
            /*//обработка завершения потока
            if (isStop) break;*/
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
            int hours = (int) ((timeElapsed / (1000 * 60*60)) % 60);
            mainForm.writeTimeElapsed(hours + ":" + minutes + ":" + sec);
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
