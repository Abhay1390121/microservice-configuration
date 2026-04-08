package com.microservice.HotelService.controller;

import com.microservice.HotelService.entites.Hotel;
import com.microservice.HotelService.service.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelServiceImpl hotelService;

    @Autowired
    HotelController(final HotelServiceImpl hotelService){
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel createHotel = hotelService.create(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createHotel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable String id, @RequestBody Hotel hotel){
        Hotel updatedHotel = hotelService.updateHotel(id, hotel);
        return ResponseEntity.ok(updatedHotel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String id){
        return ResponseEntity.ok(hotelService.getHotel(id));
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels(){
        return ResponseEntity.ok(hotelService.getHotels());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Hotel> deleteHotel(@PathVariable String id){
        return ResponseEntity.ok(hotelService.deleteHotel(id));
    }

}
