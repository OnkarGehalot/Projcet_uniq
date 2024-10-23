package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CheckIn;
import com.example.repository.CheckInRepository;

@Service
public class CheckInService {

    @Autowired
    private CheckInRepository repository;

    // Save the check-in data
    public void saveCheckIn(CheckIn checkIn) {
        repository.save(checkIn);
    }
}