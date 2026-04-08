package com.microservice.user.UserService.entites;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Rating {
    @Id
    private String ratingId;
    private String hotelId;
    private String userId;
    private int rating;
    private String feedback;
    private Hotel hotel;
}