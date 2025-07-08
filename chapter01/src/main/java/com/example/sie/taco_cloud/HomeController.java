package com.example.sie.taco_cloud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//the controller
//@Controller, Its primary purpose is to identify this class as a component for component scanning.Spring’s
//component scanning automatically discovers it and creates an instance of HomeController as a bean in the Spring application context.
//@Controller, @Service and @Repository have the same purpose.
@Controller
public class HomeController {

    //Handles request for the root path
    @GetMapping("/")
    public String home (){
        return "home";
        //return the view name
    }
}
