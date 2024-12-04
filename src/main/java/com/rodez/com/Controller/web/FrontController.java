package com.rodez.com.Controller.web;

import com.rodez.com.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class FrontController {

    @GetMapping("/")
    public String displayHome(Model model){
        model.addAttribute("user", new User());
        return "index";
    }

}

