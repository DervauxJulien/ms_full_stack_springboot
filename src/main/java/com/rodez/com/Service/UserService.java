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
        return userRepository.save(user);
    }

    public User getByRegistration(String registration){
        return  userRepository.getByRegistration(registration);
    }


}

