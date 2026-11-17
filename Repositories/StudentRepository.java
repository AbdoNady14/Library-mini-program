package Repositories;
import java.util.ArrayList; 
import java.util.List;

import models.Student;

public class StudentRepository {
    private static final List<Student> students = new ArrayList<>();

    public static void addStudent(Student student) {
        students.add(student);
    }

    public static List<Student> getStudents() {
        return students;
    }

    public static Student getStudentByEmail(String email) {
        for (Student student : students) {
            if (student.getEmail().equals(email)) {
                return student;
            }
        }
        return null; // Return null if no student with the given email is found
    }
}
