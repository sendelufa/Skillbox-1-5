import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Project Calendar
 * Created by Shibkov Konstantin on 16.12.2018.
 */

public class Loader {
    public static void main(String[] args) throws IOException {
        /*- Написать программу, которая будет распечатывать Ваш возраст, соответствующий ему день рождения и
        день недели. до текущего момента времени.

                Формат:
        0 - 13.02.1989 - Mon
        1 - 13.02.1990 - Tue*/


        BufferedReader reader = new BufferedReader(new InputStreamReader((System.in)));
        String txt;
        //разделитель строки
        String separator = "[.]";
        //Устанавливаем язык локали
        String locale = "en";

        //регулярное выражение для проверки введеной даты
        String pattern = "^([0-3][0-9])[.](0[1-9]|1[0-2])[.](18|19|20)[0-9]{2}$";

        //требуем вводить дату, до того момента пока не будет введена корректная строка
        do {
            System.out.println("Введите вашу дату рождения в формате 31.12.2018:");
            txt = reader.readLine().trim();


        } while (!txt.matches(pattern));

        //дополнительная проверка даты на корректность
        String[] dateArrCheck = txt.split(separator);

        int dayBD = Integer.parseInt(dateArrCheck[0]);
        //декрементируем, так как Январь в Java имеет индекс 0, а не 1
        int monthBD = Integer.parseInt(dateArrCheck[1]) - 1;
        int yearBD = Integer.parseInt(dateArrCheck[2]);

        //Проверка: 1. Должно быть не более 31 дня в месяце 2. На корректность дней в феверале в зависимости от года
        // Корректность соответсвия количеству дней к месяцу не проверяется
        if (dayBD > 31 ||
                (dayBD > 28 && (monthBD == 2) && (yearBD % 4 != 0))) {
            //если проверка не пройдена - завершение
            System.out.println("Введена неверная дата! Завершение программы");
            return;
        } else {

            //Создаем объект календарь на Текущую дату
            Calendar calendarToday = Calendar.getInstance();

            //Создаем объект календаря и устанавливаем на дату Дня рождения
            Calendar calendarBD = Calendar.getInstance();
            calendarBD.set(yearBD, monthBD, dayBD, 0, 0, 0);

            //пока calendarBD меньше calendarToday сравнение будет выдавать -1
            int i = 0;
            while (calendarBD.compareTo(calendarToday) < 0) {
                Date date = calendarBD.getTime();
                //Форматируем дату в соответствии шаблону и локали
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy - EEE", new Locale(locale));
                System.out.println(i + " - " + sdf.format(date));
                i++;
                //Добавляем 1 год и идем на следующую итерацию цикла
                calendarBD.add(Calendar.YEAR, 1);

            }


        }


    }
}
