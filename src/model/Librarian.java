package model;
public class Librarian extends Person {
    private String password;
    public Librarian(String name, String password) { super(name); this.password = password; }
    public String getPassword() { return password; }
    @Override public void whoyouare() { System.out.println("Ben kütüphaneciyim."); }
}