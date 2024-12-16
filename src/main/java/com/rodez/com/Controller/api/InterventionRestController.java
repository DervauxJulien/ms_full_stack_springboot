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
    /*
    Update la priority d'une intervention
    Check si on reçoit "low", "medium", "high" et return l'intervention updated, sinon return null
     */
    @PutMapping("/intervention_update_priority")
    public Intervention updatePriority(@RequestBody Map<String, Object> requestBody){
        String priority = (String) requestBody.get("priority");
        if(priority.equals("low") || priority.equals("medium") || priority.equals("high")){
            Intervention intervention = interventionService.getIntervention((Integer) requestBody.get("idIntervention"));
            intervention.setPriority(priority);
            return interventionService.updateIntervention(intervention);
        }
        return null;
    }
    /*
    Update le status d'une intervention
    Check si on reçoit "en attente", "traité", "en cours" ou "réalisé", update le status de l'intervention et renvoie le status (String), sinon renvoie "Mauvais status"
    Gère également la création/suppression des dates correspondantes en fonction du status
     */
    @PutMapping("/intervention_update_status")
    public String updateStatus(@RequestBody Map<String, Object> requestBody){
        Intervention intervention = interventionService.getIntervention((Integer) requestBody.get("idIntervention"));
        String status = (String) requestBody.get("status");
        System.out.println(status);
        if(status.equals("en cours") || status.equals("réalisé") || status.equals("en attente") || status.equals("traité")){
            intervention.setStatus(status);
            Timestamp realisationDate = intervention.getRealisationDate();
            Timestamp validationDate = intervention.getDate();
            Timestamp affectationDate = intervention.getAffectationDate();
            if (status.equals("en attente")){
                validationDate = null;
                affectationDate = null;
                realisationDate = null;
            }
            if (status.equals("en cours")){
                affectationDate = Timestamp.valueOf(LocalDateTime.now());
                realisationDate = null;
                if(validationDate==null)validationDate=affectationDate;
            }
            if (status.equals("traité")){
                validationDate = Timestamp.valueOf(LocalDateTime.now());
                affectationDate = null;
                realisationDate = null;
            }
            if(status.equals("réalisé")) {
                intervention.setIdIntervenant(null);
                realisationDate = Timestamp.valueOf(LocalDateTime.now());
                if(affectationDate==null)affectationDate=realisationDate;
                if(validationDate==null)validationDate=realisationDate;
            }
            intervention.setDate(validationDate);
            intervention.setRealisationDate(realisationDate);
            intervention.setAffectationDate(affectationDate);
            interventionService.updateIntervention(intervention);
        }
        else{
            status = "Mauvais status";
        }

        return status;
    }

    /*
    Update l'intervenant d'une intervention
    Verifie s'il est dans la base de données, puis s'il est admin ou formateur et modifie l'intervenant
     */
    @PostMapping("/intervention_update_intervenant")
    public String updateIntervenant(@RequestBody Map<String, Object> requestBody){
        Intervention intervention = interventionService.getIntervention((Integer) requestBody.get("idIntervention"));
        Optional<User> opt;
        User user = null;

        Timestamp timestamp = null;
        String s = "Intervenant null";

        if(requestBody.get("idIntervenant") != null) {
            opt = userService.getUserById((Integer) requestBody.get("idIntervenant"));
            if(opt.isPresent()){
                user = opt.get();
            }
            if(user != null){
                if(user.getRoleUser().equals("admin") || user.getRoleUser().equals("formateur")){
                    intervention.setIdIntervenant(user);
                    timestamp = Timestamp.valueOf(LocalDateTime.now());
                    s= "Intervenant modifié";
                }
                else {
                    s = "Cet utilisateur n'est pas un intervenant";
                }
            }
            else{
                s = "Intervenant pas dans la base";
            }
        }

        intervention.setAffectationDate(timestamp);
        interventionService.updateIntervention(intervention);
        return s;
    }

    /*
    Return la list des interventions d'un utilisateur, ou toutes les interventions si le role de l'utilisateur est admin ou formateur
     */
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
        if(location == null){
            throw new Error("Localisation mauvaise");
        }
        String priority = (String) requestBody.get("priority");
        if(priority != null){
            if(priority.equals("high") || priority.equals("medium") || priority.equals("low")){
                intervention.setPriority(priority);
            }
            else{
                throw new Error("Priorité pas au bon format (high, medium ou low)");
            }
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
