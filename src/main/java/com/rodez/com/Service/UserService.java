package com.rodez.com.Service;

import com.rodez.com.Entity.User;
import com.rodez.com.Repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepositoryInterface userRepository ;
    public Iterable<User> listAll(){
        return  userRepository.findAll();
    }
    public Optional<User> getUserById(Integer id){
       return  userRepository.findById(id);



    }
}
