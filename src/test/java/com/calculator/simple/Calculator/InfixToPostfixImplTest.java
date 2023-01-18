package com.calculator.simple.Calculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InfixToPostfixImplTest {
    String in1="2+3*(1+2)";
    String in2="-2+3*(1+2)";
    String in3="2+3*(-3+2)";
    String in4="2+3-(-3)";
    String in5="2.1+3.5*(1+2)";
    String empty="";

    List<String>separatedOut2=List.of(new String[]{"-2", "+", "3", "*","(", "1", "+", "2",")"});

    List<String>out1= List.of(new String[]{"2", "3", "1", "2", "+", "*", "+"});
    List<String>out2= List.of(new String[]{"-2", "3", "1", "2", "+", "*", "+"});
    List<String>out3= List.of(new String[]{"2", "3", "-3", "2", "+", "*", "+" });
    List<String>out4= List.of(new String[]{"2", "3", "+", "-3", "-"});
    List<String>out5= List.of(new String[]{"2.1", "3.5", "1", "2", "+", "*", "+"});



    InfixToPostfix itp=new InfixToPostfixImpl();

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void transform() {
        assertEquals(out1, itp.transform(itp.separateNegative(in1)));
        assertEquals(out2, itp.transform(itp.separateNegative(in2)));
        assertEquals(out3, itp.transform(itp.separateNegative(in3)));
        assertEquals(out4, itp.transform(itp.separateNegative(in4)));
        assertEquals(out5, itp.transform(itp.separateNegative(in5)));

    }
        @Test
        void separateNegative(){
        assertEquals(separatedOut2,itp.separateNegative(in2));
            assertThrows(IllegalArgumentException.class, () -> {
                itp.separateNegative(empty);
            });
        }


    }

