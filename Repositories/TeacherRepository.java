package Repositories;
import java.util.ArrayList; 
import java.util.List;

import models.Teacher;

public class TeacherRepository {
    private static final List<Teacher> teachers = new ArrayList<>();

    public static void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public static List<Teacher> getTeachers() {
        return teachers;
    }

    public static Teacher getTeacherByEmail(String email) {
        for (Teacher teacher : teachers) {
            if (teacher.getEmail().equals(email)) {
                return teacher;
            }
        }
        return null; // Return null if no teacher with the given email is found
    }
}
