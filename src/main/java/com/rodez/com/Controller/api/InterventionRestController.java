package com.rodez.com.Controller.api;

import com.rodez.com.Entity.Intervention;
import com.rodez.com.Entity.Location;
import com.rodez.com.Entity.User;
import com.rodez.com.Service.InterventionService;
import com.rodez.com.Service.LocationService;
import com.rodez.com.Service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
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

    @PostMapping("/intervention_update_priority")
    public String updatePriority(@RequestBody Map<String, Object> requestBody){
        Intervention intervention = interventionService.getIntervention((Integer) requestBody.get("idIntervention"));
        intervention.setPriority((String) requestBody.get("priority"));
        interventionService.updateIntervention(intervention);
        return "Priorité modifiée";
    }

    @PutMapping("/intervention_update_status")
    public String updateStatus(@RequestBody Map<String, Object> requestBody){
        Intervention intervention = interventionService.getIntervention((Integer) requestBody.get("idIntervention"));
        String status = (String) requestBody.get("status");
        System.out.println(status);
        if(status.equals("en cours") || status.equals("réalisé") || status.equals("en attente") || status.equals("traité")){
            intervention.setStatus(status);
            if(status.equals("réalisé"))
                intervention.setIdIntervenant(null);
            interventionService.updateIntervention(intervention);
        }
        else{
            status = "Mauvais status";
        }

        return status;
    }
    @PostMapping("/intervention_update_intervenant")
    public String updateIntervenant(@RequestBody Map<String, Object> requestBody){
        Intervention intervention = interventionService.getIntervention((Integer) requestBody.get("idIntervention"));
        Optional<User> opt = null;
        User user = null;

        Timestamp timestamp = null;
        String s = "Intervenant null";

        if(requestBody.get("idIntervenant") != null) {
            opt = userService.getUserById((Integer) requestBody.get("idIntervenant"));
//        System.out.println("--------------------"+opt.isPresent());
            if(opt.isPresent()){
                user = opt.get();
            }
            if(user != null && (user.getRoleUser().equals("admin") || user.getRoleUser().equals("formateur"))){
                intervention.setIdIntervenant(user);
                timestamp = Timestamp.valueOf(LocalDateTime.now());
                s= "Intervenant modifié";
            }
            else{
                s = "Intervenant pas dans la base";
            }
        }



        intervention.setAffectationDate(timestamp);
        interventionService.updateIntervention(intervention);
        return s;
    }
    @PostMapping("/interventions_user")
    public List<Object> interventionsList(@RequestBody Map<String, Object> requestBody){
        User user = userService.getUserById((Integer) requestBody.get("idUser")).get();


        Iterable<Intervention> listInterventions = null;
        List<Object> list = null;
        if(user.getIdUser()!= 0){
            list = new ArrayList<>();
            if(user.getRegistration().charAt(0) == 'A' || user.getRegistration().charAt(0) == 'F'){
                listInterventions = interventionService.getAll();
                list.add(listInterventions);
                list.add(user.getIdUser());
            }
            else{
                listInterventions = interventionService.getMyRequest(user.getIdUser());
                list.add(listInterventions);
                list.add(user.getIdUser());
            }
        }
        return list;
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
        Location location = locationService.getLocationByName((String) requestBody.get("nameLocation"));
        String priority = (String) requestBody.get("priority");
        if(priority != null){
            intervention.setPriority(priority);
        }

        intervention.setIdUser(user);
        intervention.setIdLocation(location);
        intervention.setDescription((String) requestBody.get("description"));
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        intervention.setCreationDate(timestamp);
        return interventionService.createIntervention(intervention);
    }

    @PostMapping("/intervention_details")
    public Intervention detailsIntervention(@RequestBody Integer idIntervention){
        return interventionService.getIntervention(idIntervention);
    }
    @PostMapping("/login")
    public Integer login(@RequestBody User user){
        int  idUser = 0;
        Iterable<User> users = userService.listAll();
        List<User> userList = new ArrayList<User>();
        users.forEach(userList::add);
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getRegistration().equals(user.getRegistration()) && userList.get(i).getPasswordUser().equals(user.getPasswordUser())) {
                idUser = userList.get(i).getIdUser();
            }
        }
        return idUser;
    }
}
