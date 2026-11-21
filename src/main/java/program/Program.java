package program;

import Services.AuthService;
import models.User;

import java.util.Scanner;

public class Program {

    public static void Options(Scanner scan) {
        int choice = 0;

        while (choice != 3) {
            System.out.println("\n=== Welcome to the Library Management System ===");
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Please select an option: ");

            if (!scan.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number (1-3).");
                scan.nextLine(); // Clear invalid input
                continue;
            }

            choice = scan.nextInt();
            scan.nextLine(); // Consume the leftover newline character

            switch (choice) {
                case 1 -> handleSignIn(scan);
                case 2 -> handleSignUp(scan);
                case 3 -> System.out.println("Exiting program. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleSignIn(Scanner scan) {
        System.out.println("\n--- Sign In ---");
        System.out.print("Enter Email: ");
        String email = scan.nextLine();

        System.out.print("Enter Password: ");
        String password = scan.nextLine();

        User loggedInUser = AuthService.signin(email, password);

        if (loggedInUser != null) {
            System.out.println("\nWelcome back, " + loggedInUser.getName() + "!");
            loggedInUser.showMenu(scan);
        } else {
            System.out.println("Invalid email or password. Please try again.");
        }
    }

    private static void handleSignUp(Scanner scan) {
        System.out.println("\n--- Sign Up ---");
        System.out.println("Select Account Type:");
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        System.out.print("Choice: ");

        if (!scan.hasNextInt()) {
            System.out.println("Invalid account type.");
            scan.nextLine();
            return;
        }

        int accountType = scan.nextInt();
        scan.nextLine(); // Consume newline

        System.out.print("Enter Full Name: ");
        String name = scan.nextLine();

        System.out.print("Enter Email: ");
        String email = scan.nextLine();

        System.out.print("Enter Password: ");
        String password = scan.nextLine();

        String result = AuthService.signup(accountType, name, email, password);
        System.out.println(result);
    }
}