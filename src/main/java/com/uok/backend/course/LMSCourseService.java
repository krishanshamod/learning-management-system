package com.uok.backend.course;

import com.uok.backend.user.User;
import com.uok.backend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LMSCourseService implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseRegistrationRepository courseRegistrationRepository;

    @Autowired
    public LMSCourseService (
            CourseRepository courseRepository,
            UserRepository userRepository,
            CourseRegistrationRepository courseRegistrationRepository
    ) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.courseRegistrationRepository = courseRegistrationRepository;
    }

    @Override
    public void addNewCourse(Course courseData) {

        courseRepository.save(courseData);
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
    //

    @Override
    public List<String> getEnrolledCourses(String userEmail) {
        return courseRegistrationRepository.findByUserEmail(userEmail);

    }

}
