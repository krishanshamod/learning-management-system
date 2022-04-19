package com.uok.backend.Content;

import com.uok.backend.exceptions.ContentAddingFailureException;
import com.uok.backend.exceptions.DataMissingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LMSContentService implements ContentService{

    private final ContentRepository contentRepository;

    @Autowired
    public LMSContentService(ContentRepository contentRepository){
        this.contentRepository = contentRepository;
    }

    @Override
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
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity getContentForACourse(GetContentRequest getContentRequest) {

        try {
            // check all the data is received or not
            if (getContentRequest.getCourseId() == null) {
                throw new DataMissingException("CourseId is Missing");
            }

            return ResponseEntity.ok(contentRepository.findByCourseId(getContentRequest.getCourseId()));

        } catch (DataMissingException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
