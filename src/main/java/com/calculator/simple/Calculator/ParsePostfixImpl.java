package com.calculator.simple.Calculator;

import java.util.List;
import java.util.Stack;

public class ParsePostfixImpl implements ParsePostfix{
    private Stack<Double> stack;

    public ParsePostfixImpl(){
        this.stack=new Stack<>();
    }

    @Override
    public double doParse(List<String>input) {
        try {
            String item;
            int j;
            double num1, num2, answer;
            for (j = 0; j < input.size(); j++) {
                item = input.get(j);
                if (!isOperator(item)) {
                    stack.push(Double.parseDouble(item));
                } else {
                    num2 = stack.pop();
                    num1 = stack.pop();
                    switch (item) {
                        case "+":
                            answer = num1 + num2;
                            break;
                        case "-":
                            answer = num1 - num2;
                            break;
                        case "*":
                            answer = num1 * num2;
                            break;
                        case "/":
                            answer = num1 / num2;
                            break;
                        default:
                            answer = 0;
                    }
                    stack.push(answer);
                }
            }
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Not a math expression");
        }
            return stack.pop();

    }

    private boolean isOperator(String item){
        return item.equals("+")||item.equals("-")||item.equals("*")||item.equals("/");

    }
}
