package com.rodez.com.Controller.web;

import com.rodez.com.Entity.Intervention;
import com.rodez.com.Service.InterventionService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class InterventionController {
    @Autowired
    InterventionService interventionService = new InterventionService();
    //admin view
    @GetMapping("/interventions_admin")
    public String getInterventionsAdmin(Model model){
        Iterable<Intervention> interventions = interventionService.getAll();
        model.addAttribute("interventionsList", interventions);
        return "interventions_admin";
    }
    //formateur view
    @GetMapping("/interventions_formateur")
    public String getInterventionsFormateur(Model model){
        Iterable<Intervention> interventions = interventionService.getAll();
        model.addAttribute("interventionsList", interventions);
        return "interventions_formateur";
    }
    //user view
    @GetMapping("/interventions_user/{id}")
    public String getInterventionsUser(@PathVariable("id") Integer id_user, Model model){
        // return juste les demandes  interventions faite  par l'utilisateur


        List<Intervention> requests = interventionService.getMyRequest ( id_user);


        model.addAttribute("myRequest", requests);
        return "interventions_user";


    }
}
