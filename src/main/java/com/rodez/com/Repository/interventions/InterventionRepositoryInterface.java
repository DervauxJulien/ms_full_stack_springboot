package com.rodez.com.Repository.interventions;

import com.rodez.com.Entity.Intervention;
import com.rodez.com.Entity.User;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.metamodel.Metamodel;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface InterventionRepositoryInterface extends JpaRepository<Intervention, Integer> {
    @Query(value = "SELECT * FROM \"INTERVENTION\" WHERE \"idUser\" = ?1 ",nativeQuery = true)
    List<Intervention> getMyRequest(@Param(("id_user")) Integer id_user);
    @Query(value = "SELECT * FROM \"INTERVENTION\" WHERE ? = \"idIntervention\" ",nativeQuery = true)
    Intervention getIntervention (@Param("id_intervention") Integer id_intervention);


//    @Query(value="SELECT * FROM \"USER\" WHERE ? = registration ", nativeQuery = true)
//    User getByRegistration(String registration);



}







