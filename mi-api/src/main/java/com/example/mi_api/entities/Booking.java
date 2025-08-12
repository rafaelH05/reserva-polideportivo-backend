package com.example.mi_api.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    public enum PaymentStatus {
        pending, paid, cancelled
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "facility_id")
    private Integer facilityId;

    @Column(name = "start_time")
    private LocalDateTime start_time;

    @Column(name = "end_time")
    private LocalDateTime end_time;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus payment_status;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Column(name = "cancelled_at")
    private LocalDateTime cancelled_at;

    public Booking() {
    }

    public Booking(Integer id, Integer userId, Integer facilityId, LocalDateTime start_time, LocalDateTime end_time,
            PaymentStatus payment_status, LocalDateTime created_at, LocalDateTime cancelled_at) {
        this.id = id;
        this.userId = userId;
        this.facilityId = facilityId;
        this.start_time = start_time;
        this.end_time = end_time;
        this.payment_status = payment_status;
        this.created_at = created_at;
        this.cancelled_at = cancelled_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

    public PaymentStatus getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(PaymentStatus payment_status) {
        this.payment_status = payment_status;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getCancelled_at() {
        return cancelled_at;
    }

    public void setCancelled_at(LocalDateTime cancelled_at) {
        this.cancelled_at = cancelled_at;
    }
}
