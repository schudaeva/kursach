package com.example.film_distribution.service;

import com.example.film_distribution.dto.UserDto;
import com.example.film_distribution.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}