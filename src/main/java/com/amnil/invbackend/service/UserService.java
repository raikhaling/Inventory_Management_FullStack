package com.amnil.invbackend.service;

import com.amnil.invbackend.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto updateUser(UserDto userDto, Long id);
    void deleteUserById(Long id);


}
