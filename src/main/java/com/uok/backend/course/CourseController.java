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

    //TODO
    // before registering the users table should be checked
    // and the Lecturer should be enrolled
    @PostMapping()
    public void registerNewCourse(@RequestBody Course courseData) {
        courseService.addNewCourse(courseData);
    }

    //TODO
    //before registering the users table should be checked
    // and the Student should be enrolled.
    @PostMapping("/adduser/{userEmail}/{courseId}")
    public void enrollUserToCourse(@PathVariable String userEmail, @PathVariable String courseId) {
        courseService.addUserToCourse(userEmail, courseId);
    }

    @GetMapping("{userEmail}")
    public List<String> checkEnrolledCourses(@PathVariable String userEmail) {
        return courseService.getEnrolledCourses(userEmail);
    }

}
