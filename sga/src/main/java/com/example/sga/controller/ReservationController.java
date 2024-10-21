package com.example.sga.controller;

import com.example.sga.model.Reservation;
import com.example.sga.service.ReservationService; // Make sure to create this service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
/*import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;*/

import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService; // Ensure this service exists

    // Method to show the reservation form
    @GetMapping("/create")
    public String showReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        // Add other required attributes, such as a list of trips
        return "reservation_form"; // Return the name of the HTML template for reservation form
    }

    // Method to create a reservation
    @PostMapping("/create")
    public String createReservation(@ModelAttribute Reservation reservation) {
        reservationService.saveReservation(reservation);
        return "redirect:/reservations"; // Redirect to a list of reservations or a confirmation page
    }

    // Method to list all reservations
    @GetMapping
    public String listReservations(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "reservation_list"; // Return the name of the HTML template for the reservation list
    }

    // Method to cancel a reservation (assuming you want to delete it)
    @PostMapping("/cancel/{id}")
    public String cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return "redirect:/reservations"; // Redirect to the list of reservations
    }

    // Method to view details of a reservation (optional)
    @GetMapping("/{id}")
    public String viewReservation(@PathVariable Long id, Model model) {
        Reservation reservation = reservationService.getReservationById(id);
        model.addAttribute("reservation", reservation);
        return "reservation_detail"; // Return the name of the HTML template for reservation details
    }
}