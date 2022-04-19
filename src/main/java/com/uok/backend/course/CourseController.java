package com.uok.backend.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "course")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("addnewcourse")
    public ResponseEntity registerNewCourse(@RequestBody Course courseData) {
        return courseService.addNewCourse(courseData);
    }

    @PostMapping("adduser")
    public ResponseEntity enrollUserToCourse(@RequestBody CourseEnrollRequest courseEnrollRequest) {
        return courseService.addUserToCourse(courseEnrollRequest);
    }

    @GetMapping("enrolledcourses")
    public ResponseEntity checkEnrolledCourses() {
        return courseService.getEnrolledCourses();
    }

    @GetMapping("availablecourses")
    public ResponseEntity checkAvailableCourses() {
        return courseService.getAvailableCourses();
    }
}
