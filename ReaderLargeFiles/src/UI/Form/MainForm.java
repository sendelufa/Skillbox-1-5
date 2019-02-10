/**
 * Project ReaderLargeFiles
 * Created by Shibkov Konstantin on 06.02.2019.
 */
package UI.Form;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import File.*;

public class MainForm extends JFrame{
    private JPanel rootPanel;
    private JButton btnSelectFile;
    private JLabel lblFilePath;
    private JTextArea txtFileView;
    private JButton ForwardToBeginFile;
    private JButton btnForwardToEndFile;
    private JButton btnPrev_100;
    private JButton btnForward_100;
    private JButton btnPrev_10;
    private JButton btnForward_10;
    private JTextField textField1;
    private JButton перейтиButton;
    private JScrollBar scrBarTextView;

    private ReaderLargeFiles reader;


    public MainForm() {


        //тест
        try {
            reader = new ReaderLargeFiles(new File("E:\\Skillbox\\Java\\M13\\.answer\\res\\numbers.txt"), txtFileView, scrBarTextView);
            setScrollMaxValue();
            System.out.println(scrBarTextView.getMaximum());
            reader.movePointer(0);
            reader.readFileLines(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //тест


        btnSelectFile.addActionListener(e -> {

            File inputFile = FileChooser.OpenFile();

            if (inputFile != null) {
                lblFilePath.setText(inputFile.getPath());
                try {
                    reader = new ReaderLargeFiles(inputFile, txtFileView, scrBarTextView);
                    setScrollMaxValue();
                    reader.movePointer(0);
                    reader.readFileLines(0);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }


        });

        ForwardToBeginFile.addActionListener(e -> {

            reader.movePointer(0);
            reader.readFileLines(0);

        });

        btnForwardToEndFile.addActionListener(e -> reader.readFileLines(1));
        btnForward_100.addActionListener(e -> reader.readFileLines(100));
        btnForward_10.addActionListener(e -> reader.readFileLines(10));
        //обработка скроллинга мыши
        txtFileView.addMouseWheelListener(e -> {
            int linesToScroll = e.getWheelRotation();
            reader.readFileLines(linesToScroll);
        });
        //обработка изменения scrollbara
        scrBarTextView.addAdjustmentListener(e -> {
            //System.out.println(e.getAdjustmentType());
            if (e.getAdjustmentType() < 5) {
                reader.movePointer((long) e.getValue());
                reader.readFileLines(1);
            }
            //System.out.println(reader.getFile().startPointer);


        });


    }

    //обнуляем форму
    public void resetForm() {

    }

    public JLabel getLblFilePath() {
        return lblFilePath;
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JButton getBtnSelectFile() {
        return btnSelectFile;
    }

    private void setScrollMaxValue() throws IOException {
        long fileLenght = reader.getFileLength();
        if (fileLenght > Integer.MAX_VALUE) {
            fileLenght = Integer.MAX_VALUE;
        }
        scrBarTextView.setValue((int)fileLenght - 20);

    }


}
