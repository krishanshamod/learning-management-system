package com.uok.backend.announcement;

import com.uok.backend.BackendApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AnnouncementControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    HttpHeaders headers = new HttpHeaders();

    @Test
    void addAnnouncement_newAnnouncementDetailsGiven_shouldReturnHttp200() {

        headers.add(
                HttpHeaders.AUTHORIZATION,
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6InRlc3RsZWN0dXJlckB0ZXN0LmNvbSI" +
                        "sImZpcnN0TmFtZSI6ImZpcnN0TmFtZSIsImxhc3ROYW1lIjoibGFzdE5hbWUiLCJyb2xlIjoi" +
                        "bGVjdHVyZXIifQ.Kfl5jfcuJ_P8KARsRpYWyRNaV_gRC7dVlUjgXgOlqRds81SF2Di6u5bsTHr" +
                        "9nrLsv9lN4xlnwcN6b3zvmU0s4Q"
        );

        Announcement announcement = new Announcement("testcourseid", "testAnnouncementTitle1", "testAnnouncementContent1");

        HttpEntity<Announcement> entity = new HttpEntity<Announcement>(announcement, headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/announcement/addannouncement"),
                HttpMethod.POST, entity, String.class);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void addAnnouncement_existingAnnouncementDetailsGiven_shouldReturnHttp400() {

        headers.add(
                HttpHeaders.AUTHORIZATION,
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6InRlc3RsZWN0dXJlckB0ZXN0LmNvbSI" +
                        "sImZpcnN0TmFtZSI6ImZpcnN0TmFtZSIsImxhc3ROYW1lIjoibGFzdE5hbWUiLCJyb2xlIjoi" +
                        "bGVjdHVyZXIifQ.Kfl5jfcuJ_P8KARsRpYWyRNaV_gRC7dVlUjgXgOlqRds81SF2Di6u5bsTHr" +
                        "9nrLsv9lN4xlnwcN6b3zvmU0s4Q"
        );

        Announcement announcement = new Announcement("testcourseid", "testAnnouncementTitle", "testAnnouncementContent");

        HttpEntity<Announcement> entity = new HttpEntity<Announcement>(announcement, headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/announcement/addannouncement"),
                HttpMethod.POST, entity, String.class);

        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    void getAnnouncementsForACourse_courseIdGiven_shouldReturnHttp200() {

        headers.add(
                HttpHeaders.AUTHORIZATION,
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6InRlc3RzdHVkZW50QHRlc3QuY29t" +
                        "IiwiZmlyc3ROYW1lIjoiZmlyc3ROYW1lIiwibGFzdE5hbWUiOiJsYXN0TmFtZSIsInJvbGU" +
                        "iOiJzdHVkZW50In0.cvVfmD0me4ob6ekr_x-HnWaI786bQNkyTlLgYxtiFQwEPTpuWB9dS5" +
                        "JfRguZIT0fjW1SETVQRuJ4WkE9w-wlzg"
        );

        GetAnnouncementRequest request = new GetAnnouncementRequest("testcourseid");

        HttpEntity<GetAnnouncementRequest> entity = new HttpEntity<GetAnnouncementRequest>(request, headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/announcement/getannouncements"),
                HttpMethod.POST, entity, String.class);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void getNotification_existingAnnouncementDetailsGiven_shouldReturnHttp200() {

        headers.add(
                HttpHeaders.AUTHORIZATION,
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6InRlc3RzdHVkZW50QHRlc3QuY29t" +
                        "IiwiZmlyc3ROYW1lIjoiZmlyc3ROYW1lIiwibGFzdE5hbWUiOiJsYXN0TmFtZSIsInJvbGU" +
                        "iOiJzdHVkZW50In0.cvVfmD0me4ob6ekr_x-HnWaI786bQNkyTlLgYxtiFQwEPTpuWB9dS5" +
                        "JfRguZIT0fjW1SETVQRuJ4WkE9w-wlzg"
        );

        GetNotificationRequest request = new GetNotificationRequest("testcourseid", "testAnnouncementTitle");

        HttpEntity<GetNotificationRequest> entity = new HttpEntity<GetNotificationRequest>(request, headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/announcement/getnotification"),
                HttpMethod.POST, entity, String.class);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void getNotification_notExistingAnnouncementDetailsGiven_shouldReturnHttp400() {

        headers.add(
                HttpHeaders.AUTHORIZATION,
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6InRlc3RzdHVkZW50QHRlc3QuY29t" +
                        "IiwiZmlyc3ROYW1lIjoiZmlyc3ROYW1lIiwibGFzdE5hbWUiOiJsYXN0TmFtZSIsInJvbGU" +
                        "iOiJzdHVkZW50In0.cvVfmD0me4ob6ekr_x-HnWaI786bQNkyTlLgYxtiFQwEPTpuWB9dS5" +
                        "JfRguZIT0fjW1SETVQRuJ4WkE9w-wlzg"
        );

        GetNotificationRequest request = new GetNotificationRequest("testcourseid", "testAnnouncementTitle2");

        HttpEntity<GetNotificationRequest> entity = new HttpEntity<GetNotificationRequest>(request, headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/announcement/getnotification"),
                HttpMethod.POST, entity, String.class);

        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    void getNotificationsForAUser_userDetailsGiven_shouldReturnHttp200() {

        headers.add(
                HttpHeaders.AUTHORIZATION,
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6InRlc3RzdHVkZW50QHRlc3QuY29t" +
                        "IiwiZmlyc3ROYW1lIjoiZmlyc3ROYW1lIiwibGFzdE5hbWUiOiJsYXN0TmFtZSIsInJvbGU" +
                        "iOiJzdHVkZW50In0.cvVfmD0me4ob6ekr_x-HnWaI786bQNkyTlLgYxtiFQwEPTpuWB9dS5" +
                        "JfRguZIT0fjW1SETVQRuJ4WkE9w-wlzg"
        );

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/announcement/getnotifications"),
                HttpMethod.GET, entity, String.class);

        assertEquals(200, response.getStatusCodeValue());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}