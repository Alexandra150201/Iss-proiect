package theater.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import theater.model.Booking;
import theater.model.Seat;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class BookingRepository implements IBookingRepo{
    private static SessionFactory sessionFactory;

    public BookingRepository() {}

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
    public Integer countEmptySeatsSituation() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Booking findOne(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Booking> findAll() {
        try {
            initialize();
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    List<Booking> participants = session.createQuery("FROM Booking", Booking.class).list();
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
    public void save(Booking entity) {
        try {
            initialize();
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    session.save(entity);
                    tx.commit();
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
    }

    @Override
    public void delete(Booking entity) throws IOException {
        try {
            initialize();
            try (Session session = sessionFactory.openSession()) {
                Transaction tx = null;
                try {
                    tx = session.beginTransaction();
                    String hql = String.format("delete from Booking where id=%d",entity.getId());
                    Query query = session.createQuery(hql);
                    query.executeUpdate();
                    tx.commit();
                } catch (RuntimeException ex) {
                    System.err.println("Eroare la delete " + ex);
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

    }

    @Override
    public Booking update(Booking entity) {
        return null;
    }
}
