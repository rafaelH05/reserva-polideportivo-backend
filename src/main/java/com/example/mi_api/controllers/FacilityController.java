package com.example.mi_api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mi_api.entities.Facility;
import com.example.mi_api.repository.FacilityRepository;

@RestController
public class FacilityController {
    public FacilityRepository facilityRepository;

    public FacilityController(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    @GetMapping("/facilities")
    public List<Facility> facilities() {
        return facilityRepository.findAll();
    }

    @GetMapping("/facilities/{id}")
    public Facility facilityById(@PathVariable Integer id) {
        return facilityRepository.findById(id).orElse(null);
    }

    @PostMapping("/facilities/create")
    public Facility createFacility(@RequestBody Facility facility) {
        return facilityRepository.save(facility);
    }

    @PutMapping("/facilities/update")
    public Facility updateFacility(@RequestBody Facility facility) {
        Facility facilityUpdate = facilityRepository.findById(facility.getFacility_id()).orElse(null);
        if (facilityUpdate != null) {
            facilityUpdate.setName(facility.getName());
            facilityUpdate.setType(facility.getType());
            facilityUpdate.setActive(facility.isActive());
            return facilityRepository.save(facilityUpdate);
        }
        return null;
    }
    @DeleteMapping("/facilities/delete/{id}")
    public void deleteFacility(@PathVariable Integer id) {
        facilityRepository.deleteById(id);
    }
}
