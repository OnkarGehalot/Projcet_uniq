package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.VillaBooking;

public interface VillaBookingRepository extends JpaRepository<VillaBooking, Long> {
}
