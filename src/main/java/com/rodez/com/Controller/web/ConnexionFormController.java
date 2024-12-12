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
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/*
    classe controle du formulaire de demande d'intervention
 */
@Controller
public class ConnexionFormController {
    @Autowired
    private InterventionService interventionService;
    @Autowired
    HttpSession session;
    @Autowired
    private LocationService locationService;
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/dashboard")
    public String login(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        boolean authorizedToConnect = false;
        // check if user authorized to connect and set authorized
        if (bindingResult.hasErrors()) {
            return "login";
        } else {
            int id = 0;
            Iterable<User> users = userService.listAll();
            List<User> userList = new ArrayList<User>();
            users.forEach(userList::add);
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getRegistration().equals(user.getRegistration()) && userList.get(i).getPasswordUser().equals(user.getPasswordUser())) {
                    authorizedToConnect = true;
                    id = userList.get(i).getIdUser();
                }
            }
            if (authorizedToConnect) {
                if (user.getRegistration().charAt(0) == 'A') {
                    model.addAttribute("interventionsList", interventionService.getAll());
                    List<User> intervenants = userService.getAllIntervenant();
                    model.addAttribute("intervenants", intervenants);
                    return "interventions_admin";
                } else if (user.getRegistration().charAt(0) == 'F') {
                    model.addAttribute("interventionsList", interventionService.getAll());
                    return "interventions_formateur";
                } else {
                    model.addAttribute("myRequest", listMyInterventions(id));
                    return "interventions_user";
                }
            }
            else {
                model.addAttribute("authorized", authorizedToConnect);
                return "login";
            }

        }
    }

    @PostMapping(value = "/submitForm", params = "action=consulte")
    public String consulteDemandes(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model){
        boolean authorizedToConnect = false;
        // check if user authorized to connect and set authorized
        if(bindingResult.hasErrors()){
            return "index";
        }
        else {
            int id = 0;
            Iterable<User> users = userService.listAll();
            List<User> userList = new ArrayList<User>();
            users.forEach(userList::add);
            for(int i=0; i<userList.size(); i++){
                if(userList.get(i).getFirstname().equals(user.getFirstname()) && userList.get(i).getRegistration().equals(user.getRegistration()) && userList.get(i).getLastname().equals(user.getLastname())){
                    authorizedToConnect = true;
                    id = userList.get(i).getIdUser();
                }
            }
            if(authorizedToConnect) {
                model.addAttribute("myRequest", listMyInterventions(id));

                return "interventions_user";
            }
            else {
                model.addAttribute("authorized", authorizedToConnect);
                return "index";
            }

        }
    }
    public Iterable<Intervention> listMyInterventions(int id){
        List<Intervention> requests = interventionService.getMyRequest (id);
        return requests;
    }
    @PostMapping( value = "/submitForm", params = "action=demande")
    public String askConnexion(@Valid @ModelAttribute ("user") User user, BindingResult bindingResult, Model model){
        boolean authorizedToConnect = false;
        // check if user authorized to connect and set authorized
        if(bindingResult.hasErrors()){
            return "index";
        }
        else{
            Iterable<User> users = userService.listAll();
            List<User> userList = new ArrayList<User>();
            users.forEach(userList::add);
            for(int i=0; i<userList.size(); i++){
                if(userList.get(i).getFirstname().equals(user.getFirstname()) && userList.get(i).getRegistration().equals(user.getRegistration()) && userList.get(i).getLastname().equals(user.getLastname())){
                    authorizedToConnect = true;
                }
            }
            if(authorizedToConnect){
                session.setAttribute("idUser", user.getRegistration());
                Intervention intervention = new Intervention();
                Iterable<Location> locations = locationService.getLocation();
                model.addAttribute("intervention", intervention);
                model.addAttribute("locationList", locations);
                return  "demande_intervention";
            }
            else {
                model.addAttribute("authorized", authorizedToConnect);
                return "index";
            }
        }

    }
}
