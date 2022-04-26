package com.uok.backend.content;

import org.springframework.http.ResponseEntity;

public interface ContentService {
    ResponseEntity addContentToACourse(Content content);

    ResponseEntity getContentForACourse(GetContentRequest getContentRequest);
}
