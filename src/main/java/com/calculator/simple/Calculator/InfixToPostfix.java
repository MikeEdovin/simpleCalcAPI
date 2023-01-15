package com.calculator.simple.Calculator;

import java.util.List;

public interface InfixToPostfix {
    List<String> transform();
    void gotOperator(String opThis, int priorityThis);
    void gotClosingBracket(String bracket);
}
