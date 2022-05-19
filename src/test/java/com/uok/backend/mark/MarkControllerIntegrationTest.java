package com.uok.backend.mark;

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
class MarkControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    HttpHeaders headers = new HttpHeaders();

    @Test
    void addMarks_markDetailsGiven_shouldReturnHttp200() {

        headers.add(
                HttpHeaders.AUTHORIZATION,
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6InRlc3RsZWN0dXJlckB0ZXN0LmNvbSI" +
                        "sImZpcnN0TmFtZSI6ImZpcnN0TmFtZSIsImxhc3ROYW1lIjoibGFzdE5hbWUiLCJyb2xlIjoi" +
                        "bGVjdHVyZXIifQ.Kfl5jfcuJ_P8KARsRpYWyRNaV_gRC7dVlUjgXgOlqRds81SF2Di6u5bsTHr" +
                        "9nrLsv9lN4xlnwcN6b3zvmU0s4Q"
        );

        AddMarksRequest addMarksRequest = new AddMarksRequest("testcourseid", 100, "teststudent@test.com");

        HttpEntity<AddMarksRequest> entity = new HttpEntity<AddMarksRequest>(addMarksRequest, headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/mark/addmarks"),
                HttpMethod.POST, entity, String.class);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void getMarksForCourse_courseIdGiven_shouldReturnHttp200() {

        headers.add(
                HttpHeaders.AUTHORIZATION,
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6InRlc3RzdHVkZW50QHRlc3QuY29t" +
                        "IiwiZmlyc3ROYW1lIjoiZmlyc3ROYW1lIiwibGFzdE5hbWUiOiJsYXN0TmFtZSIsInJvbGU" +
                        "iOiJzdHVkZW50In0.cvVfmD0me4ob6ekr_x-HnWaI786bQNkyTlLgYxtiFQwEPTpuWB9dS5" +
                        "JfRguZIT0fjW1SETVQRuJ4WkE9w-wlzg"
        );

        GetMarksRequest getMarksRequest = new GetMarksRequest("testcourseid");

        HttpEntity<GetMarksRequest> entity = new HttpEntity<GetMarksRequest>(getMarksRequest, headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/mark/getmarks"),
                HttpMethod.POST, entity, String.class);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void getStudentMarksForCourse_allDetailsGiven_shouldReturnHttp200() {

        headers.add(
                HttpHeaders.AUTHORIZATION,
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6InRlc3RsZWN0dXJlckB0ZXN0LmNvbSI" +
                        "sImZpcnN0TmFtZSI6ImZpcnN0TmFtZSIsImxhc3ROYW1lIjoibGFzdE5hbWUiLCJyb2xlIjoi" +
                        "bGVjdHVyZXIifQ.Kfl5jfcuJ_P8KARsRpYWyRNaV_gRC7dVlUjgXgOlqRds81SF2Di6u5bsTHr" +
                        "9nrLsv9lN4xlnwcN6b3zvmU0s4Q"
        );

        GetStudentMarksRequest getStudentMarksRequest = new GetStudentMarksRequest(
                "teststudent@test.com",
                "testcourseid"
        );

        HttpEntity<GetStudentMarksRequest> entity = new HttpEntity<GetStudentMarksRequest>(
                getStudentMarksRequest,
                headers
        );

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/mark/getstudentmarks"),
                HttpMethod.POST, entity, String.class);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void getMarksForUser_studentUserGiven_shouldReturnHttp200() {

        headers.add(
                HttpHeaders.AUTHORIZATION,
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6InRlc3RzdHVkZW50QHRlc3QuY29t" +
                        "IiwiZmlyc3ROYW1lIjoiZmlyc3ROYW1lIiwibGFzdE5hbWUiOiJsYXN0TmFtZSIsInJvbGU" +
                        "iOiJzdHVkZW50In0.cvVfmD0me4ob6ekr_x-HnWaI786bQNkyTlLgYxtiFQwEPTpuWB9dS5" +
                        "JfRguZIT0fjW1SETVQRuJ4WkE9w-wlzg"
        );

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/mark/getallmarks"),
                HttpMethod.GET, entity, String.class);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void getEnrolledStudents_courseIdGiven_shouldReturnHttp200() {

        headers.add(
                HttpHeaders.AUTHORIZATION,
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6InRlc3RsZWN0dXJlckB0ZXN0LmNvbSI" +
                        "sImZpcnN0TmFtZSI6ImZpcnN0TmFtZSIsImxhc3ROYW1lIjoibGFzdE5hbWUiLCJyb2xlIjoi" +
                        "bGVjdHVyZXIifQ.Kfl5jfcuJ_P8KARsRpYWyRNaV_gRC7dVlUjgXgOlqRds81SF2Di6u5bsTHr" +
                        "9nrLsv9lN4xlnwcN6b3zvmU0s4Q"
        );

        GetMarksRequest getMarksRequest = new GetMarksRequest("testcourseid");

        HttpEntity<GetMarksRequest> entity = new HttpEntity<GetMarksRequest>(getMarksRequest, headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/mark/getenrolledstudents"),
                HttpMethod.POST, entity, String.class);

        assertEquals(200, response.getStatusCodeValue());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}