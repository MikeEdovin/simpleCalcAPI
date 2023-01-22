package com.calculator.simple.Controller;

import com.calculator.simple.Calculator.InfixToPostfix;
import com.calculator.simple.Calculator.ParsePostfix;
import com.calculator.simple.Mapping.Request;
import com.calculator.simple.Mapping.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @Autowired
    InfixToPostfix itp;
    @Autowired
    ParsePostfix pp;

    @PostMapping("/calculate")
    public ResponseEntity calculate(@RequestBody Request request){
        try{
            double answer=pp.doParse(itp.transform(itp.separateNegative(request.getRequest())));
            Response response=new Response(answer);
            return ResponseEntity.ok().body(response);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(418).body("Not a math expression");
        }
    }
}
