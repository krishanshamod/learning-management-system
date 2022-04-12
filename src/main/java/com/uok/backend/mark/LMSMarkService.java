package com.uok.backend.mark;

import com.uok.backend.course.registration.CourseRegistration;
import com.uok.backend.course.registration.CourseRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LMSMarkService implements MarkService {
    private final CourseRegistrationRepository courseRegistrationRepository;

    public LMSMarkService(CourseRegistrationRepository courseRegistrationRepository) {
        this.courseRegistrationRepository = courseRegistrationRepository;
    }

    //TODO: if there's a way to handle various exceptions, then do it
    @Override
    @Transactional
    public void addCourseMarks(String courseId, String userEmail, Integer marks) {
        List<CourseRegistration> courseRegistration = courseRegistrationRepository.findByCourseIdAndUserEmail(courseId, userEmail);
        if (courseRegistration.size() == 1) {
            courseRegistration.get(0).setMarks(marks);
        }
    }

    @Override
    public Integer getMarksForACourse(String userEmail, String courseId) {
        List<CourseRegistration> courseRegistration = courseRegistrationRepository.findByCourseIdAndUserEmail(courseId, userEmail);
        if (courseRegistration.size() == 1) {
            return courseRegistration.get(0).getMarks();
        }
        return null;
    }
}
