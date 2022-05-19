package com.uok.backend.course;

import com.uok.backend.course.registration.CourseRegistration;
import com.uok.backend.course.registration.CourseRegistrationRepository;
import com.uok.backend.user.User;
import com.uok.backend.user.UserService;
import com.uok.backend.utils.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;


import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        verify(courseRegistrationRepository).addUserToCourse(
                emailArgumentCaptor.capture(),
                courseIdArgumentCaptor.capture()
        );
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

        verify(courseRegistrationRepository, never()).save(any());

        verify(courseRegistrationRepository, never()).addUserToCourse(any(), any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void shouldThrowWhenCourseNameIsNullWhenAddingNewCourse() {

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

        verify(courseRegistrationRepository, never()).save(any());

        verify(courseRegistrationRepository, never()).addUserToCourse(any(), any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldThrowWhenCourseIdIsTakenWhenAddingNewCourse() {

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

        verify(courseRegistrationRepository, never()).save(any());

        verify(courseRegistrationRepository, never()).addUserToCourse(any(), any());

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

        verify(courseRegistrationRepository, never()).addUserToCourse(any(), any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldGetEnrolledCourses() {

        //given
        User user = new User("pasandevin@gmail.com", "Pasan", "Jayawardene", "student");
        Course course0 = new Course("cf", "Computer Fundamentals");
        Course course1 = new Course("mob", "Mobile App Development");

        List<CourseRegistration> courseRegistrations = new ArrayList<>();
        courseRegistrations.add(new CourseRegistration(user, course0));
        courseRegistrations.add(new CourseRegistration(user, course1));

        //when
        when(userService.getTokenUser()).thenReturn(user);
        when(courseRegistrationRepository.findAllByUserEmail(user.getEmail())).thenReturn(courseRegistrations);
        ResponseEntity response = underTest.getEnrolledCourses();

        //then
        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        verify(courseRegistrationRepository).findAllByUserEmail(emailCaptor.capture());
        String capturedEmail = emailCaptor.getValue();
        assertThat(capturedEmail).isEqualTo(user.getEmail());

        Object body = response.getBody();
        GetCourseResponse result0 = ((List<GetCourseResponse>) body).get(0);
        GetCourseResponse result1 =  ((List<GetCourseResponse>) body).get(1);

        assertThat(result0.getId()).isEqualTo(course0.getId());
        assertThat(result0.getName()).isEqualTo(course0.getName());
        assertThat(result1.getId()).isEqualTo(course1.getId());
        assertThat(result1.getName()).isEqualTo(course1.getName());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void shouldGetAvailableCourses() {

        //given
        User user = new User("pasandevin@gmail.com", "Pasan", "Jayawardene", "student");
        Course course0 = new Course("cf", "Computer Fundamentals");
        Course course1 = new Course("mob", "Mobile Development");

        List<Course> courseList = new ArrayList<>();
        courseList.add(course0);
        courseList.add(course1);

        List<GetCourseResponse> availableCoursesResponse = new ArrayList<>();
        availableCoursesResponse.add(new GetCourseResponse(course0.getId(), course0.getName()));


        //when
        when(userService.getTokenUser()).thenReturn(user);
        when(courseRepository.findAll()).thenReturn(courseList);
        ResponseEntity response = underTest.getAvailableCourses();

        //then
        verify(courseRepository).findAll();

        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        verify(courseRegistrationRepository).findAllByUserEmail(emailCaptor.capture());
        String capturedEmail = emailCaptor.getValue();
        assertThat(capturedEmail).isEqualTo(user.getEmail());

        Object body = response.getBody();
        GetCourseResponse result0 = ((List<GetCourseResponse>) body).get(0);
        GetCourseResponse result1 =  ((List<GetCourseResponse>) body).get(1);

        assertThat(result0.getId()).isEqualTo(course0.getId());
        assertThat(result0.getName()).isEqualTo(course0.getName());
        assertThat(result1.getId()).isEqualTo(course1.getId());
        assertThat(result1.getName()).isEqualTo(course1.getName());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}