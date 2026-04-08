package com.microservice.RatingService.service;

import com.microservice.RatingService.entities.Rating;
import com.microservice.RatingService.exception.ResourceNotFoundException;
import com.microservice.RatingService.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private RatingRepository ratingRepo;

    @Autowired
     public RatingServiceImpl(final RatingRepository ratingRepository){
         this.ratingRepo = ratingRepository;
     }
    @Override
    public Rating provideRating(Rating rating) {
        return ratingRepo.save(rating);
    }

    @Override
    public Rating updateRating(String ratingId, Rating rating) {
        Rating existingRating = ratingRepo.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating with id "+ ratingId + " doesn't exist"));
        existingRating.setRating(rating.getRating());
        existingRating.setFeedback(rating.getFeedback());
        return ratingRepo.save(existingRating);
    }

    @Override
    public List<Rating> ratings() {
        return this.ratingRepo.findAll();
    }

    @Override
    public List<Rating> getRatingsByUserId(final String userId) {
        return this.ratingRepo.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingsByHotelId(final String hotelId) {
        return this.ratingRepo.findByHotelId(hotelId);
    }

    @Override
    public Rating rating(String ratingId) {
        return ratingRepo.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating with id "+ ratingId + " doesn't exist"));
    }

    @Override
    public Rating deleteRating(String ratingId) {
        final Rating existingRating = ratingRepo.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating with id "+ ratingId + " doesn't exist"));
        this.ratingRepo.deleteById(ratingId);
        return existingRating;
    }


}
