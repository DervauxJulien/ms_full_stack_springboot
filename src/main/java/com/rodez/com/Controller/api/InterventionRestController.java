package com.rodez.com.Controller.api;

import com.rodez.com.Entity.Intervention;
import com.rodez.com.Entity.Location;
import com.rodez.com.Entity.User;
import com.rodez.com.Service.InterventionService;
import com.rodez.com.Service.LocationService;
import com.rodez.com.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @PostMapping("/checkUser")
    public Integer connect(@RequestBody User user){
        Integer check = 0;
        Iterable<User> users = userService.listAll();
        List<User> userList = new ArrayList<User>();
        users.forEach(userList::add);
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getRegistration().equals(user.getRegistration()) && userList.get(i).getLastname().equals(user.getLastname()) && userList.get(i).getFirstname().equals(user.getFirstname())) {
                check = userList.get(i).getIdUser();
            }
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

    @PostMapping("/login")
    public Iterable<Intervention> login(@RequestBody User user){
        Integer idUser = 0;
        Iterable<User> users = userService.listAll();
        List<User> userList = new ArrayList<User>();
        users.forEach(userList::add);
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getRegistration().equals(user.getRegistration()) && userList.get(i).getPasswordUser().equals(user.getPasswordUser())) {
                idUser = userList.get(i).getIdUser();
            }
        }

        Iterable<Intervention> listInterventions = null;
        if(idUser!= 0){
            if(user.getRegistration().charAt(0) == 'A' || user.getRegistration().charAt(0) == 'F'){
                listInterventions = interventionService.getAll();
            }
            else{
                listInterventions = interventionService.getMyRequest(idUser);
            }
        }
        return listInterventions;
    }
}
