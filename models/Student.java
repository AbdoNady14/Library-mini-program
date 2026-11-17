package models;
import java.util.List;
import java.util.Scanner;

import Repositories.StudentRepository;
import Services.LibraryService;


public class Student extends User {

    Student(String name, String email, String password) {
        super(name, email, password);
        this.borrowedBooks = new Book[3];
    }

    public static void addStudent(String name, String email, String password) {
        Student student = new Student(name, email, password);
        StudentRepository.addStudent(student);
    }    

    @Override
    public void showMenu(Scanner scan) {
        int choice=0;
        while (choice!=6){
            System.out.println("Please select an option:\n1. Display All Books\n2. Search for a Book\n3. Borrow Book\n4. Return Book\n5. View Borrowed Books\n6. Signout");
            choice = scan.nextInt();
            switch (choice) {
                case 1 -> {
                    List<Book> allBooks = LibraryService.getAllBooks();
                    if (allBooks.isEmpty()) {
                        System.out.println("No books available in the library.");
                    } else {
                        for (Book book : allBooks) {
                            System.out.println(book); // بيطبع الكتاب بتنسيق toString() تلقائياً
                        }
                    }
                }
                case 2 -> {
                    System.out.println("Enter Book Name: ");
                    String bookTitle = scan.nextLine();
                    Book book = LibraryService.searchBook(bookTitle);
                    System.out.println(book != null ? book : "Book not found.");
                }
                case 3 -> {
                    scan.nextLine();
                    System.out.println("Enter the name of the book you want to borrow:");
                    String bookTitle = scan.nextLine();
                    bookTitle = bookTitle.toLowerCase();
                    LibraryService.borrowBook(this, bookTitle);
                }
                case 4 -> {
                    scan.nextLine();
                    System.out.println("Enter the name of the book you want to return:");
                    String bookTitle = scan.nextLine();
                    bookTitle = bookTitle.toLowerCase();
                    LibraryService.returnBook(this, bookTitle);
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
