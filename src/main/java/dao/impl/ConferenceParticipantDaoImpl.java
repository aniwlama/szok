package dao.impl;

import dao.ConferenceParicipantsDao;
import model.ConferenceParticipants;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ConferenceParticipantDaoImpl implements ConferenceParicipantsDao {
    private final SessionFactory sessionFactory;
    private final EntityManager entityManager;

    public ConferenceParticipantDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.entityManager = sessionFactory.createEntityManager();
    }

    @Override
    public List<ConferenceParticipants> findAll(){
        try (Session session = sessionFactory.openSession()){
            List<ConferenceParticipants> conferenceParticipantsList = session.createQuery("from ConferenceParticipants", ConferenceParticipants.class).list();
            conferenceParticipantsList.forEach(System.out::println);
            return conferenceParticipantsList;
        }
    }
    @Override
    public ConferenceParticipants getByID(int id){
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            ConferenceParticipants conferenceParticipants = session.load(ConferenceParticipants.class, id);
            System.out.println(conferenceParticipants);
            transaction.commit();
            return conferenceParticipants;
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public ConferenceParticipants getByClientID(int id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ConferenceParticipants> conferanceParticipantsCriteriaQuery = criteriaBuilder.createQuery(ConferenceParticipants.class);
        Root<ConferenceParticipants> root = conferanceParticipantsCriteriaQuery.from(ConferenceParticipants.class);
        CriteriaQuery<ConferenceParticipants> all = conferanceParticipantsCriteriaQuery.select(root).where(criteriaBuilder.equal(root.get("clientID"), id));
        TypedQuery<ConferenceParticipants> allQuery = entityManager.createQuery(all);
        return allQuery.getSingleResult();
    }

    @Override
    public ConferenceParticipants getByConferenceID(int id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ConferenceParticipants> criteriaQuery = criteriaBuilder.createQuery(ConferenceParticipants.class);
        Root<ConferenceParticipants> root = criteriaQuery.from(ConferenceParticipants.class);
        CriteriaQuery<ConferenceParticipants> all = criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("workshopID"), id));
        TypedQuery<ConferenceParticipants> allQuery = entityManager.createQuery(all);
        return allQuery.getSingleResult();
    }

    @Override
    public boolean insert(ConferenceParticipants conferenceParticipants) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.save(conferenceParticipants);
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
    public boolean deleteByEmployeeID(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            Query<ConferenceParticipants> query = session.createQuery("ConferenceParticipants_deleteByEmployeeID");
            transaction = session.beginTransaction();
            query.setParameter("employeeID", id);
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

    @Override
    public boolean deleteByClientID(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            Query<ConferenceParticipants> query = session.createQuery("ConferenceParticipants_deleteByClientID");
            transaction = session.beginTransaction();
            query.setParameter("clientID", id);
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

    @Override
    public boolean deleteByConferenceID(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()){
            Query<ConferenceParticipants> query = session.createQuery("ConferenceParticipants_deleteByConferenceID");
            transaction = session.beginTransaction();
            query.setParameter("conferenceID", id);
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
