package com.rodez.com.Controller.web;

import com.rodez.com.Entity.Intervention;
import com.rodez.com.Service.InterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InterventionController {
    @Autowired
    InterventionService interventionService = new InterventionService();
    @GetMapping("/interventions")
    public String getInterventions(Model model){
        Iterable<Intervention> interventions = interventionService.getAll();
        model.addAttribute("interventions", interventions);
        return "interventions";
    }



}
