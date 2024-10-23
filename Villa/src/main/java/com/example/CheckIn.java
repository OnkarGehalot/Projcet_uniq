package com.example;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime Checkin;
    private LocalDateTime Checkout;
    private Integer Persons;
    private String phoneNumber;
    private String location;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getCheckin() {
		return Checkin;
	}
	public void setCheckin(LocalDateTime checkin) {
		Checkin = checkin;
	}
	public LocalDateTime getCheckout() {
		return Checkout;
	}
	public void setCheckout(LocalDateTime checkout) {
		Checkout = checkout;
	}
	public Integer getPersons() {
		return Persons;
	}
	public void setPersons(Integer persons) {
		Persons = persons;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

    
}
