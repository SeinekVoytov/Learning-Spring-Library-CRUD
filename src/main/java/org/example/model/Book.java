package org.example.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Book {

    private int bookId;
    private int memberId;

    @Size(min = 1, max = 100, message = "Length of book title should me between 1 and 100")
    private String title;

    @Size(min = 2, max = 100, message = "Length of author name should me between 1 and 100")
    private String author;

    @Min(value = 0, message = "Publishing year should be greater or equal then 0")
    @Max(value = 2024, message = "Publishing year should be less or equal then 2024")
    @NotNull(message = "Publishing year cannot be null")
    private int publishingYear;

    public Book() {
    }

    public Book(int bookId, int memberId, String title, String author, int publishingYear) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.title = title;
        this.author = author;
        this.publishingYear = publishingYear;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }
}
