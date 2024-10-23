package com.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rooms")
public class AddNewRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private double originalPrice;
    private double discount;
    private double effectivePrice;
    private String location;
    @Column(length = 1024)
    private String roomImage; // URL of the uploaded image in Firebase

    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public String getRoomImage() {
		return roomImage;
	}

	public void setRoomImage(String roomImage) {
		this.roomImage = roomImage;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getEffectivePrice() {
        return effectivePrice;
    }

    public void setEffectivePrice(double effectivePrice) {
        this.effectivePrice = effectivePrice;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

   
}
