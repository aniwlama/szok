package dao;

import model.ConferenceParticipants;

import java.util.List;

public interface ConferenceParicipantsDao {
    List<ConferenceParticipants> findAll();
    ConferenceParticipants getByID (int id);
    ConferenceParticipants getByClientID(int id);
    ConferenceParticipants getByConferenceID(int id);
    boolean insert(ConferenceParticipants conferenceParticipants);
    boolean deleteByEmployeeID(int id);
    boolean deleteByClientID(int id);
    boolean deleteByConferenceID(int id);
}
