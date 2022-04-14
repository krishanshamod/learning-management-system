package com.uok.backend.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LMSContentService implements ContentService{

    private final ContentRepository contentRepository;

    @Autowired
    public LMSContentService(ContentRepository contentRepository){
        this.contentRepository = contentRepository;
    }

    @Override
    public void addContentToACourse(Content content) {
        contentRepository.save(content);
    }

    @Override
    public List<Content> getContentForACourse(String courseId) {
        return contentRepository.findByCourseId(courseId);
    }
}
