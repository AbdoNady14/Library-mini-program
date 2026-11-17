package program;

import auth.Signin;
import auth.Signup;
import java.util.Scanner;

public class Program {
        public static void Options(Scanner scan) {
        int choice=0;

        while (choice != 3) {
            System.out.println("Welcome to the Library Management System!\n Please select an option:\n1. Signup\n2. Signin\n3. exit");
            choice = scan.nextInt();
            switch (choice) {
                case 1 -> {
                    scan.nextLine();
                    Signup.signup(scan);
                }
                case 2 -> {
                    scan.nextLine();
                    Signin.signin(scan);
                }
                case 3 -> System.out.println("Exiting ...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

}
