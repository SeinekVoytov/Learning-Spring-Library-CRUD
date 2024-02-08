package org.example.model;

public class Book {

    private int bookId;
    private int memberId;
    private String title;
    private String author;
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
