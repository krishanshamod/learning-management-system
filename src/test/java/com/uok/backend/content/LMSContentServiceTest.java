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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        Content content = new Content("cf", "Introduction", "In Computer Fundamentals we will focus on basic computing technologies");

        //when
        ResponseEntity response = underTest.addContentToACourse(content);

        //then
        ArgumentCaptor<Content> contentArgumentCaptor = ArgumentCaptor.forClass(Content.class);
        verify(contentRepository).save(contentArgumentCaptor.capture());
        Content capturedContent = contentArgumentCaptor.getValue();
        assertThat(capturedContent).isEqualTo(content);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getContentForACourse() {
    }
}