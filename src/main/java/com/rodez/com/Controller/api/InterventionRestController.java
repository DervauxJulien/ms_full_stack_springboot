package com.rodez.com.Controller.api;

import com.rodez.com.Entity.Intervention;
import com.rodez.com.Entity.Location;
import com.rodez.com.Entity.User;
import com.rodez.com.Service.InterventionService;
import com.rodez.com.Service.LocationService;
import com.rodez.com.Service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
public class InterventionRestController {
    @Autowired
    LocationService locationService;
    @Autowired
    UserService userService;
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

    @GetMapping("/checkUser")
    public Integer connect(@RequestBody User user){
        Integer check = 0;
        User userDatabase = userService.getByRegistration(user.getRegistration());
        if(userDatabase != null){
            check = userDatabase.getIdUser();
        }
        return check;
    }

    @PostMapping("/create_intervention_rest")
    public Intervention createIntervention(@RequestBody Map<String, Object> requestBody){
        Intervention intervention = new Intervention();
        User user = userService.getUserById((Integer) requestBody.get("idUser")).get();
        Location location = locationService.getLocationById((Integer) requestBody.get("idLocation")).get();
        intervention.setIdUser(user);
        intervention.setIdLocation(location);
        intervention.setDescription((String) requestBody.get("description"));
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        intervention.setCreationDate(timestamp);
        return interventionService.createIntervention(intervention);
    }



}
