public class DisplayBooks {

    public static void displayBook(Book book) {
        System.out.println("Book Id: " + book.getBookId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", Category: " + book.getCategory() + ", Available: " + book.isAvailable());
    }

    public static void displayBooks() {
        if (Books.getBooks().isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }
        System.out.println("Books in the library:");
        for (Book book : Books.getBooks()) {
            displayBook(book);
        }
    }
}
