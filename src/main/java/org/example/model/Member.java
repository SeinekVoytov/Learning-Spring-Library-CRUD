package org.example.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class Member {

    private int memberId;

    @Size(min = 3, max = 100, message = "Name length should be between 3 and 100")
    private String fullName;

    @NotNull(message = "Birth age cannot be null")
    @Min(value = 1900, message = "Birth year cannot be less than 1900")
    @Max(value = 2014, message = "Birth year cannot be greater than 2014")
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
