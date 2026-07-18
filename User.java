public abstract class User {
    private static int idCounter = 1;
    private int id;
    private String name;
    private String email;
    protected Book[] borrowedBooks;
    
    User(String name, String email) {
        this.id = idCounter++;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Book[] getBorrowedBooks() {
        return borrowedBooks;
    }
}
