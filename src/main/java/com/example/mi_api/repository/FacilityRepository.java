package com.example.mi_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mi_api.entities.Facility;

public interface FacilityRepository extends JpaRepository<Facility, Integer> {
}
