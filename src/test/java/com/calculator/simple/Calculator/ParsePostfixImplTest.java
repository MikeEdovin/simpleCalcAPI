package com.calculator.simple.Calculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParsePostfixImplTest {
    List<String>in1= List.of(new String[]{"2", "3", "1", "2", "+", "*", "+"});
    List<String>in2= List.of(new String[]{"-2", "3", "1", "2", "+", "*", "+"});
    List<String>in3= List.of(new String[]{"2", "3", "-3", "2", "+", "*", "+" });
    List<String>in4= List.of(new String[]{"2", "3", "+", "-3", "-"});
    List<String>in5= List.of(new String[]{"2.1", "3.5", "1", "2", "+", "*", "+"});
    List<String>wrongInput= List.of(new String[]{"s", "3.5", "1", "2", "+", "*", "+"});



    ParsePostfixImpl ppi=new ParsePostfixImpl();
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void doParse() {
        assertEquals(11,ppi.doParse(in1));
        assertEquals(7,ppi.doParse(in2));
        assertEquals(-1,ppi.doParse(in3));
        assertEquals(8,ppi.doParse(in4));
        assertEquals(12.6,ppi.doParse(in5));
        assertThrows(IllegalArgumentException.class,()->{
            ppi.doParse(wrongInput);
        });
    }
}