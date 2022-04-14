package com.uok.backend.Content;

import com.uok.backend.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "content")
public class ContentController {

    private ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping()
    public void addContentToACourse(@RequestBody Content content) {
        contentService.addContentToACourse(content);
    }

    @GetMapping("/{courseId}")
    public List<Content> getContentForACourse(@PathVariable String courseId) {
        return contentService.getContentForACourse(courseId);
    }
}
