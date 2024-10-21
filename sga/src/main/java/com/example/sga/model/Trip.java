package com.example.sga.model;

import jakarta.persistence.*;

@Entity
@Table(name = "trips") // Changed to match the database table name
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Maps to the 'id' column in the trip table

    @Column(name = "date", nullable = true) // Changed to match your database column
    private String tripDate; // Assuming you want to keep it as a string, matching VARCHAR in SQL

    @Column(name = "time", nullable = true) // Added time column
    private String tripTime; // Added a field for the time of the trip

    @ManyToOne
    @JoinColumn(name = "boat_id", nullable = true) // Assuming the boat_id can be null
    private Boat boat; // This will reference the Boat entity

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public String getTripTime() {
        return tripTime;
    }

    public void setTripTime(String tripTime) {
        this.tripTime = tripTime;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }
}