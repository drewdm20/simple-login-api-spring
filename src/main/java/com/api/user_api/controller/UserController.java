package com.api.user_api.controller;

import com.api.user_api.exception.BadRequestException;
import com.api.user_api.exception.InvalidCredentialsException;
import com.api.user_api.exception.UserExistsException;
import com.api.user_api.exception.UserNotFoundException;
import com.api.user_api.model.User;
import com.api.user_api.service.UserService;
import com.api.user_api.util.ResponseHandler;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is the controller for the user API
 */
@RestController
@RequestMapping(value = "/userApi")
public class UserController {

    // Autowire the UserService class
    @Autowired
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // Define the endpoints
    // GET endpoint to get all users from the database
    @GetMapping(value = "/getUsers")
    public ResponseEntity<Object> getUsers() {
        List<User> users = userService.getUsers();
        if (users.isEmpty()){
            return ResponseHandler.generateResponse("There are currently no users", HttpStatus.OK, users);
        } else return ResponseHandler.generateResponse("Successfully retrieved users!", HttpStatus.OK, users);
    }

    // GET endpoint to get a user by id
    @GetMapping(value = "/getUser/{id}")
    public ResponseEntity<Object> getUser(@PathVariable Long id) throws BadRequestException,UserNotFoundException {
        User user = userService.getUser(id);
        return ResponseHandler.generateResponse("Successfully retrieved user", HttpStatus.OK, user);
    }

    // POST endpoint to add a user to the database
    @PostMapping(value = "/registerUser")
    public ResponseEntity<Object> addUser(@Validated @NonNull @RequestBody User user) throws BadRequestException, UserExistsException {
        userService.addUser(user);
        return ResponseHandler.generateResponse("Successfully registered user", HttpStatus.CREATED, null);
    }

    // POST endpoint to validate the credentials of a user
    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@Validated @NonNull @RequestBody User user) throws BadRequestException, InvalidCredentialsException {
        userService.validateCredentials(user);
        return ResponseHandler.generateResponse("Welcome!", HttpStatus.OK, user.getUsername());
    }
 }
