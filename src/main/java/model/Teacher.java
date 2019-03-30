package model;

import lombok.Data;
import javax.persistence.*;

@Data
@NamedQuery(name="Teacher_findById", query="from Teacher where teacherID =: teacherID")
@Entity
@Table (name="teacher")

public class Teacher {
    @Id
    private int teacherID;
    private int conferenceID;
    private int payForHour;
    private String firstName;
    private String surname;
}
