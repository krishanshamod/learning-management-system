package com.uok.backend.service;

import com.uok.backend.domain.Course;
import com.uok.backend.repository.CourseRepository;
import com.uok.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LMSCourseService implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public LMSCourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void addNewCourse(Course courseData) {
        courseRepository.save(courseData);
    }

}
