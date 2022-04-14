package com.uok.backend.Content;

import com.uok.backend.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, ContentId> {
    List<Content> findByCourseId(String courseId);
}
