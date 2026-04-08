package com.microservice.HotelService.service;

import com.microservice.HotelService.entites.Hotel;

import java.util.List;

public interface HotelService {
    Hotel create(final Hotel hotel);
    Hotel getHotel(final String id);
    List<Hotel> getHotels();
    Hotel updateHotel(final String id, Hotel hotel);
    Hotel deleteHotel(final String id);
}
