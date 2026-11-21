package Repositories;

import models.Student;
import models.User;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    public static void addStudent(Student student) {
        UserRepository.addUser(student);
    }

    public static List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        for (User u : UserRepository.getUsersByRole("STUDENT")) {
            students.add((Student) u);
        }
        return students;
    }

    public static Student getStudentByEmail(String email) {
        User u = UserRepository.getUserByEmail(email);
        return (u instanceof Student) ? (Student) u : null;
    }

    public static void updateStudent(Student student) {
        UserRepository.updateUser(student);
    }

    public static void deleteStudent(int id) {
        UserRepository.deleteUser(id);
    }
}