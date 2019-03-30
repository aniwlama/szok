package dao;

import model.Teacher;
import java.util.List;


public interface TeacherDao {

    List<Teacher>findAll();
    Teacher getTeacherById(int teacherID);
    boolean insertTeacher(Teacher teacher);
    boolean deleteTeacher(int teacherID);

}
