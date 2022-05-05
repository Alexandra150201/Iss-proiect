package theater.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import theater.model.TheaterParticipant;

import java.io.IOException;
import java.util.List;

public class TheaterParticipantRepository implements ITheaterParticipantRepo {
    private static SessionFactory sessionFactory;

    public TheaterParticipantRepository() {
    }

    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Exception " + e);
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    static void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public TheaterParticipant findOne(Integer integer) {
        return null;
    }

    @Override
    public Iterable<TheaterParticipant> findAll() {
        try {
            initialize();
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    List<TheaterParticipant> participants = session.createQuery("FROM TheaterParticipant", TheaterParticipant.class).list();
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
    public void save(TheaterParticipant entity) {

    }

    @Override
    public void delete(Integer integer) throws IOException {

    }

    @Override
    public TheaterParticipant update(TheaterParticipant entity) {
        return null;
    }
}
