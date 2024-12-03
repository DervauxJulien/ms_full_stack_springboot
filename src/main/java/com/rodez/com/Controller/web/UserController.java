package com.rodez.com.Controller.web;

import com.rodez.com.Entity.User;
import com.rodez.com.Service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @DeleteMapping ("/delete_user/id={id}" )
    public String deleteUser (@RequestParam ("id") Integer id ,Model model ){
        Optional <User> user_deleted = userService.getUserById(id);
        System.out.println("user_deleted");
        model.addAttribute("user_deleted", user_deleted);
        userService.deleteById(id);
        return  "delete_confirmation";

    }



}
