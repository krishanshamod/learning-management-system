package com.uok.backend.course;

import com.uok.backend.course.registration.CourseRegistrationRepository;
import com.uok.backend.exceptions.CourseRegistrationException;
import com.uok.backend.exceptions.DataMissingException;
import com.uok.backend.user.UserService;
import com.uok.backend.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LMSCourseService implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseRegistrationRepository courseRegistrationRepository;
    private final UserService userService;
    private Logger logger;

    @Autowired
    public LMSCourseService (
            CourseRepository courseRepository,
            CourseRegistrationRepository courseRegistrationRepository,
            UserService userService,
            Logger logger
    ) {
        this.courseRepository = courseRepository;
        this.courseRegistrationRepository = courseRegistrationRepository;
        this.userService = userService;
        this.logger = logger;
    }

    @Override
    public ResponseEntity addNewCourse(Course courseData) {

        String email = userService.getTokenUser().getEmail();

        try {

            // check all data received or not and save to the database if all data received
            if (courseData.getId() == null || courseData.getName() == null) {
                throw new DataMissingException("Course ID or Course Name is missing");
            }

            // check course already exists in the database or not
            if (courseRepository.findById(courseData.getId()).isPresent()) {
                throw new CourseRegistrationException("Course already exists");
            }

            // save course to the database
            courseRepository.save(courseData);

            // add user to the course
            courseRegistrationRepository.addUserToCourse(email, courseData.getId());

            return ResponseEntity.ok().build();

        } catch (DataMissingException | CourseRegistrationException e) {
            logger.logException(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity addUserToCourse(CourseEnrollRequest courseEnrollRequest) {

        String email = userService.getTokenUser().getEmail();

        try {
            // check all data received or not
            if (courseEnrollRequest.getCourseId() == null) {
                throw new DataMissingException("Course ID is missing");
            }

            // add user to the course
            courseRegistrationRepository.addUserToCourse(email, courseEnrollRequest.getCourseId());

            return ResponseEntity.ok().build();

        } catch (DataMissingException e) {
            logger.logException(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity getEnrolledCourses() {

        String email = userService.getTokenUser().getEmail();

        List<GetCourseResponse> enrolledCourses = new ArrayList<>();

        courseRegistrationRepository.findAllByUserEmail(email).forEach(course -> {
            enrolledCourses.add(new GetCourseResponse(course.getCourse().getId(), course.getCourse().getName()));
        });

        return ResponseEntity.ok(enrolledCourses);
    }

    @Override
    public ResponseEntity getAvailableCourses() {

        String email = userService.getTokenUser().getEmail();

        List<Course> availableCourses = courseRepository.findAll();

        // get all available courses for that user
        courseRegistrationRepository.findAllByUserEmail(email).forEach(course -> {
            availableCourses.removeIf(c -> c.getId().equals(course.getCourse().getId()));
        });

        List<GetCourseResponse> availableCoursesResponse = new ArrayList<>();

        // Convert available courses to response format
        availableCourses.forEach(course -> {
            availableCoursesResponse.add(new GetCourseResponse(course.getId(), course.getName()));
        });

        return ResponseEntity.ok(availableCoursesResponse);
    }
}
