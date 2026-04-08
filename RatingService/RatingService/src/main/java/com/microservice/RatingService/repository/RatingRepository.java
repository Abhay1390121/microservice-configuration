package com.microservice.RatingService.repository;

import com.microservice.RatingService.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {
    List<Rating> findByUserId(final String userId);
    List<Rating> findByHotelId(final String hotelId);
}
