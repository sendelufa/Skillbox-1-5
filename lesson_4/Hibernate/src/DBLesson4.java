import objects.Department;
import objects.Employee;
import objects.Vacation;
import org.hibernate.Session;
import utils.SendelUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Project Hibernate
 * Created by Shibkov Konstantin on 10.01.2019.
 */

public class DBLesson4 {
    Session s;
    String locale = "ru";
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", new Locale(locale));
    String graph = "";

    //передаем идентификатор сессии в конструктор
    public DBLesson4(Session s) {
        this.s = s;
    }

    //проверка таблицы на пустоту
    public boolean isEmptyTable(String table) {
        return s.createSQLQuery("SELECT * FROM " + table + " LIMIT 1;").uniqueResult() == null;
    }

    //перебор сотрудников и запуск генерации отпусков
    public void fillVacations() {
        List<Employee> emp = (List<Employee>) s.createQuery("FROM Employee emp").list();
        //Создаем объект календарь на Текущую дату

        emp.forEach(c -> {
            setEmployeeVacation(c);
        });

    }

    //генерация отпусков
    private void setEmployeeVacation(Employee emp) {
        //устанавливаем Calendar на дату приема на работу
        Calendar calHire = Calendar.getInstance();
        calHire.setTime(emp.getHireDate());

        //вычисляем следующий год
        Calendar yearNext = Calendar.getInstance();
        yearNext.add(Calendar.YEAR, 1);

        System.out.println(" ===" + " Сотрудник:" + emp.getName() + " Дата приема:" + sdf.format(calHire.getTime()));

        //обходим все года и назначаем отпуска


        while (calHire.get(Calendar.YEAR) <= yearNext.get(Calendar.YEAR)) {

            //длина отпуска в неделях
            int durationWeek = (int) Math.round(3 + Math.random());

            //переменные для проверки, для проверки работает ли более 120 дней в году трудоустройства
            //если работает больше 120 дней - выдаем отпуск в этом году
            int difference = calHire.getActualMaximum(Calendar.DAY_OF_YEAR) - calHire.get(Calendar.DAY_OF_YEAR);

            //проверка на допустимость отпуска, после первого прохода, difference всегда будет равен количеству дней в году
            if (difference > 120) {
                Calendar vacStart = Calendar.getInstance();
                Calendar vacEnd = Calendar.getInstance();

                //начальная дата отпуска
                vacStart.setTime(calHire.getTime());
                vacStart.add(Calendar.DAY_OF_YEAR,
                        (int) ((difference - durationWeek * 7) * Math.random()));

                //конечная дата отпуска
                vacEnd.setTime(vacStart.getTime());
                vacEnd.add(Calendar.WEEK_OF_YEAR, durationWeek);

                System.out.println("\t" + sdf.format(vacStart.getTime()) + " - " + sdf.format(vacEnd.getTime()));

                //сохраняем в БД отпуск через кастомный конструктор
                Vacation vac = new Vacation(emp, vacStart.getTime(), vacEnd.getTime(), emp.getDepartment().getEmployeeHead(), emp.getDepartment());
                s.save(vac);
            }

            //Переходим в следующий год на 1 января
            calHire.add(Calendar.YEAR, 1);
            calHire.set(Calendar.MONTH, 1);
            calHire.set(Calendar.DAY_OF_YEAR, 1);
        }


    }

    //поиск пересекающихся отпусков в департаменте
    public void getCrossVacations(int depid) {
        String query = "FROM Department dep JOIN dep.vacations v1 JOIN dep.vacations v2 " +
                "WHERE v1.departament = dep.id " +
                "AND v2.departament = dep.id " +
                "AND (((v1.date_start <= v2.date_end) AND (v1.date_end >= v2.date_start)) " +
                "OR  ((v1.date_start <= v2.date_start) AND (v1.date_end >= v2.date_start))" +
                "OR  ((v1.date_start <= v2.date_start) AND (v1.date_end >= v2.date_end))" +
                "OR  ((v1.date_start >= v2.date_start) AND (v1.date_end <= v2.date_end))) " +
                "AND v1.employee_id <> v2.employee_id " +
                "AND v1.id > v2.id AND dep.id = :id";

        List ans = s.createQuery(query).setParameter("id", depid).list();
        System.out.println("=== Повторяющиеся отпуска в одном департаменте");
        String title = SendelUtils.addSpaces("№; Департамент;№ отп;Дата отпуска; ФИО сотрудника;№ отп;Дата отпуска;ФИО сотрудника");
        String border = new String(new char[title.length()]).replace('\0', '-');
        System.out.println(title);
        System.out.println(border);
        ans.forEach(c -> {
            Object rows[] = (Object[]) c;
            Department dep = (Department) rows[0];
            Vacation v1 = (Vacation) rows[1];
            Vacation v2 = (Vacation) rows[2];
            System.out.println(SendelUtils.addSpaces(dep.getId() + ";" + dep.getName() + ";" +
                    v1.getId() + ";" + sdf.format(v1.getDate_start()) + "-" + sdf.format(v1.getDate_end()) + ";" +
                    v1.getEmployee().getName() + ";" +
                    v2.getId() + ";" + sdf.format(v2.getDate_start()) + "-" + sdf.format(v2.getDate_end()) + ";" +
                    v2.getEmployee().getName()));


        });

        System.out.println(this.graph);
    }

}

