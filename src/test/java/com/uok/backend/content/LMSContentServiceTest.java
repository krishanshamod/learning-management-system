package com.uok.backend.content;

import com.uok.backend.course.Course;
import com.uok.backend.course.CourseRepository;
import com.uok.backend.course.LMSCourseService;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {LMSContentService.class})
@ExtendWith(MockitoExtension.class)
class LMSContentServiceTest {

    @Autowired
    private LMSContentService underTest;

    @Mock
    private ContentRepository contentRepository ;
    @Mock
    private Logger logger;

    @BeforeEach
    void setUp() {
        underTest = new LMSContentService(contentRepository, logger);
    }

    @Test
    void shouldAddContentToACourse() {
        //given
        Content content = new Content(
                "cf", "Introduction",
                "In Computer Fundamentals we will focus on basic computing technologies"
        );

        //when
        ResponseEntity response = underTest.addContentToACourse(content);

        //then
        ArgumentCaptor<String> courseIdArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> contentTitleArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(contentRepository).findByCourseIdAndTitle(
                courseIdArgumentCaptor.capture(),
                contentTitleArgumentCaptor.capture()
        );
        String capturedCourseId = courseIdArgumentCaptor.getValue();
        String capturedContentTitle = contentTitleArgumentCaptor.getValue();
        assertThat(capturedCourseId).isEqualTo(content.getCourseId());
        assertThat(capturedContentTitle).isEqualTo(content.getTitle());

        ArgumentCaptor<Content> contentArgumentCaptor = ArgumentCaptor.forClass(Content.class);
        verify(contentRepository).save(contentArgumentCaptor.capture());
        Content capturedContent = contentArgumentCaptor.getValue();
        assertThat(capturedContent).isEqualTo(content);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldThrowWhenCourseIdIsMissingWhenAddingContentToACourse() {
        //given
        Content content = new Content(
                null,
                "Introduction",
                "In Computer Fundamentals we will focus on basic computing technologies"
        );

        //when
        ResponseEntity response = underTest.addContentToACourse(content);

        //then
        ArgumentCaptor<String> errorMessageCaptor = ArgumentCaptor.forClass(String.class);
        verify(logger).logException(errorMessageCaptor.capture());
        String capturedErrorMessage = errorMessageCaptor.getValue();
        assertThat(capturedErrorMessage).isEqualTo("Input Data Missing");

        verify(contentRepository, never()).findByCourseIdAndTitle(any(), any());
        verify(contentRepository, never()).save(any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldThrowWhenContentTitleIsMissingWhenAddingContentToACourse() {
        //given
        Content content = new Content(
                "cf",
                null,
                "In Computer Fundamentals we will focus on basic computing technologies"
        );

        //when
        ResponseEntity response = underTest.addContentToACourse(content);

        //then
        ArgumentCaptor<String> errorMessageCaptor = ArgumentCaptor.forClass(String.class);
        verify(logger).logException(errorMessageCaptor.capture());
        String capturedErrorMessage = errorMessageCaptor.getValue();
        assertThat(capturedErrorMessage).isEqualTo("Input Data Missing");

        verify(contentRepository, never()).findByCourseIdAndTitle(any(), any());
        verify(contentRepository, never()).save(any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldThrowWhenContentDescriptionIsMissingWhenAddingContentToACourse() {
        //given
        Content content = new Content("cf", "Introduction", null);

        //when
        ResponseEntity response = underTest.addContentToACourse(content);

        //then
        ArgumentCaptor<String> errorMessageCaptor = ArgumentCaptor.forClass(String.class);
        verify(logger).logException(errorMessageCaptor.capture());
        String capturedErrorMessage = errorMessageCaptor.getValue();
        assertThat(capturedErrorMessage).isEqualTo("Input Data Missing");

        verify(contentRepository, never()).findByCourseIdAndTitle(any(), any());
        verify(contentRepository, never()).save(any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldThrowWhenContentAlreadyExistsWhenAddingContentToACourse() {
        //given
        Content content = new Content(
                "cf",
                "Introduction",
                "In Computer Fundamentals we will focus on basic computing technologies"
        );

        //when
        when(contentRepository.findByCourseIdAndTitle(any(), any())).thenReturn(content);
        ResponseEntity response = underTest.addContentToACourse(content);

        //then
        ArgumentCaptor<String> errorMessageCaptor = ArgumentCaptor.forClass(String.class);
        verify(logger).logException(errorMessageCaptor.capture());
        String capturedErrorMessage = errorMessageCaptor.getValue();
        assertThat(capturedErrorMessage).isEqualTo("Content Already Exists");

        verify(contentRepository, never()).save(any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }

    @Test
    void shouldGetContentForACourse() {
        //given
        GetContentRequest getContentRequest = new GetContentRequest("cf");

        //when
        ResponseEntity response = underTest.getContentForACourse(getContentRequest);

        //then
        ArgumentCaptor<String> courseIdArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(contentRepository).findByCourseId(courseIdArgumentCaptor.capture());
        String capturedCourseId = courseIdArgumentCaptor.getValue();
        assertThat(capturedCourseId).isEqualTo(getContentRequest.getCourseId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldThrowWhenCourseIdIsMissingWhenGettingContentForACourse() {
        //given
        GetContentRequest getContentRequest = new GetContentRequest(null);

        //when
        ResponseEntity response = underTest.getContentForACourse(getContentRequest);

        //then
        ArgumentCaptor<String> errorMessageCaptor = ArgumentCaptor.forClass(String.class);
        verify(logger).logException(errorMessageCaptor.capture());
        String capturedErrorMessage = errorMessageCaptor.getValue();
        assertThat(capturedErrorMessage).isEqualTo("CourseId is Missing");

        verify(contentRepository, never()).findByCourseId(any());

        assertThat(response.getBody()).isNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}