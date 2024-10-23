package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.CheckIn;  
import com.example.repository.CheckInRepository;
import com.example.service.CheckInService;  

@Controller
public class CheckInController {

    @Autowired
    private CheckInRepository checkInRepository;
    
    @Autowired
    private CheckInService checkInService;

    // Show the check-in form
    @GetMapping("/checkIn")
    public String showCheckInForm(Model model) {
        model.addAttribute("checkIn", new CheckIn()); 
        return "index"; // The check-in form view
    }

    // Retrieve all check-in entries
    @GetMapping("/checkIns")
    public ResponseEntity<List<CheckIn>> getAllCheckInEntries() {
        List<CheckIn> checkIns = checkInRepository.findAll(); // Fetch all check-ins
        return ResponseEntity.ok(checkIns); // Return the list of check-ins as JSON
    }

 // Retrieve a specific check-in by ID
    @GetMapping("/checkIn/{id}")
    public ResponseEntity<?> getCheckInById(@PathVariable Long id) {
        Optional<CheckIn> checkIn = checkInRepository.findById(id);
        if (checkIn.isPresent()) {
            return ResponseEntity.ok(checkIn.get()); // Return the CheckIn object if found
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Check-in with ID " + id + " does not exist."); // Not found with message
        }
    }
    @PostMapping("/checkIn")
    public ResponseEntity<String> submitCheckInForm(@RequestBody CheckIn checkIn) {
        checkInService.saveCheckIn(checkIn); // Use service to save the check-in data
        return ResponseEntity.ok("Check-in saved successfully!");
    }

    // Handle PUT requests to update an existing check-in
    @PutMapping("/checkIn/{id}")
    public ResponseEntity<String> updateCheckIn(@PathVariable Long id, @RequestBody CheckIn checkIn) {
        if (!checkInRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Check-in with ID " + id + " does not exist.");
        }
        checkIn.setId(id); // Assuming your CheckIn class has a `setId` method
        checkInRepository.save(checkIn);
        return ResponseEntity.ok("Check-in updated successfully!");
    }

    // Handle DELETE requests to delete a check-in
    @DeleteMapping("/checkIn/{id}")
    public ResponseEntity<String> deleteCheckIn(@PathVariable Long id) {
        if (!checkInRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Check-in with ID " + id + " does not exist.");
        }
        checkInRepository.deleteById(id);
        return ResponseEntity.ok("Check-in deleted successfully!");
    }
}