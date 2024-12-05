package com.rodez.com.Controller.web;

import com.rodez.com.Entity.Intervention;
import com.rodez.com.Entity.Location;
import com.rodez.com.Entity.User;
import com.rodez.com.Service.LocationService;
import com.rodez.com.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;
import java.awt.*;
import java.util.Optional;

@Controller
public class SubmitDemandeController {
    @Autowired
    HttpSession session;

    @Autowired
    private LocationService locationService;
    @Autowired
    private UserService userService;
    @PostMapping("/submitDemande")
    public String confirmdemande (@Valid @ModelAttribute ("intervention") Intervention intervention, BindingResult bindingResult, Model model) {

        // check some critère to submit demande before redirection to confimation page
        // générer le code de suivi de la demande et l'injecter dans la page de confirmation
        if (bindingResult.hasErrors()) {
            Iterable<Location> locations = locationService.getLocation();
            model.addAttribute("locationList", locations);
            return "demande_intervention";
        } else {
            Integer id = (Integer) session.getAttribute("idUser");
            Optional<User> user = userService.getUserById(id);
            intervention.setIdUser(user.get());
            model.addAttribute("intervention", intervention);
            return "demande_confirmation";
        }
    }
}