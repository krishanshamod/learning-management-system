package com.uok.backend.api;

import com.uok.backend.service.CourseService;
import com.uok.backend.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "course")
public class CourseController {
    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping()
    public void registerNewCourse(@RequestBody Course courseData) {
        courseService.addNewCourse(courseData);
    }

    @PostMapping("/adduser/{userEmail}/{courseId}")
    public void enrollUserToCourse(@PathVariable String userEmail, @PathVariable String courseId) {
        courseService.addUserToCourse(userEmail, courseId);
    }

}
