package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ContactUs;
import com.example.repository.ContactUsRepository;

@Service
public class ContactUsService {

    @Autowired
    private ContactUsRepository repository;

    public void saveContactUsForm(ContactUs contactUs) {
        repository.save(contactUs);
    }

    public List<ContactUs> getAllMessages() {
        return repository.findAll(); 
    }
}