public class Book {
    private int bookId;
    private String title;
    private String author;
    private int year;
    private int edition;
    private String publisher;
    private String isbn;
    private String cover;
    private String condition;
    private int price;
    private String notes;
    private  int isBorrowed;

    public Book(){
        super();
    }

    /*public Book (int bookId,String title,String author,int year,int edition, String publisher,String isbn,String cover,String condition, int price,String notes ) {
        super();
        this.bookId = bookId;
        this.title = title;
        this. author = author;
        this.year = year;
        this.edition = edition;
        this.publisher = publisher;
        this.isbn = isbn;
        this.cover = cover;
        this.condition = condition;
        this.price = price;
        this.notes = notes;

    }*/

    public Book(int bookId, String title, String author, int year, int edition, String publisher, String isbn, String cover, String condition, int price, String notes, int isBorrowed) {
        super();
        this.bookId = bookId;
        this.title = title;
        this. author = author;
        this.year = year;
        this.edition = edition;
        this.publisher = publisher;
        this.isbn = isbn;
        this.cover = cover;
        this.condition = condition;
        this.price = price;
        this.notes = notes;
        this.isBorrowed = isBorrowed;
    }


    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getEdition() {
        return  edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getPrice() {
        return  price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getIsBorrowed() {
        return isBorrowed;
    }

    public void setIsBorrowed(int isBorrowed) {
        this.isBorrowed = isBorrowed;
    }


    @Override
    public String toString() {
        return String.format("Book ID: %d\nTitle: %s\nAuthor: %s\nYear: %d\nEdition: %d\nPublisher: %s\nISBN: %s\nCover: %s\nCondition: %s\nPrice: %d\nNotes: %s\nIs Borrowed: %d",bookId,title,author,year,edition,publisher,isbn,cover,condition,price,notes, isBorrowed);

    }

}
