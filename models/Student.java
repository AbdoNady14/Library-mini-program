package models;

import Repositories.StudentRepository;
import Services.LibraryService;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Student extends User {

    public Student(String name, String email, String password) {
        super(name, email, password);
        this.borrowedBooks = new Book[3];
    }

    public Student(int id, String name, String email, String password) {
        super(id, name, email, password);
        this.borrowedBooks = new Book[3];
    }

    public static void addStudent(String name, String email, String password) {
        Student student = new Student(name, email, password);
        StudentRepository.addStudent(student);
    }

    @Override
    public void showMenu(Scanner scan) {
        int choice = 0;
        while (choice != 6) {
            System.out.println("\nPlease select an option:\n1. Display All Books\n2. Search for a Book\n3. Borrow Book\n4. Return Book\n5. View Borrowed Books\n6. Signout");
            try {
                choice = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 6.");
                scan.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> {
                    List<Book> allBooks = LibraryService.getAllBooks();
                    if (allBooks.isEmpty()) {
                        System.out.println("No books available in the library.");
                    } else {
                        for (Book book : allBooks) {
                            System.out.println(book);
                        }
                    }
                }
                case 2 -> {
                    System.out.println("Enter Book Name or Author: ");
                    String keyword = scan.nextLine();
                    List<Book> results = LibraryService.searchBooksByKeyword(keyword);
                    if (results.isEmpty()) {
                        System.out.println("No matching books found.");
                    } else {
                        for (Book book : results) {
                            System.out.println(book);
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Enter the name of the book you want to borrow:");
                    String bookTitle = scan.nextLine().toLowerCase();
                    System.out.println(LibraryService.borrowBook(this, bookTitle));
                }
                case 4 -> {
                    System.out.println("Enter the name of the book you want to return:");
                    String bookTitle = scan.nextLine().toLowerCase();
                    System.out.println(LibraryService.returnBook(this, bookTitle));
                }
                case 5 -> {
                    boolean hasBooks = false;
                    for (Book book : this.getBorrowedBooks()) {
                        if (book != null) {
                            System.out.println(book);
                            hasBooks = true;
                        }
                    }
                    if (!hasBooks) {
                        System.out.println("You haven't borrowed any books yet.");
                    }
                }
                case 6 -> {
                    System.out.println("Signing out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}