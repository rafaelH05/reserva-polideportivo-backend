package com.example.mi_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mi_api.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

        @Query(value = """
                        SELECT b.*, f.*
                        FROM bookings b
                        JOIN facilities f ON b.facility_id = f.facility_id
                        WHERE b.user_id = :userId
                          AND b.end_time >= NOW()
                        """, nativeQuery = true)
        List<Object[]> findBookingsWithFacilityDataByUserId(Integer userId);

        @Query(value = """
                        SELECT a.facility_id, a.start_time, a.end_time
                        FROM availabilities a
                        WHERE a.day_of_week = DAYNAME(CURDATE())
                          AND a.start_time > CURTIME() -- 👈 Esta línea filtra horas pasadas
                          AND NOT EXISTS (
                              SELECT 1
                              FROM bookings b
                              WHERE b.facility_id = a.facility_id
                                AND DATE(b.start_time) = CURDATE()
                                AND TIME(b.start_time) = a.start_time
                                AND b.cancelled_at IS NULL
                          )
                        """, nativeQuery = true)
        List<Object[]> findAvailableFacilitiesToday();

        @Query(value = """
                        SELECT
                            DATE_FORMAT(a.start_time, '%H:%i') AS start_time
                        FROM availabilities a
                        WHERE a.facility_id = :facilityId
                          AND a.day_of_week = DAYNAME(:fecha)
                          AND NOT EXISTS (
                              SELECT 1
                              FROM bookings b
                              WHERE b.facility_id = a.facility_id
                                AND DATE(b.start_time) = :fecha
                                AND TIME(b.start_time) = a.start_time
                                AND b.cancelled_at IS NULL
                          )
                          AND (
                              :fecha > CURDATE()
                              OR ( :fecha = CURDATE() AND a.start_time > CURTIME() )
                          )
                        """, nativeQuery = true)
        List<Object[]> findAvailableHoursByFacilityAndDate(Integer facilityId, String fecha);

}
