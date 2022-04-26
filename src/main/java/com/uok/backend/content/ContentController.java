package com.uok.backend.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("getcontent")
    public ResponseEntity getContentForACourse(@RequestBody GetContentRequest getContentRequest) {
        return contentService.getContentForACourse(getContentRequest);
    }
}
