package com.uok.backend.course;

import com.uok.backend.course.registration.CourseRegistration;
import com.uok.backend.course.registration.CourseRegistrationRepository;
import com.uok.backend.exceptions.DataMissingException;
import com.uok.backend.security.TokenValidator;
import com.uok.backend.user.LMSUserService;
import com.uok.backend.user.User;
import com.uok.backend.user.UserRepository;
import com.uok.backend.user.UserService;
import com.uok.backend.utils.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.net.http.HttpResponse;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {LMSCourseService.class})
@ExtendWith(MockitoExtension.class)
class LMSCourseServiceTest {
    @Autowired
    private LMSCourseService underTest;

    @Mock
    private CourseRepository courseRepository ;
    @Mock
    private CourseRegistrationRepository courseRegistrationRepository;
    @Mock
    private UserService userService;
    @Mock
    private Logger logger;

    @BeforeEach
    void setUp() {
        underTest = new LMSCourseService(courseRepository, courseRegistrationRepository, userService, logger);
    }


    @Test
    void shouldAddNewCourse() {
        //given
        Course course = new Course("cf", "Computer Fundamentals");
        User user = new User("pasandevin@gmail.com", "Pasan", "Jayawardene", "student");

        //when
        when(userService.getTokenUser()).thenReturn(user);
        ResponseEntity response = underTest.addNewCourse(course);

        //then
        ArgumentCaptor<Course> courseArgumentCaptor = ArgumentCaptor.forClass(Course.class);
        verify(courseRepository).save(courseArgumentCaptor.capture());
        Course capturedCourse = courseArgumentCaptor.getValue();
        assertThat(capturedCourse).isEqualTo(course);

        ArgumentCaptor<String> emailArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> courseIdArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(courseRegistrationRepository).addUserToCourse(emailArgumentCaptor.capture(), courseIdArgumentCaptor.capture());
        String capturedEmail = emailArgumentCaptor.getValue();
        String capturedCourseId = courseIdArgumentCaptor.getValue();
        assertThat(capturedEmail).isEqualTo(user.getEmail());
        assertThat(capturedCourseId).isEqualTo(capturedCourse.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldThrowWhenCourseIdIsNullWhenAddingNewCourse() {

        //given
        Course courseData = new Course(null, "Computer Fundamentals");
        User user = new User("pasandevin@gmail.com", "Pasan", "Jayawardene", "student");


        //when
        when(userService.getTokenUser()).thenReturn(user);
        ResponseEntity response = underTest.addNewCourse(courseData);

        //then
        ArgumentCaptor<String> errorMessageCaptor = ArgumentCaptor.forClass(String.class);
        verify(logger).logException(errorMessageCaptor.capture());
        String capturedErrorMessage = errorMessageCaptor.getValue();
        assertThat(capturedErrorMessage).isEqualTo("Course ID or Course Name is missing");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void shouldThrowWhenCourseNameIsNull() {

        //given
        Course courseData = new Course("cf", null);
        User user = new User("pasandevin@gmail.com", "Pasan", "Jayawardene", "student");


        //when
        when(userService.getTokenUser()).thenReturn(user);
        ResponseEntity response = underTest.addNewCourse(courseData);

        //then
        ArgumentCaptor<String> errorMessageCaptor = ArgumentCaptor.forClass(String.class);
        verify(logger).logException(errorMessageCaptor.capture());
        String capturedErrorMessage = errorMessageCaptor.getValue();
        assertThat(capturedErrorMessage).isEqualTo("Course ID or Course Name is missing");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldThrowWhenCourseIdIsTaken() {

        //given
        Course courseData = new Course("cf", "Computer Fundamentals");
        User user = new User("pasandevin@gmail.com", "Pasan", "Jayawardene", "student");


        //when
        when(userService.getTokenUser()).thenReturn(user);
        when(courseRepository.findById(courseData.getId())).thenReturn(Optional.of(courseData));
        ResponseEntity response = underTest.addNewCourse(courseData);

        //then
        ArgumentCaptor<String> errorMessageCaptor = ArgumentCaptor.forClass(String.class);
        verify(logger).logException(errorMessageCaptor.capture());
        String capturedErrorMessage = errorMessageCaptor.getValue();
        assertThat(capturedErrorMessage).isEqualTo("Course already exists");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }



    @Test
    void shouldAddUserToCourse() {

        //given
        User user = new User("pasandevin@gmail.com", "Pasan", "Jayawardene", "student");
        CourseEnrollRequest courseEnrollRequest = new CourseEnrollRequest("cf");


        //when
        when(userService.getTokenUser()).thenReturn(user);
        ResponseEntity response = underTest.addUserToCourse(courseEnrollRequest);

        //then
        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> courseIdCaptor = ArgumentCaptor.forClass(String.class);
        verify(courseRegistrationRepository).addUserToCourse(emailCaptor.capture(), courseIdCaptor.capture());
        String capturedEmail = emailCaptor.getValue();
        String capturedCourseId = courseIdCaptor.getValue();
        assertThat(capturedEmail).isEqualTo(user.getEmail());
        assertThat(capturedCourseId).isEqualTo(courseEnrollRequest.getCourseId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldThrowWhenCourseIdIsNullWhenEnrollingAUser() {

        //given
        User user = new User("pasandevin@gmail.com", "Pasan", "Jayawardene", "student");
        CourseEnrollRequest courseEnrollRequest = new CourseEnrollRequest(null);


        //when
        when(userService.getTokenUser()).thenReturn(user);
        ResponseEntity response = underTest.addUserToCourse(courseEnrollRequest);

        //then
        ArgumentCaptor<String> errorMessageCaptor = ArgumentCaptor.forClass(String.class);
        verify(logger).logException(errorMessageCaptor.capture());
        String capturedErrorMessage = errorMessageCaptor.getValue();
        assertThat(capturedErrorMessage).isEqualTo("Course ID is missing");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void getEnrolledCourses() {
    }

    @Test
    void getAvailableCourses() {
    }
}