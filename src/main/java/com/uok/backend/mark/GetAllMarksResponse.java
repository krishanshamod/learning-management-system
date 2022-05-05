package com.uok.backend.mark;

public class GetAllMarksResponse {
    private String courseId;
    private String courseName;
    private int marks;
    private char grade;

    public GetAllMarksResponse() {
    }

    public GetAllMarksResponse(String courseId, String courseName, int marks, char grade) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.marks = marks;
        this.grade = grade;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }
}
