package com.uok.backend.Content;

import org.springframework.http.ResponseEntity;

public interface ContentService {
    ResponseEntity addContentToACourse(Content content);

    ResponseEntity getContentForACourse(GetContentRequest getContentRequest);
}
