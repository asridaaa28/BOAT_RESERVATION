package com.example.sga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sga.model.Trip;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByBoatId(Long boatId);
}