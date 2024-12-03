package com.rodez.com.Service;

import com.rodez.com.Entity.Location;
import com.rodez.com.Repository.location.LocationRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    private LocationRepositoryInterface locationRepositoryInterface ;
    public Iterable<Location> getLocation(){
        return  locationRepositoryInterface.findAll();
    }
}
