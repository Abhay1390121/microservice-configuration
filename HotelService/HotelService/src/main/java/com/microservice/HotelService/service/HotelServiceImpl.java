package com.microservice.HotelService.service;

import com.microservice.HotelService.entites.Hotel;
import com.microservice.HotelService.exceptions.ResourceNotFoundException;
import com.microservice.HotelService.repositories.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    final HotelRepo hotelRepo;

    @Autowired
    public HotelServiceImpl(final HotelRepo hotelRepo){
        this.hotelRepo = hotelRepo;
    }
    @Override
    public Hotel create(final Hotel hotel) {
        hotel.setId(UUID.randomUUID().toString());
        return hotelRepo.save(hotel);
    }

    @Override
    public Hotel getHotel(final String id) {
        return hotelRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel with id " + id + " doesn't exist."));
    }

    @Override
    public List<Hotel> getHotels() {
        return hotelRepo.findAll();
    }

    @Override
    public Hotel updateHotel(String id, Hotel hotel) {
        final Hotel existingHotel = hotelRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id " + id + " doesn't exist."));
        existingHotel.setName(hotel.getName());
        existingHotel.setAbout(hotel.getAbout());
        existingHotel.setLocation(hotel.getLocation());
        return hotelRepo.save(existingHotel);
    }

    @Override
    public Hotel deleteHotel(String id) {
        final Hotel hotel = hotelRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id " + id + " doesn't exist."));
        hotelRepo.deleteById(id);
        return hotel;
    }
}
