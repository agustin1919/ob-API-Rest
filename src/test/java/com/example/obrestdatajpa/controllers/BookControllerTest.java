package com.example.obrestdatajpa.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate =  new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void hello() {
        ResponseEntity<String> response =
                testRestTemplate.getForEntity("/holamundo", String.class);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Hola regordeta!!!!!!!",response.getBody());
    }

    @Test
    void findAll() {

    }

    @Test
    void findOneById() {
    }

    @Test
    void create() {
    }
}