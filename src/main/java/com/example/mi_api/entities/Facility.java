package com.example.mi_api.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
@Table(name = "Facilities")

public class Facility {
    public Facility() {
    }

    public enum Type {
        padel, tenis, futbol
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_id")
    private Integer facility_id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @Column(name = "is_active")
    private Boolean is_active;

    public Facility(Integer facility_id, String name, Type type, Boolean is_active) {
        this.facility_id = facility_id;
        this.name = name;
        this.type = type;
        this.is_active = is_active;
    }

    public Integer getFacility_id() {
        return facility_id;
    }

    public void setFacility_id(Integer facility_id) {
        this.facility_id = facility_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

}
