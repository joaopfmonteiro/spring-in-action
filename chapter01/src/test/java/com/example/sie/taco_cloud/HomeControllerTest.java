package com.example.sie.taco_cloud;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest(HomeController.class) //Web test for HomeController


public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc; //inject MockMvc

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))  //perform get
                .andExpect(status().isOk())   //expect http 200
                .andExpect(view().name("home"))  //expect home view
                .andExpect(content().string(containsString("Welcome to..."))); //expect welcome to

    }
}
