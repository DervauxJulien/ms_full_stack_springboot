package com.rodez.com.Service;

import com.rodez.com.Entity.User;
import com.rodez.com.Repository.users.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepositoryInterface userRepository ;
    public Iterable<User> listAll(){
        return  userRepository.findAll(Sort.by(Sort.Direction.ASC, "idUser"));
    }
    public Optional<User> getUserById(Integer id){
       return  userRepository.findById(id);

    }
    public List<User> getAllIntervenant(){
        return userRepository.getAllIntervenant();

    }
    public User createUser(User user ){
        return userRepository.save(user);
    }
    public void deleteById(Integer id){
        userRepository.deleteById(id);
    }
    public User updateUser(User user){
//        //Optional<User> user = userRepository.findById(id);
//        User user = userRepository.findById(user_maj.getId_user()).get();
//        user.setRole_user(user_maj.getRole_user());
//        user.setPassword_user(user_maj.getPassword_user());
//        user.setRegistration(user_maj.getRegistration());
//        user.setFirstname(user_maj.getFirstname());
//        user.setLastname(user_maj.getLastname());
        System.out.println("--------------------------------------------------------------------------------------"+user.getIdUser());
        System.out.println("--------------------------------------------------------------------------------------"+user.getFirstname());
        System.out.println("--------------------------------------------------------------------------------------"+user.getLastname());
        System.out.println("--------------------------------------------------------------------------------------"+user.getPasswordUser());
        System.out.println("--------------------------------------------------------------------------------------"+user.getRoleUser());
        System.out.println("--------------------------------------------------------------------------------------"+user.getRegistration());
        return userRepository.save(user);
    }


}

