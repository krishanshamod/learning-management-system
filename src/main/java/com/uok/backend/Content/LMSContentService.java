package com.uok.backend.Content;

import com.uok.backend.exceptions.ContentAddingFailureException;
import com.uok.backend.exceptions.DataMissingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<Content> getContentForACourse(String courseId) {
        return contentRepository.findByCourseId(courseId);
    }
}
