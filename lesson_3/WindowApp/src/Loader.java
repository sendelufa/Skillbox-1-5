import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Project WindowApp
 * Created by Shibkov Konstantin on 21.12.2018.
 */

public class Loader {
    public static void main(String[] args) {
        int width = 800; //длина X окна
        int height = 600; //длина Y окна

        JFrame frame = new JFrame();

        //устанавливаем минимальный оазмер окна
        frame.setMinimumSize(new Dimension(600, 400));

        //извлекаем формы
        MainForm rootForm = new MainForm();
        JPanel rootPanel = rootForm.getRootPanel();
        rootPanel.setFocusable(true);

        SecondForm secondForm = new SecondForm();

        frame.setContentPane(rootPanel);

        frame.setSize(width, height);
        frame.setTitle("WindowApp - " + width + "x" + height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); //выравниваем окно по центру экрана
        frame.setVisible(true);

        //текстовые поля первой формы
        JTextField txtName = rootForm.getTxfName();
        JTextField txtSurName = rootForm.getTxfSurname();
        JTextField txtPatron = rootForm.getTxfPatron();

        //слушатель ALT+ENTER или нажатие на кнопку в первом окне
        Action actionListener = new AbstractAction() {
            public void actionPerformed(ActionEvent actionEvent) {
                String warningText = "";
                //формируем сообщение если не заполнены поля Имя или Фамилия
                warningText += txtSurName.getText().trim().equals("") ? "Введите, пожалуйста, Фамилию.\n" : "";
                warningText += txtName.getText().trim().equals("") ? "Введите, пожалуйста, Имя." : "";

                //если текст предупреждения не пуст - показываем диалоговое окно и устаналиваем фокус на пустое поле
                if (!warningText.equals("")) {
                    JOptionPane.showMessageDialog(rootPanel,
                            warningText,
                            "Не все поля заполнены",
                            JOptionPane.WARNING_MESSAGE);
                    if (warningText.lastIndexOf("Фамилию") == -1) {
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
                        secondForm.getTxfNameSurPatron().setText(txtSurName.getText().trim() +
                                " " + txtName.getText().trim() + " " + txtPatron.getText().trim());
                        frame.remove(rootPanel);
                        frame.setContentPane(secondForm.getRootPanel());
                        frame.setVisible(true);
                    } else {
                        txtPatron.requestFocus();
                        return;
                    }
                }


            }};

            //слушатель ALT+ENTER во втором окне
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
        inputMap.put(KeyAltEnter,"Action");
        inputMap.put(KeyGrAltEnter,"Action");
            //Определяем зону срабатывания сочетания
            InputMap inputMap2 = secondForm.getRootPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            //добавляем, что выполнять при получении сочетания
        actionMap.put("Action",actionListener);
            ActionMap actionMap2 = secondForm.getRootPanel().getActionMap();
        inputMap2.put(KeyAltEnter,"Action2");
        inputMap2.put(KeyGrAltEnter,"Action2");

            //добавляем, что выполнять при получении сочетания
        actionMap2.put("Action2",actionListenerSec);
//работа с сочетанием клавиш ALT+ENTER

        }
    }