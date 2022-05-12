package com.uok.backend.mark;

import com.uok.backend.course.registration.CourseRegistration;
import com.uok.backend.course.registration.CourseRegistrationRepository;
import com.uok.backend.exceptions.DataMissingException;
import com.uok.backend.user.UserService;
import com.uok.backend.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class LMSMarkService implements MarkService {
    private final CourseRegistrationRepository courseRegistrationRepository;
    private final UserService userService;
    private Logger logger;

    @Autowired
    public LMSMarkService(
            CourseRegistrationRepository courseRegistrationRepository,
            UserService userService,
            Logger logger
    ) {
        this.courseRegistrationRepository = courseRegistrationRepository;
        this.userService = userService;
        this.logger = logger;
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
            logger.logException(e.getMessage());
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
            logger.logException(e.getMessage());
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
            logger.logException(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity getMarksForUser() {

        String studentEmail = userService.getTokenUser().getEmail();

        // get student marks for all courses
        List<CourseRegistration> courseRegistrations = courseRegistrationRepository.findAllByUserEmail(studentEmail);

        courseRegistrations.removeIf(Objects.requireNonNull(
                courseRegistration -> courseRegistration.getMarks() == null));

        // convert student marks to readable format
        List<GetAllMarksResponse> getAllMarksResponses = new ArrayList<>();

        courseRegistrations.forEach(courseRegistration -> {
            getAllMarksResponses.add(new GetAllMarksResponse(
                    courseRegistration.getCourse().getId(),
                    courseRegistration.getCourse().getName(),
                    courseRegistration.getMarks(),
                    courseRegistration.getGrade()));
        });

        return ResponseEntity.ok(getAllMarksResponses);
    }

    @Override
    public ResponseEntity getEnrolledStudents(GetMarksRequest getMarksRequest) {

        try {
            // check all data is received or not
            if (getMarksRequest.getCourseId() == null) {
                throw new DataMissingException("Course ID is Missing");
            }

            String email = userService.getTokenUser().getEmail();

            List<GetEnrolledStudentsResponse> enrolledStudents = new ArrayList<>();

            // get all enrollment data for that course
            List<CourseRegistration> courseRegistrations = courseRegistrationRepository
                    .findByCourseId(getMarksRequest.getCourseId());

            // add all students with marks to the list
            courseRegistrations.forEach(courseRegistration -> {

                // check if the user is student or lecturer
                if (!courseRegistration.getUser().getEmail().equals(email)) {

                    // add student to the list
                    enrolledStudents.add(new GetEnrolledStudentsResponse(
                            courseRegistration.getUser().getEmail(),
                            courseRegistration.getUser().getFirstName(),
                            courseRegistration.getUser().getLastName(),
                            courseRegistration.getMarks()
                    ));
                }
            });

            return ResponseEntity.ok(enrolledStudents);

        } catch (DataMissingException e) {
            logger.logException(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
