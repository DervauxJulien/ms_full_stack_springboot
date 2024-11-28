package com.rodez.com.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class FrontController {

    @GetMapping("/")
    public String displayHome(){
        return "index";
    }

}

