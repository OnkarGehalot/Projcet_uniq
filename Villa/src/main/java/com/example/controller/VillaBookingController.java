package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.VillaBooking;
import com.example.repository.VillaBookingRepository;
import com.example.service.VillaBookingService;

import java.util.List;
import java.util.Optional;

@Controller
public class VillaBookingController {

    @Autowired
    private VillaBookingRepository villaBookingRepository;
    
    @Autowired
    private VillaBookingService villaService;

    // Show booking form
    @GetMapping("/book")
    public String showContactUsForm(Model model) {
        model.addAttribute("contactUs", new VillaBooking());
        return "bookn";
    }

    // Get all bookings
    @GetMapping("/bookings")
    public ResponseEntity<List<VillaBooking>> getAllBookings() {
        List<VillaBooking> bookings = villaBookingRepository.findAll();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    // Get a booking by ID
    @GetMapping("/book/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Long id) {
        Optional<VillaBooking> booking = villaBookingRepository.findById(id);
        if (booking.isPresent()) {
            return new ResponseEntity<>(booking.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Booking ID does not exist!", HttpStatus.NOT_FOUND);
        }
    }

    // Create a new booking
   /* @PostMapping("/book")
    public ResponseEntity<String> createBooking(@RequestBody VillaBooking villaBooking) {
        villaBookingRepository.save(villaBooking);
        return new ResponseEntity<>("Booking created successfully!", HttpStatus.CREATED);
    }*/

    @PostMapping("/book")
    public String createBooking(@ModelAttribute VillaBooking villaBooking) {
        villaBookingRepository.save(villaBooking);
        return "redirect:/success";
    }

    // Update an existing booking
    @PutMapping("/book/{id}")
    public ResponseEntity<String> updateBooking(@PathVariable Long id, @RequestBody VillaBooking updatedBooking) {
        Optional<VillaBooking> existingBooking = villaBookingRepository.findById(id);

        if (existingBooking.isPresent()) {
            updatedBooking.setId(id);
            villaBookingRepository.save(updatedBooking);
            return new ResponseEntity<>("Booking updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Booking ID does not exist!", HttpStatus.NOT_FOUND);
        }
    }

    // Delete a booking by ID
    @DeleteMapping("/book/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        if (villaBookingRepository.existsById(id)) {
            villaService.deletePost(id);  // Delete using service
            return new ResponseEntity<>("Booking deleted successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Booking ID does not exist!", HttpStatus.NOT_FOUND);
        }
    }
}
