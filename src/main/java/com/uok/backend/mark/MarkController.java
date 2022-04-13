package com.uok.backend.mark;

import com.uok.backend.course.CourseService;
import com.uok.backend.course.registration.CourseRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "mark")
public class MarkController {

    private MarkService markService;

    @Autowired
    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @PutMapping("/{courseId}/{userEmail}/{mark}")
    public void addMarks(@PathVariable String courseId, @PathVariable String userEmail, @PathVariable Integer mark) {
        markService.addCourseMarks(courseId, userEmail, mark);
    }

    @GetMapping("/{userEmail}/{courseId}")
    public Integer getMarksForCourse(@PathVariable String userEmail, @PathVariable String courseId) {
        return markService.getMarksForACourse(userEmail , courseId);
    }

    @GetMapping("/{userEmail}")
    public List<CourseRegistration> getMarksForUser(@PathVariable String userEmail) {
        return markService.getMarksForUser(userEmail);
    }

}
