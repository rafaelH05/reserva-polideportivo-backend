package com.example.mi_api.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.mi_api.repository.BookingRepository;

import java.util.List;
import com.example.mi_api.entities.Booking;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController

public class BookingController {

    private BookingRepository bookingRepository;

    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/bookings")
    public List<Booking> bookings() {
        return bookingRepository.findAll();
    }

    @GetMapping("/bookings/{user_id}")
    public List<Booking> bookingsByUserId(@PathVariable Integer user_id) {
        return bookingRepository.findAll().stream()
                .filter(b -> b.getUserId().equals(user_id)).toList();
    }

    @PostMapping("/booking/create")
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingRepository.save(booking);
    }

    @PutMapping("/booking/update")
    public Booking updateBooking(@RequestBody Booking booking) {
        Booking bookingUpdate = bookingRepository.findById(booking.getId()).get();
        bookingUpdate.setUserId(booking.getUserId());
        bookingUpdate.setFacilityId(booking.getFacilityId());
        bookingUpdate.setStart_time(booking.getStart_time());
        bookingUpdate.setEnd_time(booking.getEnd_time());
        bookingUpdate.setPayment_status(booking.getPayment_status());
        bookingUpdate.setCreated_at(booking.getCreated_at());
        bookingUpdate.setCancelled_at(booking.getCancelled_at());

        return bookingRepository.save(bookingUpdate);
    }

    @DeleteMapping("/booking/delete/{id}")
    public void deleteBooking(@PathVariable Integer id) {
        bookingRepository.deleteById(id);
    }

    @GetMapping("/bookings-with-facility/{userId}")
    public List<Object[]> bookingsWithFacilityByUser(@PathVariable Integer userId) {
        {
            return bookingRepository.findBookingsWithFacilityDataByUserId(userId);
        }

    }

    @GetMapping("/booking-today")
    public List<Object[]> bookingToday() {
        return bookingRepository.findAvailableFacilitiesToday();
    }

    @GetMapping("/booking-available-hours/{facilityId}/{fecha}")
    public List<Object[]> bookingAvailableHours(@PathVariable Integer facilityId, @PathVariable String fecha) {
        return bookingRepository.findAvailableHoursByFacilityAndDate(facilityId, fecha);
    }
    
}
