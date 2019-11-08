package com.gmail.michzuerch.teachersassistant.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gmail.michzuerch.teachersassistant.backend.data.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
