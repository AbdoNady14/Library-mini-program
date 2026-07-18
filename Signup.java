import java.util.Scanner;

public class Signup {
    public static void signup(Scanner scan) {
        int accountType;
        System.out.println("enter account type:\n1. Student\n2. Teacher");
        accountType = scan.nextInt();
        if (accountType == 1) {
            System.out.println("Enter student name:");
            String name = scan.next();
            System.out.println("Enter student email:");
            String email = scan.next();
            Student student = new Student(name, email);
            Students.addStudent(student);

        } else if (accountType == 2) {
            System.out.println("Enter teacher name:");
            String name = scan.next();
            System.out.println("Enter teacher email:");
            String email = scan.next();
            Teacher teacher = new Teacher(name, email);
            Teachers.addTeacher(teacher);
        } else {
            System.out.println("Invalid account type");
        }
    }
}
