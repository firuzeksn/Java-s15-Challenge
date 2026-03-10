package service;

import model.Book;
import model.MemberRecord;

public interface LibraryService {
    void addBook(Book book);
    void listAllBooks();
    void borrowBook(long bookId, MemberRecord member);
    void returnBook(long bookId, MemberRecord member);
}