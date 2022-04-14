package com.uok.backend.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
