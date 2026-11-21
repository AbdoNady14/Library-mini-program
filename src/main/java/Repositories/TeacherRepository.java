package Repositories;

import models.Teacher;
import models.User;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository {
    public static void addTeacher(Teacher teacher) {
        UserRepository.addUser(teacher);
    }

    public static List<Teacher> getTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        for (User u : UserRepository.getUsersByRole("TEACHER")) {
            teachers.add((Teacher) u);
        }
        return teachers;
    }

    public static Teacher getTeacherByEmail(String email) {
        User u = UserRepository.getUserByEmail(email);
        return (u instanceof Teacher) ? (Teacher) u : null;
    }

    public static void updateTeacher(Teacher teacher) {
        UserRepository.updateUser(teacher);
    }

    public static void deleteTeacher(int id) {
        UserRepository.deleteUser(id);
    }
}