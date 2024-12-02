package com.rodez.com.Repository.location;

import com.rodez.com.Entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepositoryInterface extends JpaRepository <Location, Integer> {
}
