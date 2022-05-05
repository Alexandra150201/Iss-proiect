package theater.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import theater.model.ShowEvent;
import theater.model.TheaterParticipant;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ShowEventRepository implements IShowEventRepo{
    private static SessionFactory sessionFactory;

    public ShowEventRepository() {}

    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exception "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    static void close(){
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public ShowEvent findOne(Integer integer) {
        return null;
    }

    @Override
    public Iterable<ShowEvent> findAll() {
        try {
            initialize();
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    List<ShowEvent> participants = session.createQuery("FROM ShowEvent", ShowEvent.class).list();
                    tx.commit();
                    return participants;
                } catch (RuntimeException ex) {
                    System.err.println("Eroare la select " + ex);
                    if (tx != null)
                        tx.rollback();
                }
            }
        } catch (Exception e) {
            System.err.println("Exception " + e);
            e.printStackTrace();
        } finally {
            close();
        }
        return null;
    }

    @Override
    public void save(ShowEvent entity) {

    }

    @Override
    public void delete(Integer integer) throws IOException {

    }

    @Override
    public ShowEvent update(ShowEvent entity) {
        return null;
    }
}
