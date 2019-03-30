package dao;

import model.CustomerEmployee;

import java.util.List;

public interface CustomerEmployeeDao {

    List<CustomerEmployee> findAll ();

    CustomerEmployee getCustomerEmployeeByID(int employeeID);

    boolean insertEmployee(CustomerEmployee employee);
    boolean deleteEmployee(int employeeID);


}
