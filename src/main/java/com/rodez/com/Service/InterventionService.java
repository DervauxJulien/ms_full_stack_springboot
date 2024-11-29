package com.rodez.com.Service;

import com.rodez.com.Entity.Intervention;
import com.rodez.com.Repository.InterventionRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterventionService {
    @Autowired
    InterventionRepositoryInterface interventionRepository ;

    public Iterable<Intervention> getAll(){
        return interventionRepository.findAll();

    }
}
