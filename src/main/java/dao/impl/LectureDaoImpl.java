package dao.impl;

import dao.LectureDao;
import model.Lecture;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;

import java.util.List;

public class LectureDaoImpl implements LectureDao {

    private final SessionFactory sessionFactory;

    public LectureDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Lecture> findAll() {
        try(Session session = sessionFactory.openSession()){
            List<Lecture> allLectures = session.createQuery("from Lecture", Lecture.class).list();
            allLectures.forEach(System.out::println);
            return allLectures;
        }

    }

    @Override
    public Lecture getLectureByID(int lectureID) {
        try(Session session = sessionFactory.openSession()){
          Query<Lecture> query = session.createQuery("from Lecture where lectureID =: lectureID", Lecture.class);
          query.setParameter("lectureID", lectureID);
          return query.getSingleResult();

        }
    }

    @Override
    public boolean insertLecture(Lecture lecture) {
        return false;
    }

    @Override
    public boolean deleteLecture(int lectureID) {
        return false;
    }
}
