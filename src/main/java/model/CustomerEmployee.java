package model;


import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "customers'employees")
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
