package models;

import Repositories.BookRepository;
import Repositories.TeacherRepository;
import Services.LibraryService;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Teacher extends User {

    public Teacher(String name, String email, String password) {
        super(name, email, password);
        this.borrowedBooks = new Book[5];
    }

    public Teacher(int id, String name, String email, String password) {
        super(id, name, email, password);
        this.borrowedBooks = new Book[5];
    }

    public static void addTeacher(String name, String email, String password) {
        Teacher teacher = new Teacher(name, email, password);
        TeacherRepository.addTeacher(teacher);
    }

    @Override
    public void showMenu(Scanner scan) {
        int choice = 0;
        while (choice != 9) {
            System.out.println("\nPlease select an option:\n1. Add Book\n2. Remove Book\n3. Search for a Book\n4. Display All Books\n5. Borrow Book\n6. Return Book\n7. View Borrowed Books\n8. View Number of Books\n9. Signout");
            try {
                choice = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 9.");
                scan.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter Book Name: ");
                    String bookTitle = scan.nextLine().toLowerCase();
                    System.out.println("Enter Book Author: ");
                    String bookAuthor = scan.nextLine();
                    System.out.println("Enter Book Category: ");
                    String bookCategory = scan.nextLine();
                    System.out.println(LibraryService.addBook(bookTitle, bookAuthor, bookCategory));
                }
                case 2 -> {
                    System.out.println("Enter Book ID to remove: ");
                    try {
                        int bookId = scan.nextInt();
                        scan.nextLine();
                        System.out.println(LibraryService.removeBook(bookId));
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid ID! Please enter a numeric Book ID.");
                        scan.nextLine();
                    }
                }
                case 3 -> {
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
                case 4 -> {
                    List<Book> allBooks = LibraryService.getAllBooks();
                    if (allBooks.isEmpty()) {
                        System.out.println("No books available in the library.");
                    } else {
                        for (Book book : allBooks) {
                            System.out.println(book);
                        }
                    }
                }
                case 5 -> {
                    System.out.println("Enter the name of the book you want to borrow:");
                    String bookTitle = scan.nextLine().toLowerCase();
                    System.out.println(LibraryService.borrowBook(this, bookTitle));
                }
                case 6 -> {
                    System.out.println("Enter the name of the book you want to return:");
                    String bookTitle = scan.nextLine().toLowerCase();
                    System.out.println(LibraryService.returnBook(this, bookTitle));
                }
                case 7 -> {
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
                case 8 -> System.out.println("Number of books in the library: " + BookRepository.getNumberOfBooks());
                case 9 -> {
                    System.out.println("Signing out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}