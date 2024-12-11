package com.rodez.com.Service;

import com.rodez.com.Entity.Intervention;
import com.rodez.com.Entity.User;
import com.rodez.com.Repository.interventions.InterventionRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterventionService {
    @Autowired
    private InterventionRepositoryInterface interventionRepository ;
    public List<Intervention> getMyRequest(Integer id_user ){

        return interventionRepository.getMyRequest(id_user);

    }
    public Iterable<Intervention> getAll(){
        return interventionRepository.findAll();

    }

    public Intervention getIntervention(Integer id_intervention){
        return interventionRepository.getIntervention(id_intervention);
    }

    public Optional <Intervention> getById(Integer id){
        return  interventionRepository.findById(id);
    }

    public Intervention createIntervention(Intervention intervention) {
        return interventionRepository.save(intervention);
    }

    public Intervention updateIntervention(Intervention intervention){
        return interventionRepository.save(intervention);
    }
}
