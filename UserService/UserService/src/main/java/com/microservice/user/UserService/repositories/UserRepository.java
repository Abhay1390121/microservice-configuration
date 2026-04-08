package com.microservice.user.UserService.repositories;

import com.microservice.user.UserService.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    // we can also implement custom method or query.
}
