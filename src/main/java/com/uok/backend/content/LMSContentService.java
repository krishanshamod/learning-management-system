package com.uok.backend.content;

import com.uok.backend.exceptions.ContentAddingFailureException;
import com.uok.backend.exceptions.DataMissingException;
import com.uok.backend.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LMSContentService implements ContentService{

    private final ContentRepository contentRepository;
    private Logger logger;

    @Autowired
    public LMSContentService(ContentRepository contentRepository, Logger logger) {
        this.contentRepository = contentRepository;
        this.logger = logger;
    }

    @Override
    @CacheEvict(cacheNames = {"contentCache"}, key = "#content.courseId")
    public ResponseEntity addContentToACourse(Content content) {

        try {
            // check all the data is received or not
            if (content.getCourseId() == null || content.getTitle() == null || content.getContent() == null) {
                throw new DataMissingException("Input Data Missing");
            }

            // check if the content already exists or not
            if (contentRepository.findByCourseIdAndTitle(content.getCourseId(), content.getTitle()) != null) {
                throw new ContentAddingFailureException("Content Already Exists");
            }

            // add the content to the database
            contentRepository.save(content);

            return ResponseEntity.ok().build();

        } catch (DataMissingException | ContentAddingFailureException e) {
            logger.logException(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    @Cacheable(cacheNames = {"contentCache"}, key = "#getContentRequest.courseId")
    public ResponseEntity getContentForACourse(GetContentRequest getContentRequest) {

        try {
            // check all the data is received or not
            if (getContentRequest.getCourseId() == null) {
                throw new DataMissingException("CourseId is Missing");
            }

            return ResponseEntity.ok(contentRepository.findByCourseId(getContentRequest.getCourseId()));

        } catch (DataMissingException e) {
            logger.logException(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
