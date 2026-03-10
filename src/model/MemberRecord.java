package model;

public abstract class MemberRecord {
    private long memberId; // Bu alanın tanımlı olduğundan emin ol
    private int noBooksIssued = 0;
    private final int maxBookLimit = 5;

    // HATAYI ÇÖZEN KISIM BURASI:
    public MemberRecord(long memberId) {
        this.memberId = memberId;
    }

    // Getter ve Setter'lar
    public int getNoBooksIssued() { return noBooksIssued; }
    public void setNoBooksIssued(int noBooksIssued) { this.noBooksIssued = noBooksIssued; }
    public int getMaxBookLimit() { return maxBookLimit; }
}