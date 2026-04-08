package com.microservice.user.UserService.services;

import com.microservice.user.UserService.entites.User;

public interface UserService {
    User saveUser(final User user);

    User getUser(final String userId);

    User updateUser(final String userId, final User user);

    User deleteUser(final String userId);
}
