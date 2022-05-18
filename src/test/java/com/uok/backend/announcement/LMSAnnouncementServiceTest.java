package com.uok.backend.announcement;

import com.uok.backend.announcement.email.EmailService;
import com.uok.backend.content.Content;
import com.uok.backend.content.ContentRepository;
import com.uok.backend.content.LMSContentService;
import com.uok.backend.course.registration.CourseRegistrationRepository;
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
import static org.mockito.Mockito.never;

@ContextConfiguration(classes = {LMSAnnouncementService.class})
@ExtendWith(MockitoExtension.class)
class LMSAnnouncementServiceTest {

    @Autowired
    private LMSAnnouncementService underTest;

    @Mock
    private AnnouncementRepository announcementRepository;

    @Mock
    private CourseRegistrationRepository courseRegistrationRepository;
    @Mock
    private Logger logger;

    @Mock
    private EmailService emailService;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        underTest = new LMSAnnouncementService(
                announcementRepository,
                courseRegistrationRepository,
                logger,
                emailService,
                userService
        );
    }

    @Test
    void shouldAddAnnouncement() {
        //given
        Announcement announcement = new Announcement(
                "cf",
                "New Assignment",
                "Findout about different Computing generations"
        );

        //when
        when(announcementRepository.findByCourseIdAndTitle(any(), any())).thenReturn(null);
        ResponseEntity response = underTest.addAnnouncement(announcement);

        //then
        ArgumentCaptor<String> courseIdArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> announcementTitleArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(announcementRepository).findByCourseIdAndTitle(
                courseIdArgumentCaptor.capture(),
                announcementTitleArgumentCaptor.capture()
        );
        String capturedCourseId = courseIdArgumentCaptor.getValue();
        String capturedAnnouncementTitle = announcementTitleArgumentCaptor.getValue();
        assertThat(capturedCourseId).isEqualTo(announcement.getCourseId());
        assertThat(capturedAnnouncementTitle).isEqualTo(announcement.getTitle());

        ArgumentCaptor<Announcement> announcementArgumentCaptor = ArgumentCaptor.forClass(Announcement.class);
        verify(emailService).setAnnouncement(announcementArgumentCaptor.capture());
        Announcement capturedAnnouncement = announcementArgumentCaptor.getValue();
        assertThat(capturedAnnouncement).isEqualTo(announcement);

        verify(announcementRepository).save(announcementArgumentCaptor.capture());
        capturedAnnouncement = announcementArgumentCaptor.getValue();
        assertThat(capturedAnnouncement).isEqualTo(announcement);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldThrowWhenCourseIdIsMissingWhenAddingAnnouncement() {
        //given
        Announcement announcement = new Announcement(
                null,
                "New Assignment",
                "Findout about different Computing generations"
        );

        //when
        ResponseEntity response = underTest.addAnnouncement(announcement);

        //then
        ArgumentCaptor<String> errorMessageCaptor = ArgumentCaptor.forClass(String.class);
        verify(logger).logException(errorMessageCaptor.capture());
        String capturedErrorMessage = errorMessageCaptor.getValue();
        assertThat(capturedErrorMessage).isEqualTo("Input Data missing");

        verify(announcementRepository, never()).findByCourseIdAndTitle(any(), any());
        verify(emailService, never()).setAnnouncement(any());
        verify(announcementRepository, never()).save(any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldThrowWhenAnnouncementTitleIsMissingWhenAddingAnnouncement() {
        //given
        Announcement announcement = new Announcement(
                "cf",
                null,
                "Findout about different Computing generations"
        );

        //when
        ResponseEntity response = underTest.addAnnouncement(announcement);

        //then
        ArgumentCaptor<String> errorMessageCaptor = ArgumentCaptor.forClass(String.class);
        verify(logger).logException(errorMessageCaptor.capture());
        String capturedErrorMessage = errorMessageCaptor.getValue();
        assertThat(capturedErrorMessage).isEqualTo("Input Data missing");

        verify(announcementRepository, never()).findByCourseIdAndTitle(any(), any());
        verify(emailService, never()).setAnnouncement(any());
        verify(announcementRepository, never()).save(any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldThrowWhenAnnouncementContentIsMissingWhenAddingAnnouncement() {
        //given
        Announcement announcement = new Announcement(
                "cf",
                "New Assignment",
                null
        );

        //when
        ResponseEntity response = underTest.addAnnouncement(announcement);

        //then
        ArgumentCaptor<String> errorMessageCaptor = ArgumentCaptor.forClass(String.class);
        verify(logger).logException(errorMessageCaptor.capture());
        String capturedErrorMessage = errorMessageCaptor.getValue();
        assertThat(capturedErrorMessage).isEqualTo("Input Data missing");

        verify(announcementRepository, never()).findByCourseIdAndTitle(any(), any());
        verify(emailService, never()).setAnnouncement(any());
        verify(announcementRepository, never()).save(any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void shouldThrowWhenAnnouncementAlreadyExistsWhenAddingAnnouncement() {
        //given
        Announcement announcement = new Announcement(
                "cf",
                "New Assignment",
                "Findout about different Computing generations"
        );


        //when
        when(announcementRepository.findByCourseIdAndTitle(any(), any())).thenReturn(announcement);
        ResponseEntity response = underTest.addAnnouncement(announcement);


        //then
        ArgumentCaptor<String> courseIdArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> announcementTitleArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(announcementRepository).findByCourseIdAndTitle(
                courseIdArgumentCaptor.capture(),
                announcementTitleArgumentCaptor.capture()
        );
        String capturedCourseId = courseIdArgumentCaptor.getValue();
        String capturedAnnouncementTitle = announcementTitleArgumentCaptor.getValue();
        assertThat(capturedCourseId).isEqualTo(announcement.getCourseId());
        assertThat(capturedAnnouncementTitle).isEqualTo(announcement.getTitle());


        ArgumentCaptor<String> errorMessageCaptor = ArgumentCaptor.forClass(String.class);
        verify(logger).logException(errorMessageCaptor.capture());
        String capturedErrorMessage = errorMessageCaptor.getValue();
        assertThat(capturedErrorMessage).isEqualTo("Announcement already exists");


        verify(emailService, never()).setAnnouncement(any());
        verify(announcementRepository, never()).save(any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void getAnnouncementsForACourse() {
    }

    @Test
    void getNotification() {
    }

    @Test
    void getNotificationsForAUser() {
    }
}