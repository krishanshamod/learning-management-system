package com.uok.backend.Content;

import java.util.List;

public interface ContentService {
    void addContentToACourse(Content content);

    List<Content> getContentForACourse(String courseId);
}
