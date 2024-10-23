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

import com.example.CheckIn;
import com.example.ContactUs;
import com.example.repository.ContactUsRepository;
import com.example.service.ContactUsService;

@Controller
public class ContactUsController {
    
    @Autowired
    private ContactUsRepository contactUsRepository;


    @Autowired
    private ContactUsService contactUsService;

    // Show the contact us form
    @GetMapping("/contact")
    public String showContactUsForm(Model model) {
        model.addAttribute("contactUs", new ContactUs()); 
        return "contact"; // The contact form view
    }

    // Retrieve all contact entries
    @GetMapping("/contacts")
    public ResponseEntity<List<ContactUs>> getAllContactEntries() {
        List<ContactUs> contacts = contactUsRepository.findAll(); // Fetch all contacts
        return ResponseEntity.ok(contacts); // Return the list of contacts as JSON
    }

 //// Retrieve a specific contact by ID
    @GetMapping("/contact/{id}")
    public ResponseEntity<?> getContactById(@PathVariable Long id) {
        Optional<ContactUs> contact = contactUsRepository.findById(id);
        if (contact.isPresent()) {
            return ResponseEntity.ok(contact.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Contact with ID " + id + " does not exist.");
        }
    }

    @GetMapping("/report")
    public String showReports(Model model) {
        List<ContactUs> messages = contactUsService.getAllMessages();
        model.addAttribute("contactUsList", messages);
        return "reports"; // Ensure the template name is correct (reports.html)
    }


    
    // Handle POST requests to create a new contact
    @PostMapping("/contact")
    public ResponseEntity<String> submitContactUsForm(@RequestBody ContactUs contactUs) {
        contactUsRepository.save(contactUs);
        return ResponseEntity.status(HttpStatus.CREATED).body("Contact saved successfully!");
    }

 // Handle PUT requests to update an existing contact
    @PutMapping("/contactput/{id}")
    public ResponseEntity<String> updateContactUs(@PathVariable Long id, @RequestBody ContactUs contactUs) {
        if (!contactUsRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact with ID " + id + " does not exist.");
        }
        contactUs.setId(id); // Assuming your ContactUs class has a `setId` method
        contactUsRepository.save(contactUs);
        return ResponseEntity.ok("Contact updated successfully!");
    }


 // Handle DELETE requests to delete a contact
    @DeleteMapping("/contactdel/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        if (!contactUsRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Contact with ID " + id + " does not exist.");
        }
        contactUsRepository.deleteById(id);
        return ResponseEntity.ok("Contact deleted successfully!");
    }

}