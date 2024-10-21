package com.example.sga.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sga.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}