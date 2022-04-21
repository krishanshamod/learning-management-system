package com.uok.backend.mark;

public class GetAllMarksResponse {
    private String courseId;
    private String courseName;
    private int marks;

    public GetAllMarksResponse() {
    }

    public GetAllMarksResponse(String courseId, String courseName, int marks) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.marks = marks;
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
}
