package com.api.user_api.service;

import com.api.user_api.exception.BadRequestException;
import com.api.user_api.exception.InvalidCredentialsException;
import com.api.user_api.exception.UserExistsException;
import com.api.user_api.exception.UserNotFoundException;
import com.api.user_api.model.User;

import java.util.List;

/**
 * This interface defines the methods that the UserService class must implement
 */
public interface UserService {

    List<User> getUsers();

    User getUser(Long id) throws BadRequestException, UserNotFoundException;

    void addUser(User user) throws BadRequestException, UserExistsException;

    void validateCredentials(User user) throws BadRequestException, InvalidCredentialsException;
}
