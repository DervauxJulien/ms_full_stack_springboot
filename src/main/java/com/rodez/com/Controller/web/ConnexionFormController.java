package com.rodez.com.Controller.web;

import com.rodez.com.Entity.Intervention;
import com.rodez.com.Entity.Location;
import com.rodez.com.Entity.User;
import com.rodez.com.Service.LocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/*
    classe controle du formulaire de demande d'intervention
 */
@Controller
public class ConnexionFormController {
    @Autowired
    HttpSession session;
    @Autowired
    private LocationService locationService;


    @PostMapping( "/submitForm")
    public String askConnexion(@Valid @ModelAttribute ("user") User user, BindingResult bindingResult, Model model){
        boolean authorizedToConnect = true;
        // check if user authorized to connect and set authorized
        if(bindingResult.hasErrors()){
            return "index";
        }
        else{

            if(authorizedToConnect){
                session.setAttribute("user", user);
                Intervention intervention = new Intervention();
                Iterable<Location> locations = locationService.getLocation();
                model.addAttribute("intervention", intervention);
                model.addAttribute("locationList", locations);
                return  "demande_intervention";

            }
            else {
                return "index";
            }
        }

    }
}
