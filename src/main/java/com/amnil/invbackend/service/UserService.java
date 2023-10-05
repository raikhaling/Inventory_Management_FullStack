package com.amnil.invbackend.service;

import com.amnil.invbackend.dto.core.UserDto;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Gets all users.
     *
     * @return the all users
     */
    List<UserDto> getAllUsers();

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    UserDto getUserById(Long id);

    /**
     * Update user user dto.
     *
     * @param userDto the user dto
     * @param id      the id
     * @return the user dto
     */
    UserDto updateUser(UserDto userDto, Long id);

    /**
     * Delete user by id.
     *
     * @param id the id
     */
    void deleteUserById(Long id);


}
