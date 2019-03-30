package dao.impl;

import dao.PaymentDao;
import model.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PaymentDaoImpl implements PaymentDao {

    private final SessionFactory sessionFactory;
    public PaymentDaoImpl (SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;}

    @Override
    public List<Payment> findAll() {
        try (Session session = sessionFactory.openSession()){
            List<Payment>paymentList=session.createQuery("from Payment", Payment.class).list();
            paymentList.forEach(System.out::println);
            return paymentList;
    }
    }

    @Override
    public Payment getPaymentById(int paymentID) {
        try(Session session=sessionFactory.openSession()){
            Query<Payment> query=session.createNamedQuery("Payment_findById", Payment.class);
            query.setParameter("paymentID", paymentID);
            return query.getSingleResult();
        }
    }

    @Override
    public boolean insertPayment(Payment payment) {
        Transaction transaction=null;
        try(Session session=sessionFactory.openSession()){
            transaction=session.beginTransaction();
            session.save(payment);
            transaction.commit();
            return true;}
        catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            } e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deletePayment(int paymentID) {
        Transaction transaction=null;
        try(Session session=sessionFactory.openSession()){
            transaction=session.beginTransaction();
            Payment paymentToDelete=getPaymentById(paymentID);
            session.delete(paymentToDelete);
            transaction.commit();
            return true;}
        catch(Exception e){
            if(transaction!=null){
                transaction.rollback();
            } e.printStackTrace();
            return false;
        }
    }
}
