package dao.impl;

import model.Teacher;
import dao.TeacherDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao{

    private final SessionFactory sessionFactory;
    public TeacherDaoImpl (SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Override
    public List<Teacher> findAll() {
        try (Session session = sessionFactory.openSession()){
            List<Teacher>teacherList=session.createQuery("from Teacher", Teacher.class).list();
            teacherList.forEach(System.out::println);
            return teacherList;
        }
    }

    @Override
    public Teacher getTeacherById(int teacherID) {
         try(Session session=sessionFactory.openSession()){
             Query<Teacher>query=session.createNamedQuery("Teacher_findById", Teacher.class);
             query.setParameter("teacherID", teacherID);
             return query.getSingleResult();
         }
    }

    @Override
    public boolean insertTeacher(Teacher teacher) {
        Transaction transaction=null;
        try(Session session=sessionFactory.openSession()){
            transaction=session.beginTransaction();
            session.save(teacher);
            transaction.commit();
            return true;}
            catch (Exception e){
            if(transaction!=null){
                transaction.rollback();
            } e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteTeacher(int teacherID) {
        Transaction transaction=null;
        try(Session session=sessionFactory.openSession()){
            transaction=session.beginTransaction();
            Teacher teacherToDelete=getTeacherById(teacherID);
            session.delete(teacherToDelete);
            transaction.commit();
            return true;}
            catch(Exception e){
            if(transaction!=null){
                transaction.rollback();
            } e.printStackTrace();
            return false;
        }
    }
}
