package org.example.model;

import java.util.List;

public class Member {

    // TODO: 11.02.24 validation
    private int memberId;
    private String fullName;
    private int birthYear;
    private List<Book> takenBooks;

    public Member() {
    }

    public Member(int memberId, String fullName, int birthYear, List<Book> takenBooks) {
        this.memberId = memberId;
        this.fullName = fullName;
        this.birthYear = birthYear;
        this.takenBooks = takenBooks;
    }

    public List<Book> getTakenBooks() {
        return takenBooks;
    }

    public void setTakenBooks(List<Book> takenBooks) {
        this.takenBooks = takenBooks;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
