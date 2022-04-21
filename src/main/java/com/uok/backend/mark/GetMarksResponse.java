package com.uok.backend.mark;

public class GetMarksResponse {
    private String studentEmail;
    private String courseId;
    private int marks;

    public GetMarksResponse() {
    }

    public GetMarksResponse(String studentEmail, String courseId, int marks) {
        this.studentEmail = studentEmail;
        this.courseId = courseId;
        this.marks = marks;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
