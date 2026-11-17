package Services;

import Repositories.StudentRepository;
import Repositories.TeacherRepository;
import models.Student;
import models.Teacher;
import models.User;

public class AuthService {

// 1. Sign in: returns the User object if credentials are valid, or null otherwise
    public static User signin(String email, String password) {
        if (email == null || password == null) return null;

        User user = getUserByEmail(email.trim());
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

// 2. Sign up: validates input, creates the account, and returns a status message
    public static String signup(int accountType, String name, String email, String password) {
        if (accountType != 1 && accountType != 2) {
            return "Invalid account type.";
        }
        if (name == null || name.trim().isEmpty()) {
            return "Name cannot be empty.";
        }
        if (!isValidEmail(email)) {
            return "Invalid email format (Example: name@example.com).";
        }
        if (getUserByEmail(email.trim()) != null) {
            return "Email already exists. Try a different email.";
        }
        if (password == null || password.isEmpty()) {
            return "Password cannot be empty.";
        }

        String cleanName = name.trim();
        String cleanEmail = email.trim();

        switch (accountType) {
            case 1 -> Student.addStudent(cleanName, cleanEmail, password);
            case 2 -> Teacher.addTeacher(cleanName, cleanEmail, password);
        }

        return "Account created successfully!";
    }

// 3. Helper methods for user lookup and email validation
    public static User getUserByEmail(String email) {
        if (email == null) return null;
        String cleanEmail = email.trim();

        Student student = StudentRepository.getStudentByEmail(cleanEmail);
        if (student != null) {
            return student;
        }
        return TeacherRepository.getTeacherByEmail(cleanEmail);
    }

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.trim().matches(emailRegex);
    }
}