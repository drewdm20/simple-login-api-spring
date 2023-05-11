package com.api.user_api.service;

import com.api.user_api.exception.BadRequestException;
import com.api.user_api.exception.InvalidCredentialsException;
import com.api.user_api.exception.UserExistsException;
import com.api.user_api.exception.UserNotFoundException;
import com.api.user_api.model.User;
import com.api.user_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * This class implements the UserService interface
 */
@Service
public class UserServiceImpl implements UserService{

    // Autowire the UserRepository interface
    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Implement the methods defined in the UserService interface
    // Method to get all users
    public List<User> getUsers(){
        List<User> users = userRepository.findAll();

        return users.isEmpty() ? null:users;
    }

    // Method to get a user by id
    public User getUser(Long id) throws BadRequestException, UserNotFoundException {
        if (id <= 0) throw new BadRequestException("Please enter a valid user id");
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));
    }

    // Method to add a user
    public void addUser(User user) throws BadRequestException, UserExistsException {
        if (user == null) throw new BadRequestException("Please provide a valid request body");
        List<User> users = getUsers();
        for (User eachUser:users
             ) {
            if (Objects.equals(eachUser.getUsername(), user.getUsername())) throw new UserExistsException("User already exists");
        }
        userRepository.save(user);
    }

    // Method to validate user credentials
    public void validateCredentials(User user) throws BadRequestException, InvalidCredentialsException {
        if (user == null) throw new BadRequestException("Please provide a valid request body");
        List<User> users = getUsers();
        for (User eachUser: users
             ) {
            if (!(Objects.equals(eachUser.getUsername(), user.getUsername())) || !(Objects.equals(eachUser.getPassword(), user.getPassword()))) throw new InvalidCredentialsException("Invalid username and/or password");
        }
    }
}
