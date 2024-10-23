package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AddNewRoom;
import com.example.repository.AddNewRoomRepository;

@Service
public class AddNewRoomService {

    @Autowired
    private AddNewRoomRepository repository;

    // Save the room entity to the database
    public void saveRoom(AddNewRoom room) {
        repository.save(room);
    }

    // Delete room by ID
    public void deleteRoom(Long id) {
        repository.deleteById(id); // Delete the room entity by its ID
    }

    // Fetch all rooms from the database
    public List<AddNewRoom> getAllRooms() {
        return repository.findAll();
    }
    
    // Get a room by its ID with error handling
    public AddNewRoom getRoomById(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }
}