public class SearchBook {
    public static Book searchBook(String bookName) {
        bookName = bookName.toLowerCase();
        for (Book book : Books.getBooks()) {
            if (book.getTitle().equals(bookName)) {
                return book;
            }
        }
        return null;
    }
}
