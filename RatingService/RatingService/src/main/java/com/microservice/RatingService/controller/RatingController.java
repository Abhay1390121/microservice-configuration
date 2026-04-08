package com.microservice.RatingService.controller;

import com.microservice.RatingService.entities.Rating;
import com.microservice.RatingService.service.RatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private RatingServiceImpl ratingService;

    @Autowired
    public RatingController(final RatingServiceImpl ratingService){
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<Rating> provideRating( @RequestBody final Rating rating){
        final Rating createdRating = this.ratingService.provideRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRating);
    }

    @PutMapping("/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable final String ratingId, @RequestBody final Rating rating){
        final Rating updatedRating = this.ratingService.updateRating(ratingId, rating);
        return ResponseEntity.ok(updatedRating);
    }

    @DeleteMapping("/{ratingId}")
    public ResponseEntity<Rating> deleteRating(@PathVariable final String ratingId){
        return ResponseEntity.ok(this.ratingService.deleteRating(ratingId));
    }

    @GetMapping
    public ResponseEntity<List<Rating>> ratings(){
        return ResponseEntity.ok(this.ratingService.ratings());
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRatingById(@PathVariable final String ratingId){
        return ResponseEntity.ok(this.ratingService.rating(ratingId));
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable final String hotelId){
        return ResponseEntity.ok(this.ratingService.getRatingsByHotelId(hotelId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable final String userId){
        return ResponseEntity.ok(this.ratingService.getRatingsByUserId(userId));
    }

}
