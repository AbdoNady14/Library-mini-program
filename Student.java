public class Student extends User {

    Student(String name, String email) {
        super(name, email);
        this.borrowedBooks = new Book[3];
    }
    
}
