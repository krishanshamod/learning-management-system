package com.uok.backend.course;

import org.springframework.beans.factory.annotation.Autowired;
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

    // This method should be changed. before registering the users table should be checked
    // and the user should be enrolled
    @PostMapping()
    public void registerNewCourse(@RequestBody Course courseData) {
        courseService.addNewCourse(courseData);
    }

    @PostMapping("/adduser/{userEmail}/{courseId}")
    public void enrollUserToCourse(@PathVariable String userEmail, @PathVariable String courseId) {
        courseService.addUserToCourse(userEmail, courseId);
    }

    ////
    @GetMapping("{userEmail}")
    public List<CourseRegistration> checkEnrolledCourses(@PathVariable String userEmail) {
        return courseService.getEnrolledCourses(userEmail);
    }
    ////

}
