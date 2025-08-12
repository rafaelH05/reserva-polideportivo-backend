package com.example.mi_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mi_api.entities.VerificationToken;

public interface TokenRepository extends JpaRepository<VerificationToken, Integer>{
    
}
