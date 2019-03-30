package model;

import lombok.Data;

import javax.persistence.*;

@Data
@NamedQuery(name = "ConferenceParticipants_deleteByEmployeeID", query = "delete from ConferenceParticipants where eployeeID =: id")
@NamedQuery(name = "ConferenceParticipants_deleteByClientID", query = "delete from ConferenceParticipants where clientID =: id")
@NamedQuery(name = "ConferenceParticipants_deleteByConferenceID", query = "delete from ConferenceParticipants where conferenceID =: id")
@Entity
@Table (name = "conferenceparticipants")
public class ConferenceParticipants {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeID;
    private int clientID;

    private int conferenceID;
}
