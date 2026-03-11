package service;

import model.*;

public class LibraryData {
    public static void loadInitialData(LibraryManager manager) {
        manager.addBook(new StudyBook(1, "Fyodor Dostoyevski", "Suç ve Ceza", 250.0, "1. Basım"));
        manager.addBook(new Journal(2, "TÜBİTAK", "Bilim ve Teknik", 100.0, "Sayı 540"));
        manager.addBook(new Magazine(3, "National Geographic", "Vahşi Doğa", 150.0, "Nisan 2026"));
        manager.addBook(new StudyBook(4, "Victor Hugo", "Sefiller", 200.0, "2. Basım"));
        manager.addBook(new Journal(5, "Nature", "Scientific Discovery", 150.0, "Vol 12"));
        manager.addBook(new Journal(6, "Aldous Huxley", "Cesur Yeni Dünya", 200.0, "35. Baskı"));
        manager.addBook(new Journal(7, "Yun Jungeun", "Kalp Çamaşırhanesi", 180.0, "Nisan 2024"));
        manager.addBook(new Journal(8, "Matt Haıg", "Gece Yarısı Kütüphanesi", 160.0, "Haziran 2021"));
        manager.addBook(new Journal(9, "Jean Teule", "Dansa Davet", 150.0, "Ağustos 2024"));
        manager.addBook(new Journal(10, "Osamu Dazai", "İnsanlığımı Yitirirken", 160.0, "Haziran 2022"));
        System.out.println(">>> Sistem verileri başarıyla yüklendi.");
    }
}