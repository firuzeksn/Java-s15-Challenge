import model.*;
import service.LibraryManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryManager manager = new LibraryManager();
        Scanner scanner = new Scanner(System.in);

        // Test için bir Librarian (Kütüphaneci) ve bir Student (Üye) oluşturalım
        Librarian admin = new Librarian("Firuze", "1234");
        Student member = new Student(101);

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
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Buffer temizleme

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

                    // Polymorphism örneği: StudyBook ekliyoruz
                    manager.addBook(new StudyBook(id, author, name, price, "1. Edition"));
                    System.out.println("Kitap başarıyla eklendi.");
                    break;

                case 4:
                    System.out.println("--- Kütüphanedeki Kitaplar ---");
                    manager.listAllBooks();
                    break;

                case 5:
                    System.out.print("Ödünç almak istediğiniz Kitap ID: ");
                    long borrowId = scanner.nextLong();
                    // Burada Manager içindeki ödünç alma mantığını çağırıyoruz
                    manager.borrowBook(borrowId, member);
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