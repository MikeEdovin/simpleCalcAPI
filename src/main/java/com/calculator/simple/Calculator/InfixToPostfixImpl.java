package com.calculator.simple.Calculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Service
public class InfixToPostfixImpl implements InfixToPostfix{
    private Stack<String> stack=new Stack<>();
    String delimiters = "(?<=[-+*/(),])(?=.)|(?<=.)(?=[-+*/(),])";


    @Override
    public List<String> separateNegative(String in){
        if(in==null||in==""){
            throw new IllegalArgumentException("String couldn't be empty");
        }
        List<String> input=new ArrayList<>();
        String[] array=in.split(delimiters);
        input.addAll(Arrays.asList(array));
        for(int i=0;i<input.size();i++){
            if(input.get(0).equals("-")){
                String negativeValue=input.get(0)+input.get(1);
                input.set(0,negativeValue);
                input.remove(1);
            }

            if(input.get(i).equals("-")&&input.get(i-1).equals("(")){
                String negativeValue=input.get(i)+input.get(i+1);
                input.set(i,negativeValue);
                input.remove(i+1);
            }
        }
        return input;
    }


    @Override
    public List<String> transform(List<String> input) {
        List<String> output=new ArrayList<>();
        for(int i=0;i< input.size();i++) {
            String item = input.get(i);
            switch (item){
                case "+":
                case "-":
                    gotOperator(item,1,output);
                    break;
                case "*":
                case "/":
                    gotOperator(item,2,output);
                    break;
                case "(":
                    stack.push(item);
                    break;
                case ")":
                    gotClosingBracket(item,output);
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
    public void gotOperator(String opThis, int priorityThis, List<String>output) {
        while(!stack.empty()){
            String opTop=stack.pop();
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
    public void gotClosingBracket(String bracket, List<String>output) {
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
