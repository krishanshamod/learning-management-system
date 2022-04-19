package com.uok.backend.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("addcontent")
    public ResponseEntity addContentToACourse(@RequestBody Content content) {
        return contentService.addContentToACourse(content);
    }

    @GetMapping("/{courseId}")
    public List<Content> getContentForACourse(@PathVariable String courseId) {
        return contentService.getContentForACourse(courseId);
    }
}
