import java.util.Scanner;

public class AddBook {

    public static void addBook(Scanner scan) {
        System.out.println("Enter Book Name: ");
        String bookName = scan.nextLine();
        bookName = bookName.toLowerCase();
        System.out.println("Enter Book Author: ");
        String bookAuthor = scan.nextLine();
        System.out.println("Enter Book category: ");
        String bookCategory = scan.nextLine();
        Book book = new Book(bookName, bookAuthor, bookCategory);
        Books.addBook(book);
    }
}
