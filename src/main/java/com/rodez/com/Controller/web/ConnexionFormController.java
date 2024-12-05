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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    HttpSession session;
    @Autowired
    private LocationService locationService;
    @Autowired
    private UserService userService;

    @PostMapping( "/submitForm")
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
                session.setAttribute("idUser", user.getIdUser());
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
