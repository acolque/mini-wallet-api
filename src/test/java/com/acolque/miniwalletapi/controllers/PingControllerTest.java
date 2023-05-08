package com.acolque.miniwalletapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PingControllerTest {

    @Autowired
    private PingController controller;

    @Test
    public void testPingController() {
        assertEquals("pong", controller.ping());
    }
}
