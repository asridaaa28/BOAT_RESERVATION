package com.example.sga.controller;

import com.example.sga.model.Reservation;
import com.example.sga.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

/*import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;*/

import java.util.List;

@Controller
@RequestMapping("/admin") // Base URL for admin functionalities
public class AdminController {

    @Autowired
    private ReservationService reservationService;

    // Get all reservations
    @GetMapping("/reservations")
    public String getAllReservations(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "admin/reservations"; // Returns the view name
    }

    // Get reservation by ID
    @GetMapping("/reservations/{id}")
    public String getReservationById(@PathVariable Long id, Model model) {
        Reservation reservation = reservationService.getReservationById(id);
        model.addAttribute("reservation", reservation);
        return "admin/reservationDetail"; // Returns the view name for reservation details
    }

    // Cancel reservation
    @GetMapping("/reservations/cancel/{id}")
    public String cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return "redirect:/admin/reservations"; // Redirects to the list of reservations
    }
}