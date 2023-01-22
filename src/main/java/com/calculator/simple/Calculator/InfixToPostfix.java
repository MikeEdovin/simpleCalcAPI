package com.calculator.simple.Calculator;

import java.util.List;

public interface InfixToPostfix {
    List<String> separateNegative(String in);
    List<String> transform(List<String>input);
    void gotOperator(String opThis, int priorityThis,List<String>output);
    void gotClosingBracket(String bracket,List<String>output);
}
