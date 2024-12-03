package com.rodez.com.Controller.api;

import com.rodez.com.Entity.User;
import com.rodez.com.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserRestController {
    @Autowired
    private UserService userService = new UserService();

    @GetMapping("users_admin_rest")
    public Iterable <User> getAllUsers(){
        return userService.listAll();

    }
    @GetMapping("users_admin_rest/{id}")
    public Optional<User> getUserById(@PathVariable ("id") Integer id) {
        return userService.getUserById(id);

    }

    @PostMapping("/create_user_rest")

    public User createUser(@RequestBody User user){
        return userService.createUser(user);

    }
    @DeleteMapping("/delete_user_rest/{id}")
    public void delete_User(@PathVariable("id") Integer id){
        userService.deleteById(id);
    }




}
