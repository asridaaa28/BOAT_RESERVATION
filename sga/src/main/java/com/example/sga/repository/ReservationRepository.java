package com.example.sga.repository;

import com.example.sga.model.Reservation; // Use the correct entity class
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByTripId(Long tripId);
}