package com.example.mi_api.config;

import com.example.mi_api.entities.Availability;
import com.example.mi_api.entities.Facility;
import com.example.mi_api.repository.AvailabilityRepository;
import com.example.mi_api.repository.FacilityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(FacilityRepository facilityRepository, 
                                   AvailabilityRepository availabilityRepository) {
        return args -> {
           
            List<Facility> facilities = new ArrayList<>();
            facilities.add(crearFacility("Pista Pádel 1", "padel", true));
            facilities.add(crearFacility("Pista Pádel 2", "padel", true));
            facilities.add(crearFacility("Pista Tenis 1", "tenis", true));
            facilities.add(crearFacility("Pista Tenis 2", "tenis", true));
            facilities.add(crearFacility("Campo de Fútbol", "futbol", true));

            List<Facility> savedFacilities = facilityRepository.saveAll(facilities);

            List<String> days = Arrays.asList("monday", "tuesday", "wednesday", "thursday", "friday", "saturday");
            List<Availability> availabilities = new ArrayList<>();
            for (Facility facility : savedFacilities) {

                for (String day : days) {

                    LocalTime startTime = LocalTime.of(8, 0);       // 08:00:00
                    LocalTime endTimeLimit = LocalTime.of(21, 30);  // Última hora permitida

                    while (!startTime.isAfter(endTimeLimit)) {
                        LocalTime endTime = startTime.plusMinutes(90);

                        Availability availability = new Availability();
                        availability.setFacility(facility);da
                        availability.setDayOfWeek(day);
                        availability.setStartTime(startTime);
                        availability.setEndTime(endTime);
                        
                        availabilities.add(availability);

                        startTime = startTime.plusMinutes(90);
                    }
                }
            }

            availabilityRepository.saveAll(availabilities);

        };
    }

    private Facility crearFacility(String name, String type, boolean isActive) {
        Facility f = new Facility();
        f.setName(name);
        f.setType(type);
        f.setIsActive(isActive);
        return f;
    }
}