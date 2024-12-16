package com.rodez.com.Controller.api;

import com.rodez.com.Entity.User;
import com.rodez.com.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public User createUser(@RequestBody Map<String, Object> requestBody){
        User user = null;
        String registration = (String) requestBody.get("registration");
        String lastname = (String) requestBody.get("lastname");
        String firstname = (String) requestBody.get("firstname");
        String passwordUser = (String) requestBody.get("passwordUser");
        Iterable<User> users = userService.listAll();
        List<User> userList = new ArrayList<User>();
        users.forEach(userList::add);
        boolean already = false;
        if((registration.charAt(0)== 'A' || registration.charAt(0)== 'F' || registration.charAt(0)== 'U' || registration.charAt(0)== 'P')) {
            for (int i = 0; i < userList.size(); i++) {
                if(registration.equals(userList.get(i).getRegistration())){
                    throw new Error("Utilisateur déjà existant (matricule)");
                }
            }
            user = new User();
            user.setRegistration(registration);
            user.setPasswordUser(passwordUser);
            user.setLastname(lastname);
            user.setFirstname(firstname);
            if(registration.charAt(0)=='A') user.setRoleUser("admin");
            else if(registration.charAt(0)=='F') user.setRoleUser("formateur");
            else if(registration.charAt(0)=='P') user.setRoleUser("personnel");
            else if(registration.charAt(0)=='U') user.setRoleUser("user");
            user = userService.createUser(user);
        }

        return user;
    }
    @DeleteMapping("/delete_user_rest")
    public void delete_User(@RequestBody Map<String, Object> requestBody){
        userService.deleteById((Integer) requestBody.get("idUser"));
    }

    @PutMapping("/update_user_rest")
    public User updateUser(@RequestBody Map<String, Object> requestBody){
        User user = userService.getUserById((Integer) requestBody.get("idUser")).get();
        if(requestBody.get("registration")!=null){
            user.setRegistration((String) requestBody.get("registration"));
        }
        if(requestBody.get("roleUser")!=null){
            user.setRoleUser((String) requestBody.get("roleUser"));
        }
        if(requestBody.get("lastname")!=null){
            user.setLastname((String) requestBody.get("lastname"));
        }
        if(requestBody.get("firstname")!=null){
            user.setFirstname((String) requestBody.get("firstname"));
        }
        if(requestBody.get("passwordUser")!=null){
            user.setPasswordUser((String) requestBody.get("passwordUser"));
        }
        return userService.updateUser(user);
    }




}
