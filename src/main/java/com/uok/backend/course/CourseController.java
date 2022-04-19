package com.uok.backend.course;

import com.uok.backend.course.registration.CourseRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/enrolled/{userEmail}")
    public List<Course> checkEnrolledCourses(@PathVariable String userEmail) {
        return courseService.getEnrolledCourses(userEmail);
    }

    @GetMapping("/available/{userEmail}")
    public List<Course> checkAvailableCourses(@PathVariable String userEmail) {
        return courseService.getAvailableCourses(userEmail);
    }

}
