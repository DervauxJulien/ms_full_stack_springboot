package com.rodez.com.Controller.api;

import com.rodez.com.Entity.Location;
import com.rodez.com.Entity.User;
import com.rodez.com.Service.InterventionService;
import com.rodez.com.Service.LocationService;
import com.rodez.com.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationRestController {

    @Autowired
    private LocationService locationService = new LocationService();

    @GetMapping("locations")
    public Iterable <Location> getAllLocations(){
        return locationService.getLocation();

    }
}
