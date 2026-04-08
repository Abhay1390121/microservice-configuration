package com.microservice.user.UserService.controller;

import com.microservice.user.UserService.entites.User;
import com.microservice.user.UserService.services.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServicesImpl userServicesImpl;

    @Autowired
    public UserController(final UserServicesImpl userServicesImpl){
        this.userServicesImpl = userServicesImpl;
    }


    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user){
        final User createUser = userServicesImpl.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }

    //Get single user
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") String userId){
        final User user = userServicesImpl.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //Get all User
    @GetMapping()
    public ResponseEntity<List<User>> getUsers(){
        final List<User> users = userServicesImpl.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //Update a user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") String userId, @RequestBody User user){
        final User updatedUser = userServicesImpl.updateUser(userId, user);
        return ResponseEntity.ok(updatedUser);
    }

    //delete a user
    @DeleteMapping("/id")
    public ResponseEntity<User> deleteUser(@PathVariable("id") String userId){
        final User deletedUser = userServicesImpl.deleteUser(userId);
        return ResponseEntity.ok(deletedUser);
    }

}
