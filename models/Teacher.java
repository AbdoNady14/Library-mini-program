package models;
import java.util.List;
import java.util.Scanner;

import Repositories.BookRepository;
import Repositories.TeacherRepository;
import Services.LibraryService;


public class Teacher extends User {
    
    Teacher(String name, String email, String password) {
        super(name, email, password);
        this.borrowedBooks = new Book[5];
    }

    public static void addTeacher(String name, String email, String password) {
        Teacher teacher = new Teacher(name, email, password);
        TeacherRepository.addTeacher(teacher);
    }

    @Override 
    public void showMenu(Scanner scan) {
        int choice=0;
        while (choice!=9){
            System.out.println("Please select an option:\n1. Add Book\n2. Remove Book\n3. Search for a Book\n4. Display All Books\n5. Borrow Book\n6. Return Book\n7. View Borrowed Books\n8. View Number of Books\n9. Signout");
            choice = scan.nextInt();
            switch (choice) {
                case 1 -> {
                    scan.nextLine();
                    System.out.println("Enter Book Name: ");
                    String bookTitle = scan.nextLine();
                    bookTitle = bookTitle.toLowerCase();
                    System.out.println("Enter Book Author: ");
                    String bookAuthor = scan.nextLine();
                    System.out.println("Enter Book category: ");
                    String bookCategory = scan.nextLine();
                    LibraryService.addBook(bookTitle, bookAuthor, bookCategory);
                }
                case 2 -> {
                    scan.nextLine();
                    System.out.println("Enter Book Name: ");
                    String bookTitle = scan.nextLine();
                    bookTitle = bookTitle.toLowerCase();
                    LibraryService.removeBook(bookTitle);
                }
                case 3 -> {
                    System.out.println("Enter Book Name: ");
                    String bookTitle = scan.nextLine();
                    Book book = LibraryService.searchBook(bookTitle);
                    System.out.println(book != null ? book : "Book not found.");
                }
                case 4 ->{
                    List<Book> allBooks = LibraryService.getAllBooks();
                    if (allBooks.isEmpty()) {
                        System.out.println("No books available in the library.");
                    } else {
                        for (Book book : allBooks) {
                            System.out.println(book); // بيطبع الكتاب بتنسيق toString() تلقائياً
                        }
                    }
                }
                case 5 -> {
                    scan.nextLine();
                    System.out.println("Enter the name of the book you want to borrow:");
                    String bookTitle = scan.nextLine();
                    bookTitle = bookTitle.toLowerCase();
                    LibraryService.borrowBook(this, bookTitle);
                }
                case 6 -> {
                    scan.nextLine();
                    System.out.println("Enter the name of the book you want to return:");
                    String bookTitle = scan.nextLine();
                    bookTitle = bookTitle.toLowerCase();
                    LibraryService.returnBook(this, bookTitle);
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
