
package br.com.exponent.persistence;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


@SuppressWarnings("deprecation")
public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            
            sessionFactory = new Configuration().configure("br/com/exponent/config/pgsql_hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
