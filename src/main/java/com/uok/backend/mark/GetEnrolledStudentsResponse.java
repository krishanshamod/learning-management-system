package com.uok.backend.mark;

public class GetEnrolledStudentsResponse {

    private String email;
    private String firstName;
    private String lastName;
    private Integer marks;

    public GetEnrolledStudentsResponse() {
    }

    public GetEnrolledStudentsResponse(String email, String firstName, String lastName, Integer marks) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.marks = marks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }
}
