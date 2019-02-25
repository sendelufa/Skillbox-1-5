import objects.Department;
import objects.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.util.Date;
import java.util.List;

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

        /*List<Department> departments = (List<Department>) session.createQuery("FROM Department").list();
        for (Department department : departments) {

            System.out.println(department.getName() + " " + department.getEmployeeHead().getName());
            for (Employee emp : department.getEmployees()) {
                System.out.println("    - " + emp.getName());
            }
        }*/
        // TODO Задание №1: выбрать руководителей отделов, которые числятся не в том отделе в котором руководят.
        System.out.println("\n===\nЗадание №1: выбрать руководителей отделов, которые числятся не в том отделе в котором руководят.");

        List<Department> dep1 = (List<Department>)
                session.createQuery("FROM Department dep WHERE dep.employeeHead.dep_id <> dep.id").list();

        System.out.println("|- Руководитель департамента / ID департамента сотрудника / ID департамента / Название департамента");
        for (Department department : dep1) {
            System.out.println(addSpaces("-- " + department.getEmployeeHead().getName() + ";" +
                    department.getEmployeeHead().getDep_id() + ";" + department.getId() + ";" + department.getName()));
        }

        // TODO Задание №2: вывести руководителей отделов зп которых меньше 115000р
        System.out.println("\n===\nЗадание №2: вывести руководителей отделов зп которых меньше 115000р");

        List<Department> dep_salary = (List<Department>)
                session.createQuery("FROM Department dep WHERE dep.employeeHead.salary < 115000").list();

        System.out.println("|- Руководитель департамента / ЗП / Название департамента");
        for (Department department : dep_salary) {
            System.out.println(addSpaces("-- " + department.getEmployeeHead().getName() + ";" +
                    department.getEmployeeHead().getSalary() + ";" + department.getName()));
        }

        // TODO Задание №3: руководители отделов устроившиеся до 01.03.2010
        System.out.println("\n===\nЗадание №3: руководители отделов устроившиеся до 01.03.2010 (2010-03-01)");

        List<Department> dep_hire = (List<Department>)
                session.createQuery("FROM Department dep WHERE dep.employeeHead.hireDate < '2010-03-01'").list();

        System.out.println("|- Руководитель департамента / Дата трудоустройства / Название департамента");
        for (Department department : dep_hire) {
            System.out.println(addSpaces("-- " + department.getEmployeeHead().getName() + ";" +
                    department.getEmployeeHead().getHireDate() + ";" + department.getName()));
        }


        session.getTransaction().commit();
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

    //выравниваем ячейки добавляя пробелы
    private static String addSpaces(String lineIn) {
        String lines[] = lineIn.split(";");
        String lineFinal = "";
        int j = 0;
        for (String line : lines) {
            j++;
            lineFinal += line;
            for (int i = 0; i < (25 - line.length()); i++) {
                lineFinal += " ";
            }
            if (lines.length != j) lineFinal += " | ";
        }
        return lineFinal;
    }
}
