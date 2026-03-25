package model;

public class Student extends MemberRecord {

    public Student(long memberId) {
        super(memberId);
    }

    public void study() {
        System.out.println("Öğrenci ders çalışıyor...");
    }
}