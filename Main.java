import java.util.Scanner;


public class Main {
    public static void signedin(Scanner scan, Teacher teacher) {
        int choice=0;
        while (choice!=9){
            System.out.println("Please select an option:\n1. Add Book\n2. Remove Book\n3. Search for a Book\n4. Display Books\n5. Borrow Book\n6. Return Book\n7. View Borrowed Books\n8. View Number of Books\n9. Signout");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    scan.nextLine();
                    AddBook.addBook(scan);
                    break;
                case 2:
                    scan.nextLine();
                    RemoveBook.removeBook(scan);
                    break;
                case 3:
                    scan.nextLine();
                    System.out.println("Enter Book Name: ");
                    String bookName = scan.nextLine();
                    Book book = SearchBook.searchBook(bookName);
                    if (book != null) {
                        DisplayBooks.displayBook(book);
                    }
                    break;
                case 4:
                    DisplayBooks.displayBooks();
                    break;
                case 5:
                    scan.nextLine();
                    BorrowBook.borrowBook(teacher, scan);
                    break;
                case 6:
                    scan.nextLine();
                    ReturnBook.returnBook(teacher, scan);
                    break;
                case 7:
                    Book[] borrowedBooks = teacher.getBorrowedBooks();
                    for (int i = 0; i < borrowedBooks.length; i++) {
                        if (borrowedBooks[i] != null) {
                            DisplayBooks.displayBook(borrowedBooks[i]);
                        }
                    }
                    break;
                case 8:
                    System.out.println("Number of books in the library: " + Books.getNumberOfBooks());
                    break;
                case 9:
                    System.out.println("Signing out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public static void signedin(Scanner scan, Student student) {
        int choice=0;
        while (choice!=6){
            System.out.println("Please select an option:\n1. Display Books\n2. Search for a Book\n3. Borrow Book\n4. Return Book\n5. View Borrowed Books\n6. Signout");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    DisplayBooks.displayBooks();
                    break;
                case 2:
                    scan.nextLine();
                    System.out.println("Enter Book Name: ");
                    String bookName = scan.nextLine();
                    Book book = SearchBook.searchBook(bookName);
                    if (book != null) {
                        DisplayBooks.displayBook(book);
                    }
                    break;
                case 3:
                    scan.nextLine();
                    BorrowBook.borrowBook(student, scan);
                    break;
                case 4:
                    scan.nextLine();
                    ReturnBook.returnBook(student, scan);
                    break;
                case 5:
                    Book[] borrowedBooks = student.getBorrowedBooks();
                    for (int i = 0; i < borrowedBooks.length; i++) {
                        if (borrowedBooks[i] != null) {
                            DisplayBooks.displayBook(borrowedBooks[i]);
                        }
                    }
                    break;
                case 6:
                    System.out.println("Signing out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int choice=0;

        while (choice != 3) {
            System.out.println("Welcome to the Library Management System!\n Please select an option:\n1. Signup\n2. Signin\n3. exit");
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    scan.nextLine();
                    Signup.signup(scan);
                    break;
                case 2:
                    scan.nextLine();
                    Signin.signin(scan);
                    break;
                case 3:
                    System.out.println("Exiting ...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scan.close();
        
    }
}
