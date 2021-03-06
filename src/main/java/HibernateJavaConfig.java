
import model.ConferenceParticipants;
import model.Firms;


import model.Payment;
import model.Teacher;
import model.CustomerEmployee;

import model.Lecture;



import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

import static javax.swing.text.html.HTML.Tag.HEAD;

public class HibernateJavaConfig implements HibernateConfig {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    @Override
    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/szok?allowPublicKeyRetrieval=true&useSSL=false");
                settings.put(Environment.USER, "newuser");
                settings.put(Environment.PASS, "password");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.FORMAT_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                //settings.put(Environment.HBM2DDL_AUTO, "create");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(ConferenceParticipants.class);
                configuration.addAnnotatedClass(Firms.class);


                configuration.addAnnotatedClass(Teacher.class);

                //configuration.addAnnotatedClass(Admin.class); // to be added once entity is created
                configuration.addAnnotatedClass(CustomerEmployee.class); // to be added once entity is created

                configuration.addAnnotatedClass(Lecture.class); // to be added once entity is created

                configuration.addAnnotatedClass(Payment.class);



                serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    @Override
    public void shutdown() {
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }
}
