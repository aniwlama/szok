package dao;

import model.Lecture;

import java.util.List;

public interface LectureDao {

    List<Lecture> findAll ();

    Lecture getLectureByID (int lectureID);

    boolean insertLecture(Lecture lecture);

    boolean deleteLecture(int lectureID);
}
