import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import utils.SendelUtils;

import java.io.File;
import java.io.IOException;


/**
 * Project Hibernate
 * Created by Shibkov Konstantin on 10.01.2019.
 */
public class Loader {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) throws IOException {
        setUp();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // TODO ЗАПОЛНИТЬ
        // TODO проверить и найти пересечения отпусков внутри отделов и вывести
        //TODO проверить заполнение таблицы


        //Создаем класс для работы с БД, передаем ссылку на сессию.
        DBLesson4 db = new DBLesson4(session);

        //проверяем заполнена ли таблица и заполняем если пустая
        if (db.isEmptyTable("learn.vacation")) {
            //если таблица пустая - заполняем ее отпусками
            db.fillVacations();
            //вносим изменения в БД
            session.getTransaction().commit();

            //начинаем новую. транзакцию
            session.beginTransaction();
            System.out.println("Таблица заполнена отпусками успешно!");
        }
        //запрос на поиск пересечений в БД
        db.getCrossVacations(SendelUtils.getDepartmentIdFromConsole("Введите номер департамента"));

        //вносим изменения в БД и закрываем сессию
        session.getTransaction().commit();
        session.close();

        //==================================================================
        if (sessionFactory != null) sessionFactory.close();

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
