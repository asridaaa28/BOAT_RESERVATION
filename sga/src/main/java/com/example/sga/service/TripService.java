package com.example.sga.service;

import com.example.sga.repository.TripRepository;
import com.example.sga.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public List<Trip> getTripsForBoat(Long boatId) {
        return tripRepository.findByBoatId(boatId);
    }

    public Trip getTripById(Long id) {
        return tripRepository.findById(id).orElse(null);
    }
}