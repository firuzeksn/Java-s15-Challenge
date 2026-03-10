package service;

import model.Book;
import model.MemberRecord;
import java.util.*;

public class LibraryManager implements LibraryService {
    // Veri saklamak için MAP kullanımı (İsterleri karşılıyor)
    private Map<Long, Book> bookMap = new HashMap<>();
    // Yazarları benzersiz tutmak için SET kullanımı (İsterleri karşılıyor)
    private Set<String> authors = new HashSet<>();

    @Override
    public void addBook(Book book) {
        bookMap.put(book.getBookID(), book);
        authors.add(book.getAuthor());
    }

    @Override
    public void listAllBooks() {
        if (bookMap.isEmpty()) {
            System.out.println("Kütüphanede hiç kitap yok.");
            return;
        }
        for (Book book : bookMap.values()) {
            String status = book.isStatus() ? "Mevcut" : "Ödünç Verildi";
            System.out.println("ID: " + book.getBookID() + " | İsim: " + book.getName() + " | Durum: " + status);
        }
    }

    @Override
    public void borrowBook(long bookId, MemberRecord member) {
        Book book = bookMap.get(bookId);

        if (book == null) {
            System.out.println("Kitap bulunamadı!");
        } else if (!book.isStatus()) {
            System.out.println("Bu kitap zaten başkasında.");
        } else if (member.getNoBooksIssued() >= member.getMaxBookLimit()) {
            System.out.println("Kitap limitiniz (5) dolmuştur!");
        } else {
            // Kitabı ödünç ver
            book.setStatus(false);
            member.setNoBooksIssued(member.getNoBooksIssued() + 1);
            System.out.println("--- FATURA ---");
            System.out.println("Kitap: " + book.getName() + " | Ücret: " + book.getPrice() + " TL");
            System.out.println("Keyifli okumalar!");
        }
    }

    @Override
    public void returnBook(long bookId, MemberRecord member) {
        Book book = bookMap.get(bookId);
        if (book != null && !book.isStatus()) {
            book.setStatus(true);
            member.setNoBooksIssued(member.getNoBooksIssued() - 1);
            System.out.println("Kitap iade edildi. Ücret iadesi yapıldı: " + book.getPrice() + " TL");
        }
    }
}