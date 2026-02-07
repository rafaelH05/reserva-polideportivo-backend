package com.example.mi_api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Facilities")
public class Facility {

    public enum Type {
        padel, tenis, futbol
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_id")
    private Integer id; 

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;

    @Column(name = "is_active")
    private Boolean active; 

    public Facility() {
    }

    public Facility(String name, Type type, Boolean active) {
        this.name = name;
        this.type = type;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}