package com.uok.backend.course;

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
class CourseControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    HttpHeaders headers = new HttpHeaders();

    @Test
    void registerNewCourse_newCourseDetailsGiven_shouldReturnHttp200() {

        headers.add(
                HttpHeaders.AUTHORIZATION,
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6InRlc3RsZWN0dXJlckB0ZXN0LmNvbSI" +
                        "sImZpcnN0TmFtZSI6ImZpcnN0TmFtZSIsImxhc3ROYW1lIjoibGFzdE5hbWUiLCJyb2xlIjoi" +
                        "bGVjdHVyZXIifQ.Kfl5jfcuJ_P8KARsRpYWyRNaV_gRC7dVlUjgXgOlqRds81SF2Di6u5bsTHr" +
                        "9nrLsv9lN4xlnwcN6b3zvmU0s4Q"
        );

        Course course = new Course("testcourseid2", "testCourseName2");

        HttpEntity<Course> entity = new HttpEntity<Course>(course, headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/course/addnewcourse"),
                HttpMethod.POST, entity, String.class);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void registerNewCourse_existingCourseDetailsGiven_shouldReturnHttp400() {

        headers.add(
                HttpHeaders.AUTHORIZATION,
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6InRlc3RsZWN0dXJlckB0ZXN0LmNvbSI" +
                        "sImZpcnN0TmFtZSI6ImZpcnN0TmFtZSIsImxhc3ROYW1lIjoibGFzdE5hbWUiLCJyb2xlIjoi" +
                        "bGVjdHVyZXIifQ.Kfl5jfcuJ_P8KARsRpYWyRNaV_gRC7dVlUjgXgOlqRds81SF2Di6u5bsTHr" +
                        "9nrLsv9lN4xlnwcN6b3zvmU0s4Q"
        );

        Course course = new Course("testcourseid", "testCourseName");

        HttpEntity<Course> entity = new HttpEntity<Course>(course, headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/course/addnewcourse"),
                HttpMethod.POST, entity, String.class);

        assertEquals(400, response.getStatusCodeValue());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}