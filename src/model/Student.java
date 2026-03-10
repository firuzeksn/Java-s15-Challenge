package model;

// Inheritance: Student bir MemberRecord'dur.
public class Student extends MemberRecord {

    // Constructor: Dışarıdan gelen memberId'yi super ile üst sınıfa gönderir.
    public Student(long memberId) {
        super(memberId);
    }

    // İstersen buraya öğrenciye özel metodlar ekleyebilirsin
    public void study() {
        System.out.println("Öğrenci ders çalışıyor...");
    }
}