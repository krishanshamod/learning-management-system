package com.uok.backend.mark;

import com.uok.backend.course.registration.CourseRegistration;
import com.uok.backend.course.registration.CourseRegistrationRepository;
import com.uok.backend.exceptions.DataMissingException;
import com.uok.backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class LMSMarkService implements MarkService {
    private final CourseRegistrationRepository courseRegistrationRepository;

    @Autowired
    public LMSMarkService(CourseRegistrationRepository courseRegistrationRepository) {
        this.courseRegistrationRepository = courseRegistrationRepository;
    }

    @Override
    @Transactional
    public ResponseEntity addCourseMarks(AddMarksRequest addMarksRequest) {

        try {
            // check all data is received or not
            if (addMarksRequest.getStudentEmail() == null || addMarksRequest.getCourseId() == null
                    || addMarksRequest.getMarks() == -1) {
                throw new DataMissingException("Input Data Missing");
            }

            courseRegistrationRepository
                    .findByCourseIdAndUserEmail(addMarksRequest.getCourseId(), addMarksRequest.getStudentEmail())
                    .setMarks(addMarksRequest.getMarks());

            return ResponseEntity.ok().build();

        } catch (DataMissingException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public Integer getMarksForACourse(String userEmail, String courseId) {
//        List<CourseRegistration> courseRegistration = courseRegistrationRepository.findByCourseIdAndUserEmail(courseId, userEmail);
//        if (courseRegistration.size() == 1) {
//            return courseRegistration.get(0).getMarks();
//        }
        return null;
    }

    //TODO: change this to return a list of courses and marks only
    @Override
    public List<CourseRegistration> getMarksForUser(String userEmail) {
        List<CourseRegistration> courseRegistrations = courseRegistrationRepository.findAllByUserEmail(userEmail);
        courseRegistrations.removeIf(Objects.requireNonNull(courseRegistration -> courseRegistration.getMarks() == null));
        return courseRegistrations;
    }
}
