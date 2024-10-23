package com.example.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.AddNewRoom;
import com.example.repository.AddNewRoomRepository;
import com.example.service.AddNewRoomService;
import com.example.service.FirebaseStorageService;

@Controller
public class AddNewRoomController {

    @Autowired
    private AddNewRoomService roomService;

    @Autowired
    private AddNewRoomRepository roomRepository;

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    // Display all rooms on the /ourroom page
    @GetMapping("/ourroom")
    public String showRooms(Model model) {
        List<AddNewRoom> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        return "room"; // This returns the room.html page
    }

    // Show form to add a new room
    @GetMapping("/addroom")
    public String showAddRoomForm(Model model) {
        return "dashboard"; // Ensure this points to your add-room form HTML file
    }

    // Get all rooms (API endpoint)
    @GetMapping("/allrooms")
    public ResponseEntity<List<AddNewRoom>> getAllRooms() {
        List<AddNewRoom> rooms = roomRepository.findAll();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    // Get room by ID (API endpoint)
    @GetMapping("/addroom/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable Long id) {
        Optional<AddNewRoom> room = roomRepository.findById(id);
        if (room.isPresent()) {
            return new ResponseEntity<>(room.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Room ID does not exist!", HttpStatus.NOT_FOUND);
        }
    }

  /*  
    // Add a new room
    @PostMapping("/addroom")
    public String addRoom(@RequestParam String title,
                          @RequestParam double originalPrice,
                          @RequestParam double discount,
                          @RequestParam double effectivePrice,
                          @RequestParam String location,
                          @RequestParam MultipartFile roomImage, // File upload for room image
                          Model model) {
    	
        AddNewRoom room = new AddNewRoom();
        room.setTitle(title);
        room.setOriginalPrice(originalPrice);
        room.setDiscount(discount);
        room.setEffectivePrice(effectivePrice);
        room.setLocation(location);
        */
    @PostMapping("/addroom")
    public String addRoom(@RequestParam String title,
                          @RequestParam double originalPrice,
                          @RequestParam double discount,
                          @RequestParam double effectivePrice,
                          @RequestParam String location,
                          @RequestParam MultipartFile roomImage, // File upload for room image
                          Model model) {

        AddNewRoom room = new AddNewRoom();
        room.setTitle(title);
        room.setOriginalPrice(originalPrice);
        room.setDiscount(discount);
        room.setEffectivePrice(effectivePrice);
        room.setLocation(location);

        // Handle image upload
        if (!roomImage.isEmpty()) {
            try {
                String fileName = firebaseStorageService.uploadFile(roomImage, "post new room images");
                room.setRoomImage(fileName); // Assuming your AddNewRoom class has a roomImage field

            } catch (IOException e) {
                model.addAttribute("errorMessage", "Error uploading image: " + e.getMessage());
                return "dashboard"; // Return to the form with an error message
            }
        }

        roomService.saveRoom(room); // Save the room entity
        System.out.println("Room added successfully!");
        return "redirect:/success"; // Redirect to success page
    }
    /*
        // Handle image upload
        if (!roomImage.isEmpty()) {
            try {
                String fileName = firebaseStorageService.uploadFile(roomImage, "post new room images");
                room.setRoomImage(fileName);
            } catch (IOException e) {
                model.addAttribute("errorMessage", "Error uploading image: " + e.getMessage());
                return "dashboard"; // Return to the form with an error message
            }
        }

        roomService.saveRoom(room); // Save the room entity
        System.out.println("Room added successfully!");
        return "redirect:/success"; // Redirect to a success page
    }*/
        // Update an existing room
    @PutMapping("/addroom/{id}")
    @ResponseBody
    public ResponseEntity<String> updateRoom(@PathVariable Long id, @RequestBody AddNewRoom updatedRoom) {
        return roomRepository.findById(id)
            .map(existingRoom -> {
                existingRoom.setTitle(updatedRoom.getTitle());
                existingRoom.setOriginalPrice(updatedRoom.getOriginalPrice());
                existingRoom.setDiscount(updatedRoom.getDiscount());
                existingRoom.setEffectivePrice(updatedRoom.getEffectivePrice());
                existingRoom.setLocation(updatedRoom.getLocation());
                existingRoom.setRoomImage(updatedRoom.getRoomImage());

                roomRepository.save(existingRoom);
                return ResponseEntity.ok("Room updated successfully!");
            })
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found with ID: " + id)); // Return 404 if room not found
    }

    // Delete a room by ID
    @DeleteMapping("/rooms/{id}")
    @ResponseBody
    public String deleteRoom(@PathVariable Long id) {
        if (roomRepository.existsById(id)) {
            roomService.deleteRoom(id);
            return "Room deleted successfully!"; // Return a success message
        } else {
            return "Room not found with ID: " + id; // Return a message if room not found
        }
    }
}