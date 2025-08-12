package com.example.mi_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mi_api.entities.Photos;

@Repository
public interface PhotoRepository extends JpaRepository<Photos, Integer> {

}
