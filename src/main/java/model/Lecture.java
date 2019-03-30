package model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "lecture")
public class Lecture {

    @Id
    private int lectureID;
    @Column(name = "maxLearnersNumber")
    private int maxNumber;
    private String lectureDate;
    private String startHour;
    private String endHour;
    private int teacherID;
    private int conferenceID;
}
