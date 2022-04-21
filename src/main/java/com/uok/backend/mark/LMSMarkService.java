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
    private final UserService userService;

    @Autowired
    public LMSMarkService(CourseRegistrationRepository courseRegistrationRepository, UserService userService) {
        this.courseRegistrationRepository = courseRegistrationRepository;
        this.userService = userService;
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

            // set student marks for the course
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
    public ResponseEntity getMarksForACourse(GetMarksRequest getMarksRequest) {

        try {
            // check all data is received or not
            if (getMarksRequest.getCourseId() == null) {
                throw new DataMissingException("Course ID is Missing");
            }

            String studentEmail = userService.getTokenUser().getEmail();
            String courseId = getMarksRequest.getCourseId();

            // get student marks for the course
            int marks = courseRegistrationRepository
                    .findByCourseIdAndUserEmail(courseId, studentEmail).getMarks();

            return ResponseEntity.ok(new GetMarksResponse(studentEmail, courseId, marks));

        } catch (DataMissingException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity getStudentMarksForACourse(GetStudentMarksRequest getStudentMarksRequest) {

        try {
            // check all data is received or not
            if (getStudentMarksRequest.getCourseId() == null || getStudentMarksRequest.getStudentEmail() == null) {
                throw new DataMissingException("Student Email or Course ID is Missing");
            }

            String studentEmail = getStudentMarksRequest.getStudentEmail();
            String courseId = getStudentMarksRequest.getCourseId();

            // get student marks for the course
            int marks = courseRegistrationRepository
                    .findByCourseIdAndUserEmail(courseId, studentEmail).getMarks();

            return ResponseEntity.ok(new GetMarksResponse(studentEmail, courseId, marks));

        } catch (DataMissingException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    //TODO: change this to return a list of courses and marks only
    @Override
    public List<CourseRegistration> getMarksForUser(String userEmail) {
        List<CourseRegistration> courseRegistrations = courseRegistrationRepository.findAllByUserEmail(userEmail);
        courseRegistrations.removeIf(Objects.requireNonNull(courseRegistration -> courseRegistration.getMarks() == null));
        return courseRegistrations;
    }
}
