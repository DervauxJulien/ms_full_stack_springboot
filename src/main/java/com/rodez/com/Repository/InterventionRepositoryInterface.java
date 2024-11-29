package com.rodez.com.Repository;

import com.rodez.com.Entity.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterventionRepositoryInterface extends JpaRepository<Intervention, Integer> {


}
