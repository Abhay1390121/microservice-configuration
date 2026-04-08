package com.microservice.RatingService.service;

import com.microservice.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {
    Rating provideRating(Rating rating);
    Rating updateRating(final String ratingId, Rating rating);
    Rating rating(String ratingId);
    Rating deleteRating(String ratingId);
    List<Rating> ratings();
    List<Rating> getRatingsByUserId(final String userId);
    List<Rating> getRatingsByHotelId(final String hotelId);

}
