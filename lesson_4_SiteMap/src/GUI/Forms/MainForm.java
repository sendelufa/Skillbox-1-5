package GUI.Forms;

import GUI.FileChooser;
import SiteMap.LinkParser;
import sendel.utils.TimerElapsed;
import sendel.utils.Utils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * Project SiteMap.SiteMap
 * Created by Shibkov Konstantin on 18.01.2019.
 */

public class MainForm {
    private TimerElapsed timer;
    private File outputFile = null;
    //Количество потоков  = количеству поток физического процессора (для win)
    private int numThreads = Runtime.getRuntime().availableProcessors() * 2;
    //int numThreads = 3;
    private ArrayList<LinkParser> lpThreads = new ArrayList<>(numThreads);
    private JPanel mainPanel;
    private JTextField txtSiteURL;
    private JTextArea txtLog;
    private JButton btnStartStop;
    private JButton btnPause;
    private JLabel lblInfoPagesDone;
    private JLabel lblInfoTimePassed;
    private JButton btnChooseOutputFile;
    private JLabel lblFileChosen;
    private JPanel pnlLogScroll;
    private JButton btnClearLog;
    private boolean isParseWorking = false;

    public MainForm() {
        //прокурчиваем txtLog всегда к нижней строке используя DefaultCaret
        DefaultCaret caret = (DefaultCaret) txtLog.getCaret();
        pnlLogScroll.setOpaque(true);

        //выделяем весь текст при клике на поле, для удобной вставки ссылки
        txtSiteURL.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!txtSiteURL.isFocusOwner()) txtSiteURL.selectAll();
            }
        });
        btnChooseOutputFile.addActionListener(e -> {
            //Запрашиваем новый файл через диалоговое окно SAVE
            File chosenFile = FileChooser.chooseFile(new FileNameExtensionFilter("Text File (.txt)", "txt"));

            //Если файл не выбран - не меняем файл для сохранения
            if (chosenFile != null) outputFile = chosenFile;

            //выводим информацию о результате выбора файла
            getLblFileChosen().setText(outputFile != null ? outputFile.getPath() : "файл не выбран");
            //если файл выбран - активируем кнопку старт
            getBtnStartStop().setEnabled(outputFile != null);
        });
        btnStartStop.addActionListener(e -> {
            if (!isParseWorking) {
                //прокурчиваем txtLog всегда к нижней строке используя DefaultCaret
                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
                //флаг работы парсера
                isParseWorking = true;
                //меняем надпись на кнопке
                getBtnStartStop().setText("STOP");
                getBtnPause().setEnabled(true);
                //читаем адрес сайта из текстового поля
                String inputURL = getTxtSiteURL().getText().trim();
                inputURL = Utils.addTailSlash(inputURL);
                getTxtSiteURL().setText(inputURL);
                //если URL верный по формату - запускаем парсинг
                if (Utils.isValidURL(inputURL)) {


                    lpThreads = new ArrayList<>(numThreads);
                    LinkParser.resetParser(inputURL, Utils.getHost(inputURL), getForm());

                    for (int i = 0; i < numThreads; i++) {
                        lpThreads.add(new LinkParser());
                    }

                    for (int i = 0; i < numThreads; i++) {
                        lpThreads.get(i).setName("ParseThread-" + i);
                        lpThreads.get(i).start();
                    }

                    //запускаем таймер для подсчета времени
                    timer = new TimerElapsed(getForm());
                    timer.start();

                    //поток проверяет все ли потоки парсинга завершены
                    Thread statusThread = new Thread(() -> {
                        int countFinish = -1;
                        while (countFinish != numThreads) {
                            countFinish = 0;
                            for (int i = 0; i < numThreads; i++) {
                                if (!lpThreads.get(i).isWorking()) countFinish++;

                            }
                            try {
                                sleep(500);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }

                        }
                        if (!LinkParser.isStop()) {
                            LinkParser.endAllThreadsRegular(true);
                        }
                        timer.interrupt();
                        getBtnPause().setEnabled(false);
                        getBtnStartStop().setText("START");
                        getBtnStartStop().setEnabled(true);
                        isParseWorking = false;

                        if (LinkParser.isRegularStop()) {
                            writeLog(Utils.writeSiteMapToFile(LinkParser.getLinksMap(), outputFile));
                        } else {
                            writeLog("========================================");
                            writeLog("  Выполнение прервано, файл не записан!");
                            writeLog("========================================");
                        }
                        caret.setUpdatePolicy(DefaultCaret.UPDATE_WHEN_ON_EDT);
                    });

                    statusThread.start();

                } else {
                    getTxtLog().setText("неверный URL");
                }
            } else {
                //завершаем все потоки

                getBtnStartStop().setEnabled(false);
                getBtnPause().setEnabled(false);
                isParseWorking = false;
                LinkParser.endAllThreads();
                timer.interrupt();
                writeLog("\t -> Останавливается парсинг, пожалуйста ждите...");

            }
        });
        btnClearLog.addActionListener(e -> getTxtLog().setText(""));
        btnPause.addActionListener(e -> {
            if (LinkParser.isPause()) {
                writeLog("\t -> Парсинг продолжен");
                getBtnPause().setText("Pause");
                for (int i = 0; i < numThreads; i++) {
                    LinkParser.AllPlay();
                }
                timer.Play();
            } else {

                writeLog("\t -> Парсинг ставится на паузу...");
                getBtnPause().setText("Play");
                getBtnPause().setEnabled(false);
                getBtnStartStop().setEnabled(false);
                LinkParser.AllPause();
                //если вставли на паузу - активируем кнопки
                boolean AllInPause = false;
                while (!AllInPause) {
                    AllInPause = true;
                    for (LinkParser lp : lpThreads) {
                        if (!lp.isStatePause()) {
                            AllInPause = false;
                        }
                    }

                   /* for (int i = 0; i < lpThreads.size(); i++) {
                        if (!lpThreads.get(i).isStatePause()) {
                            AllInPause = false;
                        }
                    }*/

                    try {
                        sleep(50);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                }

                writeLog("\t -> Парсинг поставлен на паузу. Для продолжения нажмите Play");
                getBtnPause().setEnabled(true);
                getBtnStartStop().setEnabled(true);

                timer.Pause();
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getBtnChooseOutputFile() {
        return btnChooseOutputFile;
    }

    private JLabel getLblFileChosen() {
        return lblFileChosen;
    }

    private JButton getBtnStartStop() {
        return btnStartStop;
    }

    private JTextField getTxtSiteURL() {
        return txtSiteURL;
    }

    private JTextArea getTxtLog() {
        return txtLog;
    }

    //возвращаем класс сам себя - для доступа из слушателей
    private MainForm getForm() {
        return this;
    }

    private JButton getBtnPause() {
        return btnPause;
    }

    public void writeLog(String log) {
        getTxtLog().append(log + "\n");
    }

    public void writeNumberLinks(String str) {
        lblInfoPagesDone.setText(str);
    }

    public void writeTimeElapsed(String str) {
        lblInfoTimePassed.setText(str);
    }

    //обнуляем форму
    public void resetForm() {
        btnStartStop.setEnabled(false);
        btnPause.setEnabled(false);
    }

}
