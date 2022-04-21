package com.uok.backend.mark;

public class GetStudentMarksRequest {

    private String studentEmail;
    private String courseId;

    public GetStudentMarksRequest() {
    }

    public GetStudentMarksRequest(String studentEmail, String courseId) {
        this.studentEmail = studentEmail;
        this.courseId = courseId;
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
}
