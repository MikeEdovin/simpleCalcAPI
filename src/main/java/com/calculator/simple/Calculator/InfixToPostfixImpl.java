package com.calculator.simple.Calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPostfixImpl implements InfixToPostfix{
    private Stack<String> stack;
    private String[] input;
    private List<String> output;
    String delimiters = "(?<=[-+*/(),])(?=.)|(?<=.)(?=[-+*/(),])";

    public InfixToPostfixImpl(String in){
        this.stack=new Stack<>();
        this.input=in.split(delimiters);
        this.output=new ArrayList<>();
    }


    @Override
    public List<String> transform() {
        for(int i=0;i< input.length;i++){
            String item=input[i];
            System.out.println("item "+item);
            switch (item){
                case "+":
                case "-":
                    gotOperator(item,1);
                    break;
                case "*":
                case "/":
                    gotOperator(item,2);
                    break;
                case "(":
                    stack.push(item);
                    break;
                case ")":
                    gotClosingBracket(item);
                    break;
                default:
                    output.add(item);

            }
        }
        while(!stack.empty()){
            output.add(stack.pop());
        }
        return output;
    }

    @Override
    public void gotOperator(String opThis, int priorityThis) {
        while(!stack.empty()){
            String opTop=stack.pop();
            System.out.println("got "+opTop);
            if(opTop.equals("(")){
                stack.push(opTop);
                break;
            }
            else{
                int priorityTop;
                if(opTop.equals("+")||opTop.equals("-")){
                    priorityTop=1;
                }
                else {
                    priorityTop=2;
                }
                if(priorityTop<priorityThis){
                    stack.push(opTop);
                    break;
                }
                else{
                    output.add(opTop);
                }
            }
        }
        stack.push(opThis);

    }

    @Override
    public void gotClosingBracket(String bracket) {
        while (!stack.empty()){
            String item=stack.pop();
            if(item.equals("(")){
                break;
            }
            else{
                output.add(item);
            }
        }

    }
}
