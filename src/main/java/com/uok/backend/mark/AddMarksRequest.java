package com.uok.backend.mark;

public class AddMarksRequest {

    private String courseId;
    private int marks = -1;
    private String studentEmail;

    public AddMarksRequest() {
    }

    public AddMarksRequest(String courseId, int marks, String studentEmail) {
        this.courseId = courseId;
        this.marks = marks;
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

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
}
