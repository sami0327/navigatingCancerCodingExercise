package com.navigatingcancer.codingexercise.controller;

import com.navigatingcancer.codingexercise.model.BasicMathResult;
import com.navigatingcancer.codingexercise.model.MathOperation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = RANDOM_PORT
)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MathsControllerIT {


    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void givenTwoAndTwo_shouldReturnFour(){
        int param1 = 2;
        int param2 = 2;
        String url = String.format("/math/add?parameter1=%d&parameter2=%d", param1, param2);

        ResponseEntity<BasicMathResult> result = testRestTemplate.getForEntity(url, BasicMathResult.class);

        assertNotNull(result);
        BasicMathResult mathResult = result.getBody();
        assertNotNull(mathResult);
        assertEquals(mathResult.getParameter1(), param1);
        assertEquals(mathResult.getParameter2(), param2);
        assertEquals(mathResult.getOperation(), MathOperation.ADDITION);
        assertEquals(4, mathResult.getResult(), "result doesn't match expected 4");
    }


    @Test
    void givenTwoAndFive_shouldReturnSeven(){
        int param1 = 2;
        int param2 = 5;
        String url = String.format("/math/add?parameter1=%d&parameter2=%d", param1, param2);

        ResponseEntity<BasicMathResult> result = testRestTemplate.getForEntity(url, BasicMathResult.class);

        assertNotNull(result);
        BasicMathResult mathResult = result.getBody();
        assertNotNull(mathResult);
        assertEquals(mathResult.getParameter1(), param1);
        assertEquals(mathResult.getParameter2(), param2);
        assertEquals(mathResult.getOperation(), MathOperation.ADDITION);
        assertEquals(7, mathResult.getResult(), "result doesn't match expected 7");
    }

    @Test
    void givenTwoAndFive_shouldReturnNegThree(){
        int param1 = 2;
        int param2 = 5;
        String url = String.format("/math/subtract?parameter1=%d&parameter2=%d", param1, param2);

        ResponseEntity<BasicMathResult> result = testRestTemplate.getForEntity(url, BasicMathResult.class);

        assertNotNull(result);
        BasicMathResult mathResult = result.getBody();
        assertNotNull(mathResult);
        assertEquals(mathResult.getParameter1(), param1);
        assertEquals(mathResult.getParameter2(), param2);
        assertEquals(mathResult.getOperation(), MathOperation.SUBTRACTION);
        assertEquals(-3, mathResult.getResult(), "result doesn't match expected 7");
    }

    @Test
    void givenSubtractionOverflowResult_shouldThrowError(){
        int param1 = Integer.MIN_VALUE;
        int param2 = 5;
        String url = String.format("/math/subtract?parameter1=%d&parameter2=%d", param1, param2);

        ResponseEntity<BasicMathResult> result = testRestTemplate.getForEntity(url, BasicMathResult.class);

        assertEquals(result.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void givenAdditionOverflowResult_shouldThrowError(){
        int param1 = Integer.MAX_VALUE;
        int param2 = 5;
        String url = String.format("/math/add?parameter1=%d&parameter2=%d", param1, param2);

        ResponseEntity<BasicMathResult> result = testRestTemplate.getForEntity(url, BasicMathResult.class);

        assertEquals(result.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
