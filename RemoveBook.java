import java.util.Scanner;

public class RemoveBook {
    public static void removeBook(Scanner scan) {
        System.out.println("Enter Book Name: ");
        String bookName = scan.nextLine();
        Book book = SearchBook.searchBook(bookName);
        if (book != null) {
            Books.removeBook(book);
            System.out.println("Book removed successfully");
        } else {
            System.out.println("Book not found");
        }
    }
}
