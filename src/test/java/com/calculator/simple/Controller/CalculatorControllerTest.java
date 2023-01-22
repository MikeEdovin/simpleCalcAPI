package com.calculator.simple.Controller;

import com.calculator.simple.Calculator.InfixToPostfix;
import com.calculator.simple.Calculator.ParsePostfix;
import com.calculator.simple.Mapping.Request;
import com.calculator.simple.Mapping.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    ParsePostfix pp;
    @MockBean
    InfixToPostfix itp;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void calculate() throws Exception {
        Request request=new Request("2+3*(1+2)");
        Response response=new Response(11.0);

        Mockito.when(pp.doParse(itp.transform(itp.separateNegative(request.getRequest())))).thenReturn(response.getResponse());
        mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response").value("11.0"))
                .andDo(print()).andReturn();
    }
    @Test
    void failedCalculate() throws Exception {
        Request request=new Request("s+3*(1+2)");
        Mockito.when(pp.doParse(itp.transform(itp.separateNegative(request.getRequest())))).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(post("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().is(418))
                .andDo(print()).andReturn();
    }
}