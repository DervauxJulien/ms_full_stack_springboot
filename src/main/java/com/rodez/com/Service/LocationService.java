package com.rodez.com.Service;

import com.rodez.com.Entity.Location;
import com.rodez.com.Entity.User;
import com.rodez.com.Repository.location.LocationRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepositoryInterface locationRepositoryInterface ;
    public Iterable<Location> getLocation(){
        return  locationRepositoryInterface.findAll();
    }
    public Optional<Location> getLocationById(Integer id){
        return  locationRepositoryInterface.findById(id);

    }
}
