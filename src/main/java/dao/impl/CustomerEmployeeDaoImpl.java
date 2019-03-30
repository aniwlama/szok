package dao.impl;

import dao.CustomerEmployeeDao;
import model.CustomerEmployee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class CustomerEmployeeDaoImpl implements CustomerEmployeeDao {

    private final SessionFactory sessionFactory;

    public CustomerEmployeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    @Override
    public List<CustomerEmployee> findAll() {
        try (Session session = sessionFactory.openSession()) {
            List<CustomerEmployee> employeesList = session.createQuery
                    ("from CustomerEmployee", CustomerEmployee.class).list();
            employeesList.forEach(System.out::println);
            System.out.println();
            return employeesList;
        }

    }

    @Override
    public CustomerEmployee getCustomerEmployeeByID(int employeeID) {
        try (Session session = sessionFactory.openSession()) {
            Query<CustomerEmployee> query =
                    session.createNamedQuery("CustomerEmployee_byEmployeeID", CustomerEmployee.class);
            query.setParameter("employeeID", employeeID);
            return query.getSingleResult();
        }

    }

    @Override
    public boolean insertEmployee(CustomerEmployee employee) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public boolean deleteEmployee(int employeeID) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CustomerEmployee employeeToDelete = getCustomerEmployeeByID(employeeID);
            session.delete(employeeToDelete);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction == null) {
                transaction.rollback();

            }
            e.printStackTrace();
            return false;
        }
    }
}

