package com.rodez.com.Controller.web;

import com.rodez.com.Entity.Intervention;
import com.rodez.com.Entity.Location;
import com.rodez.com.Entity.User;
import com.rodez.com.Service.InterventionService;
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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class SubmitDemandeController {
    @Autowired
    HttpSession session;
    @Autowired
    private InterventionService interventionService ;
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
            String id = (String) session.getAttribute("idUser");
            User user = userService.getByRegistration(id);
            intervention.setIdUser(user);
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); si besoinde faire le formatage des dates
            intervention.setCreationDate(timestamp);


            //Ajout de l'intervention dans la base de données.
            Intervention newIntervention = interventionService.createIntervention(intervention);
            Integer identifiant = newIntervention.getIdIntervention();
            String identifiantString = identifiant.toString(identifiant);

            while(identifiantString.length()<4 ){
                identifiantString= "0"+identifiantString;

            }
            model.addAttribute("intervention", newIntervention);
            model.addAttribute("identifiant_intervention" , identifiantString);

            return "demande_confirmation";
        }
    }
}
