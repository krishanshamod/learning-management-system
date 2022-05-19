package com.uok.backend.user;

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
class UserControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    HttpHeaders headers = new HttpHeaders();

    @Test
    void getUser_shouldReturnHttp200() {

        headers.add(
                HttpHeaders.AUTHORIZATION,
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6InRlc3RzdHVkZW50QHRlc3QuY29t" +
                        "IiwiZmlyc3ROYW1lIjoiZmlyc3ROYW1lIiwibGFzdE5hbWUiOiJsYXN0TmFtZSIsInJvbGU" +
                        "iOiJzdHVkZW50In0.cvVfmD0me4ob6ekr_x-HnWaI786bQNkyTlLgYxtiFQwEPTpuWB9dS5" +
                        "JfRguZIT0fjW1SETVQRuJ4WkE9w-wlzg"
        );

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/user/getuser"),
                HttpMethod.GET, entity, String.class);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void addUser_shouldReturnHttp200() {

        headers.add(
                HttpHeaders.AUTHORIZATION,
                "Bearer eyJhbGciOiJIUzUxMiJ9.eyJlbWFpbCI6InRlc3RzdHVkZW50QHRlc3QuY29t" +
                        "IiwiZmlyc3ROYW1lIjoiZmlyc3ROYW1lIiwibGFzdE5hbWUiOiJsYXN0TmFtZSIsInJvbGU" +
                        "iOiJzdHVkZW50In0.cvVfmD0me4ob6ekr_x-HnWaI786bQNkyTlLgYxtiFQwEPTpuWB9dS5" +
                        "JfRguZIT0fjW1SETVQRuJ4WkE9w-wlzg"
        );

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/user/adduser"),
                HttpMethod.POST, entity, String.class);

        assertEquals(200, response.getStatusCodeValue());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}