package com.uok.backend.mark;

import com.uok.backend.course.registration.CourseRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("addmarks")
    public ResponseEntity addMarks(@RequestBody AddMarksRequest addMarksRequest) {
        return markService.addCourseMarks(addMarksRequest);
    }

    @PostMapping("getmarks")
    public ResponseEntity getMarksForCourse(@RequestBody GetMarksRequest getMarksRequest) {
        return markService.getMarksForACourse(getMarksRequest);
    }

    @PostMapping("getstudentmarks")
    public ResponseEntity getStudentMarksForCourse(@RequestBody GetStudentMarksRequest getStudentMarksRequest) {
        return markService.getStudentMarksForACourse(getStudentMarksRequest);
    }

    @GetMapping("/{userEmail}")
    public List<CourseRegistration> getMarksForUser(@PathVariable String userEmail) {
        return markService.getMarksForUser(userEmail);
    }

}
