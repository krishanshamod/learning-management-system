package com.uok.backend.course;

import com.uok.backend.course.registration.CourseRegistrationRepository;
import com.uok.backend.exceptions.CourseRegistrationException;
import com.uok.backend.exceptions.DataMissingException;
import com.uok.backend.security.JwtRequestFilter;
import com.uok.backend.security.TokenValidator;
import com.uok.backend.user.User;
import com.uok.backend.user.UserRepository;
import com.uok.backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LMSCourseService implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseRegistrationRepository courseRegistrationRepository;
    private final UserService userService;

    @Autowired
    public LMSCourseService (
            CourseRepository courseRepository,
            UserRepository userRepository,
            CourseRegistrationRepository courseRegistrationRepository,
            UserService userService
    ) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.courseRegistrationRepository = courseRegistrationRepository;
        this.userService = userService;
    }

    @Override
    public ResponseEntity addNewCourse(Course courseData) {

        String email = userService.getTokenUser().getEmail();

        try {

            // check all data received or not and save to the database if all data received
            if (courseData.getId() == null || courseData.getName() == null) {
                throw new DataMissingException("Course ID or Course Name is missing");
            }

            // check if course already exists in the database
            if (courseRepository.findById(courseData.getId()).isPresent()) {
                throw new CourseRegistrationException("Course already exists");
            }

            // save course to the database
            courseRepository.save(courseData);

            // add user to the course
            courseRegistrationRepository.addUserToCourse(email, courseData.getId());

            return ResponseEntity.ok().build();

        } catch (DataMissingException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();

        } catch (CourseRegistrationException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();

        }
    }

    //TODO
    // this method should be changed in future
    @Override
    public void addUserToCourse(String userEmail, String courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        Optional<User> userOptional = userRepository.findById(userEmail);

        if (userOptional.isPresent() && courseOptional.isPresent()) {
            courseRegistrationRepository.addUserToCourse(userEmail, courseId);
        }
    }

    @Override
    public List<Course> getEnrolledCourses(String userEmail) {
        List<Course> enrolledCourses = new ArrayList<>();
        courseRegistrationRepository.findAllByUserEmail(userEmail).forEach(course -> {
            enrolledCourses.add(course.getCourse());
        });
        return  enrolledCourses;
    }

    @Override
    public List<Course> getAvailableCourses(String userEmail) {
        List<Course> allCourses = courseRepository.findAll();
        courseRegistrationRepository.findAllByUserEmail(userEmail).forEach(course -> {
            allCourses.removeIf(c -> c.getId().equals(course.getCourse().getId()));
        });
        return allCourses;
    }
}
