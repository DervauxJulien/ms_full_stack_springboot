package com.rodez.com.Controller.api;

import com.rodez.com.Entity.Intervention;
import com.rodez.com.Entity.User;
import com.rodez.com.Service.InterventionService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InterventionRestController {
    @Autowired
    InterventionService interventionService = new InterventionService();

    @GetMapping("/interventions_admin_rest")
    public Iterable<Intervention> interventionList (){
        return interventionService.getAll();
    }
    @GetMapping("/interventions_admin_rest/{id}")
    public Optional<Intervention> interventionId (@PathVariable("id") Integer id){
        return interventionService.getById(id);
    }

    @PostMapping("/create_intervention_rest")

    public Intervention createIntervention(@RequestBody Intervention intervention){
        return interventionService.createIntervention(intervention);

    }


}
