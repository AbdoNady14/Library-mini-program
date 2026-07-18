import java.util.Scanner;

public class ReturnBook {
    
    public static void returnBook(User user, Scanner scan) {
        System.out.println("Enter the name of the book you want to return:");
        String bookName = scan.nextLine();
        Book book = SearchBook.searchBook(bookName);
        if (book == null) {
            System.out.println("Book not found");
            return;
        }
        for (int i = 0; i < user.borrowedBooks.length; i++) {
            if (user.borrowedBooks[i] != null && user.borrowedBooks[i].getBookId() == book.getBookId()) {
                user.borrowedBooks[i] = null;
                book.setAvailable(true);
                System.out.println(user.getName() + " returned the book: " + book.getTitle());
                return;
            }
        }
        System.out.println(user.getName() + " has not borrowed the book: " + book.getTitle());
    }
}
