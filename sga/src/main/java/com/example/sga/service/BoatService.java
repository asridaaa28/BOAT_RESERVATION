package com.example.sga.service;

import com.example.sga.model.Boat;
import com.example.sga.repository.BoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoatService {

    @Autowired
    private BoatRepository boatRepository;

    public List<Boat> getAllBoats() {
        return boatRepository.findAll();
    }

    public Boat getBoatById(Long id) {
        return boatRepository.findById(id).orElse(null);
    }

    public BoatRepository getBoatRepository() {
        return boatRepository;
    }

    public void setBoatRepository(BoatRepository boatRepository) {
        this.boatRepository = boatRepository;
    }
}