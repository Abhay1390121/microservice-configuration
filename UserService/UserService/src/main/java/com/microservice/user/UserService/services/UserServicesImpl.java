package com.microservice.user.UserService.services;

import com.microservice.user.UserService.entites.Hotel;
import com.microservice.user.UserService.entites.Rating;
import com.microservice.user.UserService.entites.User;
import com.microservice.user.UserService.exceptions.ResourceNotFoundException;
import com.microservice.user.UserService.externalservices.HotelService;
import com.microservice.user.UserService.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserServicesImpl implements UserService {

    private final UserRepository userRepository;
    private final RestTemplate template;
    private final HotelService hotelService;

    @Autowired
    public UserServicesImpl(
            final UserRepository userRepository,
            final RestTemplate template,
            final HotelService hotelService)
    {
        this.userRepository = userRepository;
        this.template = template;
        this.hotelService = hotelService;
    }

    @Override
    public User getUser(final String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with userId " + userId + " doesn't exist."));
        final Rating[] ratingsArray = template.getForObject("http://RATING-SERVICE/ratings/user/" + user.getUserId(), Rating[].class);
        List<Rating> ratings = Arrays.asList(ratingsArray);
        ratings.forEach((rating) -> {
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
        });
        user.setRatings(ratings);
        return user;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User saveUser(final User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(final String userId, final User user) {
        final User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with userId " + userId + " doesn't exist."));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setAbout(user.getAbout());
        return userRepository.save(existingUser);
    }

    @Override
    public User deleteUser(final String userId) {
        final User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with userId " + userId + " doesn't exist."));
        userRepository.deleteById(userId);
        return existingUser;
    }
}
