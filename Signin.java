import java.util.Scanner;

public class Signin {
    public static void signin(Scanner scan) {
        System.out.println("Enter your Email");
        String email = scan.next();

        for(Student student : Students.getStudents()) {
            if(student.getEmail().equals(email)) {
                System.out.println("Welcome " + student.getName());
                Main.signedin(scan, student);
                return;
            }
        }

        for(Teacher teacher : Teachers.getTeachers()) {
            if(teacher.getEmail().equals(email)) {
                System.out.println("Welcome " + teacher.getName());
                Main.signedin(scan, teacher);
                return;
            }
        }
        System.out.println("Invalid email or password");
    }
}
