package model;

public abstract class Book {
    private long bookID;
    private String author;
    private String name;
    private double price;
    private boolean status;
    private String edition;

    public Book(long bookID, String author, String name, double price, String edition) {
        this.bookID = bookID;
        this.author = author;
        this.name = name;
        this.price = price;
        this.edition = edition;
        this.status = true;
    }

    public double getPrice() {
        return price;
    }

    public long getBookID() {
        return bookID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}