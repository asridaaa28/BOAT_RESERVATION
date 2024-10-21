package com.example.sga.controller;

import com.example.sga.model.Trip;
import com.example.sga.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;*/

import java.util.List;

@Controller
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping("/boats/{boatId}/trips")
    public String getTripsForBoat(@PathVariable Long boatId, Model model) {
        List<Trip> trips = tripService.getTripsForBoat(boatId);
        model.addAttribute("trips", trips);
        return "trips";
    }
}