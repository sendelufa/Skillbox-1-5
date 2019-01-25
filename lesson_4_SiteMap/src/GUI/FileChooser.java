/**
 * Project SiteMap.SiteMap
 * Created by Shibkov Konstantin on 18.01.2019.
 */
package GUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileChooser {
    static private FileNameExtensionFilter fileFilter;
    static private File fileChosen;


    static public File chooseFile(FileNameExtensionFilter ff) {
        fileFilter = ff;
        //вызываем диалоговое окно для сохранения результата в файл
        JFileChooser fileChooser = new JFileChooser();
        //настройка выбора: только файлы
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setDialogTitle("Выбор файла для сохранения SiteMap.SiteMap");
        //настройка фильтра файлов
        fileChooser.setFileFilter(fileFilter);
        //вызываем диалоговое окно
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            fileChosen = fileChooser.getSelectedFile();
            return setFileExtension();
        }

        return null;
    }

    //проверяем - если на конце файла расширение, если нет - добавляем
    static private File setFileExtension() {
        return fileChosen.getPath().matches("^.*\\."+ fileFilter.getExtensions()[0] + "$") ? fileChosen :
                new File(fileChosen.getPath() + "." + fileFilter.getExtensions()[0]);
    }
}
