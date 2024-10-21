package com.example.sga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sga.model.Boat;

public interface BoatRepository extends JpaRepository<Boat, Long> {
}