import java.util.Scanner;

public class BorrowBook {
    public static void borrowBook(User user, Scanner scan) {
        System.out.println("Enter the name of the book you want to borrow:");
        String bookName = scan.nextLine();
        Book book = SearchBook.searchBook(bookName);
        if (book == null) {
            System.out.println("Book not found");
            return;
        }
        if (book.isAvailable()) {
            for (int i = 0; i < user.borrowedBooks.length; i++) {
                if (user.borrowedBooks[i] == null) {
                    user.borrowedBooks[i] = book;
                    book.setAvailable(false);
                    System.out.println(user.getName() + " borrowed the book: " + book.getTitle());
                    return;
                }
            }
            System.out.println(user.getName() + " has already borrowed the maximum number of books");
        } else {
            System.out.println(book.getTitle() + " is not available for borrowing");
        }
    }
}
