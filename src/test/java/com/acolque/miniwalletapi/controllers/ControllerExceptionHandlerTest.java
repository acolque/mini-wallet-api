package com.acolque.miniwalletapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerExceptionHandlerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private PingController pingController;

    @Test
    public void testRestClientExceptionHandled() {
        when(pingController.ping()).thenThrow(RestClientException.class);

        ResponseEntity<String> response = testRestTemplate.getForEntity("/ping", String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUnhandledException() {
        when(pingController.ping()).thenThrow(RuntimeException.class);

        ResponseEntity<String> response = testRestTemplate.getForEntity("/ping", String.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
