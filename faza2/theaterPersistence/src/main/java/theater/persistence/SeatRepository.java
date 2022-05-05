package theater.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import theater.model.Seat;
import theater.model.TheaterParticipant;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class SeatRepository implements ISeatRepo{
    private static SessionFactory sessionFactory;

    public SeatRepository() {}

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
    public Seat findOne(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Seat> findAll() {
        try {
            initialize();
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    List<Seat> participants = session.createQuery("FROM Seat", Seat.class).list();
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
    public void save(Seat entity) {

    }

    @Override
    public void delete(Integer integer) throws IOException {

    }

    @Override
    public Seat update(Seat entity) {
        try {
            initialize();
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    session.update(entity);
                    tx.commit();
                    return entity;
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
}
