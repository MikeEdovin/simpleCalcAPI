package com.calculator.simple.Calculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InfixToPostfixImplTest {
    String in="2.1+3.5*(1+2)";
    InfixToPostfix itp=new InfixToPostfixImpl(in);

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void transform() {
        List<String> out=itp.transform();
        for(String item:out) {
            System.out.print(item);
        }
    }
}