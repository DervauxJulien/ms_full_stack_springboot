package com.rodez.com.Controller.web;

import com.rodez.com.Entity.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/*
    classe controle du formulaire de demande d'intervention
 */
@Controller
public class ConnexionFormController {
    @PostMapping( "/submitForm")
    public String askConnexion(@Valid @ModelAttribute ("user") User user, BindingResult bindingResult){
        boolean authorizedToConnect = true;
        // check if user authorized to connect and set authorized
        if(bindingResult.hasErrors()){
            return "index";
        }
        else{
            if(authorizedToConnect){
                return  "demande_intervention";

            }
            else {
                return "index";
            }
        }

    }
}
