package com.uok.backend.mark;

import com.uok.backend.course.Course;
import com.uok.backend.course.CourseRepository;
import com.uok.backend.course.GetCourseResponse;
import com.uok.backend.course.LMSCourseService;
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

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@ContextConfiguration(classes = {LMSMarkService.class})
@ExtendWith(MockitoExtension.class)
class LMSMarkServiceTest {

    @Autowired
    private LMSMarkService underTest;

    @Mock
    private CourseRegistrationRepository courseRegistrationRepository;
    @Mock
    private UserService userService;
    @Mock
    private Logger logger;

    @BeforeEach
    void setUp() {
        underTest = new LMSMarkService(courseRegistrationRepository, userService, logger);
    }

    @Test
    void shouldAddMarksToACourse() {
        //given
        User user = new User("pasandevin@gmail.com", "Pasan", "Jayawardene", "student");
        Course course = new Course("cf", "Computer Fundamentals");
        AddMarksRequest addMarksRequest = new AddMarksRequest("cf", 70, "pasandevin@gmail.com");
        CourseRegistration courseRegistration = new CourseRegistration(user, course);

        //when
        when(courseRegistrationRepository.findByCourseIdAndUserEmail(any(), any())).thenReturn(courseRegistration);
        ResponseEntity response = underTest.addCourseMarks(addMarksRequest);

        //then
        ArgumentCaptor<String> courseIdArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> emailArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(courseRegistrationRepository).findByCourseIdAndUserEmail(courseIdArgumentCaptor.capture(), emailArgumentCaptor.capture());
        String capturedCourseId = courseIdArgumentCaptor.getValue();
        String capturedEmail = emailArgumentCaptor.getValue();
        assertThat(capturedCourseId).isEqualTo(addMarksRequest.getCourseId());
        assertThat(capturedEmail).isEqualTo(addMarksRequest.getStudentEmail());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldThrowWhenStudentEmailIsNullWhenAddingMarksToACourse() {

        //given
        AddMarksRequest addMarksRequest = new AddMarksRequest("cf", 70, null);

        //when
        ResponseEntity response = underTest.addCourseMarks(addMarksRequest);

        //then
        ArgumentCaptor<String> errorMessageCaptor = ArgumentCaptor.forClass(String.class);
        verify(logger).logException(errorMessageCaptor.capture());
        String capturedErrorMessage = errorMessageCaptor.getValue();
        assertThat(capturedErrorMessage).isEqualTo("Input Data Missing");

        verify(courseRegistrationRepository, never()).findByCourseIdAndUserEmail(any(), any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldThrowWhenCourseIdIsNullWhenAddingMarksToACourse() {

        //given
        AddMarksRequest addMarksRequest = new AddMarksRequest(null, 70, "pasandevin@gmail.com");

        //when
        ResponseEntity response = underTest.addCourseMarks(addMarksRequest);

        //then
        ArgumentCaptor<String> errorMessageCaptor = ArgumentCaptor.forClass(String.class);
        verify(logger).logException(errorMessageCaptor.capture());
        String capturedErrorMessage = errorMessageCaptor.getValue();
        assertThat(capturedErrorMessage).isEqualTo("Input Data Missing");

        verify(courseRegistrationRepository, never()).findByCourseIdAndUserEmail(any(), any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldThrowWhenMarksAreNotSetWhenAddingMarksToACourse() {

        //given
        AddMarksRequest addMarksRequest = new AddMarksRequest("cf", -1, "pasandevin@gmail.com");

        //when
        ResponseEntity response = underTest.addCourseMarks(addMarksRequest);

        //then
        ArgumentCaptor<String> errorMessageCaptor = ArgumentCaptor.forClass(String.class);
        verify(logger).logException(errorMessageCaptor.capture());
        String capturedErrorMessage = errorMessageCaptor.getValue();
        assertThat(capturedErrorMessage).isEqualTo("Input Data Missing");

        verify(courseRegistrationRepository, never()).findByCourseIdAndUserEmail(any(), any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldGetMarksForACourse() {
        //given
        User user = new User("pasandevin@gmail.com", "Pasan", "Jayawardene", "student");
        Course course = new Course("cf", "Computer Fundamentals");
        CourseRegistration courseRegistration = new CourseRegistration(user, course);
        courseRegistration.setMarks(70);
        GetMarksRequest getMarksRequest = new GetMarksRequest("cf");

        //when
        when(userService.getTokenUser()).thenReturn(user);
        when(courseRegistrationRepository.findByCourseIdAndUserEmail(anyString(), anyString())).thenReturn(courseRegistration);
        ResponseEntity response = underTest.getMarksForACourse(getMarksRequest);

        //then
        ArgumentCaptor<String> courseIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        verify(courseRegistrationRepository).findByCourseIdAndUserEmail(courseIdCaptor.capture(), emailCaptor.capture());
        String email = emailCaptor.getValue();
        String courseId = courseIdCaptor.getValue();
        assertThat(email).isEqualTo(user.getEmail());
        assertThat(courseId).isEqualTo(getMarksRequest.getCourseId());

        Object body = response.getBody();
        GetMarksResponse result = ((GetMarksResponse) body);
        assertThat(result.getStudentEmail()).isEqualTo(courseRegistration.getUser().getEmail());
        assertThat(result.getCourseId()).isEqualTo(courseRegistration.getCourse().getId());
        assertThat(result.getMarks()).isEqualTo(courseRegistration.getMarks());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldThrowWhenCourseIdIsNullWhenGettingMarksForACourse() {
        //given
        User user = new User("pasandevin@gmail.com", "Pasan", "Jayawardene", "student");
        Course course = new Course("cf", "Computer Fundamentals");
        CourseRegistration courseRegistration = new CourseRegistration(user, course);
        courseRegistration.setMarks(70);
        GetMarksRequest getMarksRequest = new GetMarksRequest(null);

        //when
        ResponseEntity response = underTest.getMarksForACourse(getMarksRequest);

        //then
        ArgumentCaptor<String> errorMessageCaptor = ArgumentCaptor.forClass(String.class);
        verify(logger).logException(errorMessageCaptor.capture());
        String capturedErrorMessage = errorMessageCaptor.getValue();
        assertThat(capturedErrorMessage).isEqualTo("Course ID is Missing");

        verify(courseRegistrationRepository, never()).findByCourseIdAndUserEmail(any(), any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldGetStudentMarksForACourse() {
        //given
        User user = new User("pasandevin@gmail.com", "Pasan", "Jayawardene", "student");
        Course course = new Course("cf", "Computer Fundamentals");
        CourseRegistration courseRegistration = new CourseRegistration(user, course);
        courseRegistration.setMarks(70);
        GetStudentMarksRequest getStudentMarksRequest = new GetStudentMarksRequest(
                courseRegistration.getUser().getEmail(),
                courseRegistration.getCourse().getId()
        );

        //when
        when(courseRegistrationRepository.findByCourseIdAndUserEmail(anyString(), anyString())).thenReturn(courseRegistration);
        ResponseEntity response = underTest.getStudentMarksForACourse(getStudentMarksRequest);

        //then
        ArgumentCaptor<String> courseIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        verify(courseRegistrationRepository).findByCourseIdAndUserEmail(courseIdCaptor.capture(), emailCaptor.capture());
        String email = emailCaptor.getValue();
        String courseId = courseIdCaptor.getValue();
        assertThat(email).isEqualTo(getStudentMarksRequest.getStudentEmail());
        assertThat(courseId).isEqualTo(getStudentMarksRequest.getCourseId());

        Object body = response.getBody();
        GetMarksResponse result = ((GetMarksResponse) body);
        assertThat(result.getStudentEmail()).isEqualTo(courseRegistration.getUser().getEmail());
        assertThat(result.getCourseId()).isEqualTo(courseRegistration.getCourse().getId());
        assertThat(result.getMarks()).isEqualTo(courseRegistration.getMarks());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldThrowWhenCourseIdIsNullWhenGettingStudentMarksForACourse() {
        //given
        User user = new User("pasandevin@gmail.com", "Pasan", "Jayawardene", "student");
        Course course = new Course(null, "Computer Fundamentals");
        CourseRegistration courseRegistration = new CourseRegistration(user, course);
        courseRegistration.setMarks(70);
        GetStudentMarksRequest getStudentMarksRequest = new GetStudentMarksRequest(
                courseRegistration.getUser().getEmail(),
                courseRegistration.getCourse().getId()
        );

        //when
        ResponseEntity response = underTest.getStudentMarksForACourse(getStudentMarksRequest);

        //then
        ArgumentCaptor<String> errorMessageCaptor = ArgumentCaptor.forClass(String.class);
        verify(logger).logException(errorMessageCaptor.capture());
        String capturedErrorMessage = errorMessageCaptor.getValue();
        assertThat(capturedErrorMessage).isEqualTo("Student Email or Course ID is Missing");

        verify(courseRegistrationRepository, never()).findByCourseIdAndUserEmail(any(), any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldThrowWhenStudentEmailIsNullWhenGettingStudentMarksForACourse() {
        //given
        User user = new User(null, "Pasan", "Jayawardene", "student");
        Course course = new Course("cf", "Computer Fundamentals");
        CourseRegistration courseRegistration = new CourseRegistration(user, course);
        courseRegistration.setMarks(70);
        GetStudentMarksRequest getStudentMarksRequest = new GetStudentMarksRequest(
                courseRegistration.getUser().getEmail(),
                courseRegistration.getCourse().getId()
        );

        //when
        ResponseEntity response = underTest.getStudentMarksForACourse(getStudentMarksRequest);

        //then
        ArgumentCaptor<String> errorMessageCaptor = ArgumentCaptor.forClass(String.class);
        verify(logger).logException(errorMessageCaptor.capture());
        String capturedErrorMessage = errorMessageCaptor.getValue();
        assertThat(capturedErrorMessage).isEqualTo("Student Email or Course ID is Missing");

        verify(courseRegistrationRepository, never()).findByCourseIdAndUserEmail(any(), any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void getMarksForUser() {
    }

    @Test
    void getEnrolledStudents() {
    }
}