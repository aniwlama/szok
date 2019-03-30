import dao.CustomerEmployeeDao;
import dao.impl.CustomerEmployeeDaoImpl;
import model.CustomerEmployee;
import org.hibernate.SessionFactory;

public class Main {

    public static void main(String[] args) {
        HibernateConfig config = new HibernateJavaConfig();
        SessionFactory sessionFactory = config.getSessionFactory();

        CustomerEmployeeDao customerEmployeeDao = new CustomerEmployeeDaoImpl(sessionFactory);
        customerEmployeeDao.findAll();
        System.out.println(customerEmployeeDao.getCustomerEmployeeByID(10));

        CustomerEmployee employee = new CustomerEmployee();
        employee.setConferenceID(10);
        employee.setEmail("ble@ble.pl");
        employee.setEmployeeID(11);
        employee.setEmployerID(9);
        employee.setName("Jan");
        employee.setSurname("Kowalski");

        customerEmployeeDao.insertEmployee(employee);
        System.out.println(customerEmployeeDao.getCustomerEmployeeByID(11));
        customerEmployeeDao.deleteEmployee(11);
        config.shutdown();
    }





}

