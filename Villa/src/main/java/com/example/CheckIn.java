/*package com.example;
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
*/


package com.example;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class CheckIn {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime checkin; // Changed field name to checkin (camelCase)

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime checkout; // Changed field name to checkout (camelCase)

    private Integer persons; // Changed field name to persons (camelCase)
    
    private String phoneNumber;
    private String location;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getCheckin() {
		return checkin;
	}
	public void setCheckin(LocalDateTime checkin) {
		this.checkin = checkin;
	}
	public LocalDateTime getCheckout() {
		return checkout;
	}
	public void setCheckout(LocalDateTime checkout) {
		this.checkout = checkout;
	}
	public Integer getPersons() {
		return persons;
	}
	public void setPersons(Integer persons) {
		this.persons = persons;
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
