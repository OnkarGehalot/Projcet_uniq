
package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CheckIn;
import com.example.ContactUs;
import com.example.VillaBooking;
import com.example.repository.CheckInRepository;
import com.example.repository.ContactUsRepository;
import com.example.repository.VillaBookingRepository;

@Service
public class VillaBookingService {

    @Autowired
    private VillaBookingRepository repository;

    public void saveContactUsForm(VillaBooking contactUs) {
        repository.save(contactUs);
    }

	public void saveCheckIn(CheckIn check) {
		// TODO Auto-generated method stub
		
	}

	public void saveVillaBooking(VillaBooking villaBooking) {
		// TODO Auto-generated method stub
		repository.save(villaBooking);
	}

	public List<VillaBooking> getAllBookings() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getBookingById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteBooking(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	public VillaBooking createBooking(VillaBooking booking) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deletePost(Long id) {
		// TODO Auto-generated method stub
		
	}
}
