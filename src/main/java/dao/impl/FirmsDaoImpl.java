package dao.impl;

import dao.FirmsDao;
import model.Firms;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import java.util.List;

public class FirmsDaoImpl implements FirmsDao {
    private final SessionFactory sessionFactory;
    private final EntityManager entityManager;

    public FirmsDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.entityManager = sessionFactory.createEntityManager();
    }

    @Override
    public List<Firms> findAll() {
        try (Session session = sessionFactory.openSession()){
            List<Firms> firmsList = session.createQuery("from Firms", Firms.class).list();
            firmsList.forEach(System.out::println);
            return firmsList;
        }
    }

    @Override
    public Firms getByID(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Firms firms = session.load(Firms.class, id);
            System.out.println(firms);
            transaction.commit();
            return firms;
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Firms getByName(String firmName) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Firms firms = session.load(Firms.class, firmName);
            System.out.println(firms);
            transaction.commit();
            return firms;
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(Firms firms) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.save(firms);
            transaction.commit();
            return true;
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteByFirmName(String name) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            Query<Firms> query = session.createQuery("Firms_deleteByFirmName");
            transaction = session.beginTransaction();
            query.setParameter("firmName", name);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
