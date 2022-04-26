package com.uok.backend.content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, ContentId> {
    List<Content> findByCourseId(String courseId);

    Content findByCourseIdAndTitle(String courseId, String title);
}
