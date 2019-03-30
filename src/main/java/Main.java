import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Session;
import java.util.List;
import model.Teacher;
import dao.TeacherDao;
import dao.impl.TeacherDaoImpl;

public class Main {

    public static void main(String[]args){
        HibernateConfig config=new HibernateJavaConfig();
        SessionFactory sessionFactory=config.getSessionFactory();

        TeacherDao teacherDao=new TeacherDaoImpl(sessionFactory);
        teacherDao.findAll();
        System.out.println(teacherDao.getTeacherById(101));

       // Teacher newTeacher=new Teacher();
      //  newTeacher.setTeacherID(2001);
       // newTeacher.setConferenceID(5);
      //  newTeacher.setFirstName("Barnaba");
       // newTeacher.setSurname("Rabarbar");
       // newTeacher.setPayForHour(12);

     //   teacherDao.insertTeacher(newTeacher);

        //teacherDao.deleteTeacher(2001);

        config.shutdown();
    }
}
