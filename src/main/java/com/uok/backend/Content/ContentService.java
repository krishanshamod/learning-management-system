package com.uok.backend.Content;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContentService {
    ResponseEntity addContentToACourse(Content content);

    List<Content> getContentForACourse(String courseId);
}
