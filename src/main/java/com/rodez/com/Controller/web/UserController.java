package com.rodez.com.Controller.web;

import com.rodez.com.Entity.User;
import com.rodez.com.Service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class UserController {


    @Autowired
    UserService userService = new UserService();
    /*
        function list all users
     */
    @GetMapping("/users")
    public String listUsers( Model model){
        Iterable<User> users = userService.listAll();

        /*
        users = StreamSupport.stream(users.spliterator(), false)
                .sorted(Comparator.comparing(User::getId_user))
                .collect(Collectors.toList());

         */
        model.addAttribute("usersList",users);
        return "users";
    }

    /*
        function to fin user by id
     */
    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable( "id") Integer id, Model model){
        User user =   userService.getUserById(id).orElse(null);
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/create_user")

    public String createUser(@RequestBody User user ,Model model ){
        User userCreated =userService.createUser(user);
        model.addAttribute("user_created", userCreated);

        return "confirmation_user_created";

    }
    @GetMapping (value="/action_user/{id}" ,params = "action=delete")
    public String deleteUser (@PathVariable ("id") Integer id ,Model model ){
        User user =null;
        Optional <User> user_deleted = userService.getUserById(id);
        if(user_deleted.isPresent()) {
            user = user_deleted.get();
        }
        model.addAttribute("user_deleted", user);
        userService.deleteById(id);
        return  "delete_confirmation";

    }
    @GetMapping(value="/action_user/{id}" ,params = "action=update")
    public String loadUser(@PathVariable ("id") Integer id , Model model ){
        User user = null;
        Optional <User> user_loaded =userService.getUserById(id);
        user = user_loaded.get();
        model.addAttribute("user_to_edit", user);
        return "update_user";
    }
    @GetMapping(value = "/update_user/{id}")
    public String updateUser(@ModelAttribute("user_to_edit") User user){
        userService.updateUser(user);

        return "update_confirmation";
    }




}
