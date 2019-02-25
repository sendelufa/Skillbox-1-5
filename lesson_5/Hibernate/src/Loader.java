import objects.Department;
import objects.Vacation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.math.BigInteger;
import java.util.List;

import static utils.SendelUtils.addSpaces;

/**
 * Created by Danya on 26.10.2015.
 */
public class Loader {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        setUp();

        // create a couple of events...
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // TODO Список отделов с количеством сотрудников в каждом из них

        List<Object> depCountEmployees = (List<Object>)
                session.createSQLQuery("SELECT d.id, d.name, count(emp.department_id) as ce " +
                        "FROM learn.employee emp " +
                        "RIGHT OUTER JOIN learn.department d ON d.id=emp.department_id " +
                        "GROUP BY d.id " +
                        "ORDER BY ce DESC").list();
        String title = addSpaces("№; Отдел; Кол-во сотрудников");
        String border = new String(new char[title.length()]).replace('\0', '-');
        System.out.println("\n === Список отделов с количеством сотрудников в каждом из них (убывающая сортировка по количеству сотрудников)");
        System.out.println(border + "\n" + title + "\n" + border);

        depCountEmployees.forEach(c -> {
            Object rows[] = (Object[]) c;
            System.out.println(addSpaces(rows[0] + ";" + rows[1] + ";" + rows[2]));
        });
        System.out.println(border);


        // TODO Список отделов, в которых работает меньше трёх сотрудников


        List<Object> depCountLessThreeEmployees = (List<Object>) session.createSQLQuery("" +
                "SELECT d.id, d.name, count(emp.department_id) ce " +
                "FROM learn.employee emp " +
                "RIGHT OUTER JOIN learn.department d ON d.id=emp.department_id " +
                "GROUP BY d.id " +
                "HAVING count(emp.department_id) < 3 " +
                "ORDER BY ce DESC").list();
        title = addSpaces("№; Отдел; Кол-во сотрудников");
        border = new String(new char[title.length()]).replace('\0', '-');
        System.out.println("\n === Список отделов, в которых работает меньше трёх сотрудников (убывающая сортировка по количеству сотрудников)");
        System.out.println(border + "\n" + title + "\n" + border);

        depCountLessThreeEmployees.forEach(c -> {
            Object rows[] = (Object[]) c;
            System.out.println(addSpaces(rows[0] + ";" + rows[1] + ";" + rows[2]));
        });
        System.out.println(border);
        session.close();

        //==================================================================
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    //=====================================================================

    private static void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(new File("src/config/hibernate.cfg.xml")) // configures settings from hibernate.config.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
