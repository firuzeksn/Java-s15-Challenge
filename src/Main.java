import model.*;
import service.LibraryData;
import service.LibraryManager;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LibraryManager manager = new LibraryManager();
        Scanner scanner = new Scanner(System.in);


        Librarian admin = new Librarian("Firuze", "1234");
        Student member = new Student(101);
        Faculty teacher = new Faculty(201);


        LibraryData.loadInitialData(manager);

        System.out.println("--- Kütüphane Otomasyonuna Hoşgeldiniz ---");
        admin.whoyouare();

        boolean running = true;
        while (running) {
            System.out.println("\n1. Kitap Ekle");
            System.out.println("2. Kitap Sil");
            System.out.println("3. Kitap Ara (ID/İsim)");
            System.out.println("4. Tüm Kitapları Listele");
            System.out.println("5. Kitap Ödünç Al");
            System.out.println("6. Kitap İade Et");
            System.out.println("7. Yazara Göre Kitap Listele");
            System.out.println("8. Kitap Adı Güncelle");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Kitap ID: ");
                    long id = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Kitap Adı: ");
                    String name = scanner.nextLine();
                    System.out.print("Yazar: ");
                    String author = scanner.nextLine();
                    System.out.print("Fiyat: ");
                    double price = scanner.nextDouble();

                    manager.addBook(new StudyBook(id, author, name, price, "1. Edition"));
                    System.out.println("Kitap başarıyla eklendi.");
                    break;
                case 2:
                    System.out.print("Silmek istediğiniz Kitap ID: ");
                    long deleteId = scanner.nextLong();
                    manager.deleteBook(deleteId);
                    break;
                case 3:
                    System.out.print("Aramak istediğiniz Kitap ID'si: ");
                    long searchId = scanner.nextLong();
                    Book foundBook = manager.findBookById(searchId);

                    if (foundBook != null) {
                        System.out.println("Kitap Bulundu: " + foundBook.getName() + " - Yazar: " + foundBook.getAuthor());
                    } else {
                        System.out.println("Bu ID ile bir kitap bulunamadı.");
                    }
                    break;
                case 4:
                    System.out.println("--- Kütüphanedeki Kitaplar ---");
                    manager.listAllBooks();
                    break;

                case 5:

                    MemberRecord activeMember = null;
                    int memberType = 0;
                    long mId = 0;

                    while (activeMember == null) {
                        System.out.println("\n>>> Üye Tipini Seçiniz: 1-Öğrenci, 2-Akademisyen");
                        if (scanner.hasNextInt()) {
                            memberType = scanner.nextInt();
                            scanner.nextLine();

                            if (memberType == 1) {
                                System.out.print("Öğrenci Numaranızı Giriniz: ");
                                mId = scanner.nextLong();
                                scanner.nextLine();
                                activeMember = new Student(mId);
                            } else if (memberType == 2) {
                                System.out.print("Akademisyen Numaranızı Giriniz: ");
                                mId = scanner.nextLong();
                                scanner.nextLine();
                                activeMember = new Faculty(mId);
                            } else {
                                System.out.println(">>> HATA: Yanlış tuşladınız! Lütfen 1 veya 2 giriniz.");
                            }
                        } else {
                            System.out.println(">>> HATA: Lütfen bir rakam giriniz!");
                            scanner.nextLine();
                        }
                    }


                    List<Long> selectedBookIds = new ArrayList<>();
                    boolean isDone = false;

                    while (!isDone) {
                        System.out.print("Ödünç almak istediğiniz Kitap ID: ");
                        if (scanner.hasNextLong()) {
                            long borrowId = scanner.nextLong();
                            scanner.nextLine();
                            selectedBookIds.add(borrowId);


                            System.out.print("Başka bir kitap eklemek ister misiniz? (1-Evet / 2-Hayır): ");
                            int continueChoice = scanner.nextInt();
                            scanner.nextLine();

                            if (continueChoice != 1) {
                                isDone = true;
                            }
                        } else {
                            System.out.println(">>> HATA: Geçersiz ID!");
                            scanner.nextLine();
                        }
                    }


                    System.out.println("\n--- İŞLEM ÖZETİ ---");
                    System.out.println("İşlem Yapan Üye ID: " + mId + " (" + (memberType == 1 ? "Öğrenci" : "Akademisyen") + ")");

                    double totalBill = 0;
                    for (Long bId : selectedBookIds) {
                        Book b = manager.findBookById(bId);
                        if (b != null && b.isStatus()) {
                            manager.borrowBook(bId, activeMember);
                            totalBill += b.getPrice();
                        } else {
                            System.out.println("ID: " + bId + " olan kitap kütüphanede yok veya alınamadı.");
                        }
                    }
                    System.out.println("Toplam Ödenecek Tutar: " + totalBill + " TL");
                    System.out.println("------------------------------");
                    break;
                case 6:
                    System.out.println("\n--- Kitap İade İşlemi ---");
                    System.out.print("İade edilecek Kitap ID: ");

                    if (scanner.hasNextLong()) {
                        long returnId = scanner.nextLong();
                        scanner.nextLine();


                        Book bookToReturn = manager.findBookById(returnId);

                        if (bookToReturn != null) {
                            manager.returnBook(returnId, member);
                        } else {
                            System.out.println(">>> HATA: Bu ID ile kayıtlı bir kitap bulunamadı.");
                        }
                    } else {
                        System.out.println(">>> HATA: Lütfen geçerli bir sayısal ID giriniz!");
                        scanner.nextLine();
                    }
                    break;

                case 7:
                    System.out.print("Aramak istediğiniz yazarın adı: ");
                    String searchAuthor = scanner.nextLine();
                    manager.listBooksByAuthor(searchAuthor);
                    break;

                case 8:
                    System.out.print("Güncellenecek Kitap ID: ");
                    long updateId = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Yeni Kitap Adı: ");
                    String newName = scanner.nextLine();
                    manager.updateBookName(updateId, newName);
                    break;

                case 0:
                    running = false;
                    System.out.println("Sistemden çıkılıyor...");
                    break;

                default:
                    System.out.println("Geçersiz seçenek!");
            }
        }
    }
}