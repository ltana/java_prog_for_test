package sv.pvt.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import sv.pvt.mantis.model.Users;
import sv.pvt.mantis.model.UsersData;

import java.util.List;

/**
 * Created by Ltana on 25.04.2016.
 */
public class DBHelper {
    private final SessionFactory sessionFactory;

    public DBHelper(ApplicationManager applicationManager) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Users users() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UsersData> result = session.createQuery("from UsersData").list();
        session.getTransaction().commit();
        session.close();
        return new Users(result);
    }
}
