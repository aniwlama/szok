package model;


import lombok.Data;
import javax.persistence.*;

@Data
@NamedQuery(name = "CustomerEmployee_byEmployeeID", query = "from CustomerEmployee where employeeID =: employeeID")
@Entity
@Table(name = "customersemployees")
public class CustomerEmployee {
    @Id
    private int employeeID;
    private String name;
    private String surname;
    private int employerID;
    @Column(unique = true)
    private String email;
    private int conferenceID;

}
