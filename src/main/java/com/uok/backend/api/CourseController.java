package com.uok.backend.api;

import com.uok.backend.service.CourseService;
import com.uok.backend.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
