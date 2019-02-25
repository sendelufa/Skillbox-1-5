import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project WindowApp
 * Created by Shibkov Konstantin on 21.12.2018.
 */

public class Loader {

    static JFrame frame = new JFrame();
    //извлекаем формы
    static MainForm rootForm = new MainForm();
    static SecondForm secondForm = new SecondForm();

    static JPanel rootPanel = rootForm.getRootPanel();

    //текстовые поля первой формы
    static JTextField txtName = rootForm.getTxfName();
    static JTextField txtSurName = rootForm.getTxfSurname();
    static JTextField txtPatron = rootForm.getTxfPatron();

    //проверка на ввод верных символов в ФИО
    static final String SNP = "^[а-яA-яЁё]*$";

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.updateComponentTreeUI(frame);
        SwingUtilities.updateComponentTreeUI(secondForm.getRootPanel());
        SwingUtilities.updateComponentTreeUI(rootPanel);

        int width = 800; //длина X окна
        int height = 600; //длина Y окна

        //устанавливаем минимальный оазмер окна
        frame.setMinimumSize(new Dimension(600, 400));

        rootPanel.setFocusable(true);
        frame.setContentPane(rootPanel);
        frame.setSize(width, height);
        frame.setTitle("WindowApp - " + width + "x" + height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); //выравниваем окно по центру экрана
        frame.setVisible(true);

        //слушатель ALT+ENTER или нажатие на кнопку в первом окне
        Action actionListener = actionListenerFirstForm();

        //слушатель ALT+ENTER во втором окне
        Action actionListenerSec = actionListenerSec();


        //обработчик кнопки на форме с тремя полями
        JButton btnSend = rootForm.getBtnSend();
        //обработчик кнопки на форме с одним текстовым полем
        JButton btnSendSecond = secondForm.getBtnSend();
        btnSend.addActionListener(actionListener);
        // KeyStroke соотв сочетанию ALT + ENTER
        KeyStroke KeyAltEnter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.ALT_MASK);
        btnSendSecond.addActionListener(actionListenerSec);


        //работа с сочетанием клавиш ALT+ENTER
        KeyStroke KeyGrAltEnter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.ALT_GRAPH_MASK);
        //Определяем зону срабатывания сочетания
        InputMap inputMap = rootPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = rootPanel.getActionMap();
        inputMap.put(KeyAltEnter, "Action");
        inputMap.put(KeyGrAltEnter, "Action");

        //Определяем зону срабатывания сочетания
        InputMap inputMap2 = secondForm.getRootPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        //добавляем, что выполнять при получении сочетания
        actionMap.put("Action", actionListener);
        ActionMap actionMap2 = secondForm.getRootPanel().getActionMap();
        inputMap2.put(KeyAltEnter, "Action2");
        inputMap2.put(KeyGrAltEnter, "Action2");

        //добавляем, что выполнять при получении сочетания
        actionMap2.put("Action2", actionListenerSec);

    }

    private static void transferTextToSecondForm() {
        secondForm.getTxfNameSurPatron().setText(txtSurName.getText().trim() +
                " " + txtName.getText().trim() + " " + txtPatron.getText().trim());
        frame.remove(rootPanel);
        frame.setContentPane(secondForm.getRootPanel());
        //заполняем значение прогресс бара и настраиваем форму
        secondForm.checkSNP_size();

        frame.setVisible(true);
    }

    //слушатель ALT+ENTER во втором окне
    private static Action actionListenerSec() {
        Action actionListenerSec = new AbstractAction() {
            public void actionPerformed(ActionEvent actionEvent) {
                String[] arrSurNamePatron = secondForm.getTxfNameSurPatron().getText().trim().split(" ");

                //проверка на существование всех элементов в форме с Ф.И.О
                //заполняем текстовые поля основной (первой) формы
                rootForm.getTxfSurname().setText(arrSurNamePatron.length > 0 ? arrSurNamePatron[0] : "");
                rootForm.getTxfName().setText(arrSurNamePatron.length > 1 ? arrSurNamePatron[1] : "");
                rootForm.getTxfPatron().setText(arrSurNamePatron.length > 2 ? arrSurNamePatron[2] : "");
                //меняем Panel и делаем ее видимой
                frame.setContentPane(rootPanel);
            }
        };
        return actionListenerSec;
    }

    //работа с сочетанием клавиш ALT+ENTER в первой форме(окне)
    private static Action actionListenerFirstForm(){
        Action actionListener = new AbstractAction() {
            public void actionPerformed(ActionEvent actionEvent) {
                String warningText = "";
                //формируем сообщение если не заполнены поля Имя или Фамилия
                warningText += txtSurName.getText().trim().equals("") ? "Введите, пожалуйста, Фамилию.\n" : "";
                warningText += txtSurName.getText().trim().matches(SNP) ? "" : "Неверный формат Фамилии\n";
                warningText += txtName.getText().trim().equals("") ? "Введите, пожалуйста, Имя.\n" : "";
                warningText += txtName.getText().trim().matches(SNP) ? "" : "Неверный формат Имени\n";
                warningText += txtPatron.getText().trim().matches(SNP) ? "" : "Неверный формат Отчества\n";

                //если текст предупреждения не пуст - показываем диалоговое окно и устаналиваем фокус на пустое поле
                if (!warningText.equals("")) {
                    JOptionPane.showMessageDialog(rootPanel,
                            warningText,
                            "Не все поля заполнены/ Неверный формат",
                            JOptionPane.WARNING_MESSAGE);
                    if (warningText.lastIndexOf("Фамилию") == -1) {
                        txtName.requestFocus();
                    } else {
                        txtSurName.requestFocus();
                    }

                    if (warningText.lastIndexOf("Фамилии") == -1) {
                        txtName.requestFocus();
                    } else {
                        txtSurName.requestFocus();
                    }
                    return;
                }

                warningText = txtPatron.getText().trim().equals("") ?
                        "Отчество не заполнено.\nПропустить ввод Отчества?" : "";
                if (!warningText.equals("")) {

                    int actionReturn = JOptionPane.showConfirmDialog(rootPanel,
                            warningText,
                            "Предупреждение",
                            JOptionPane.YES_NO_OPTION);
                    if (actionReturn == JOptionPane.YES_OPTION) {
                        transferTextToSecondForm();
                    } else {
                        txtPatron.requestFocus();
                        return;
                    }
                } else {
                    transferTextToSecondForm();
                }


            }
        };
        return actionListener;
    }
}