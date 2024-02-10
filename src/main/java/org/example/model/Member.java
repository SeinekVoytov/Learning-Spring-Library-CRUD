package org.example.model;

public class Member {

    private int memberId;
    private String fullName;
    private int birthYear;

    public Member() {
    }

    public Member(int memberId, String fullName, int birthYear) {
        this.memberId = memberId;
        this.fullName = fullName;
        this.birthYear = birthYear;
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
