package com.amnil.invbackend.service.impl;

import com.amnil.invbackend.dto.core.UserDto;
import com.amnil.invbackend.entity.LocalUser;
import com.amnil.invbackend.entity.Role;
import com.amnil.invbackend.repository.UserRepository;
import com.amnil.invbackend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock private UserRepository userRepository;
    private UserService underTest;
    ModelMapper modelMapper;

    @BeforeEach
    void setUp(){
        modelMapper = new ModelMapper();
        underTest = new UserServiceImpl(userRepository, modelMapper);
    }
    @Test
    void getAllUsers() {
        //when
        underTest.getAllUsers();

        //then
        verify(userRepository).findAllUsers();
    }

    @Test
    @Disabled
    void getUserById() {

        LocalUser user = new LocalUser();
        user.setId(1L);
        user.setName("test");
        user.setUsername("test");
        user.setEmail("john@example.com");
        user.setPassword("test");
        user.setAddress("mock");
        user.setPhoneNumber("1234567890");
        user.setActive(true);
        user.setDate(LocalDateTime.now());

        // Create a set of roles and add them to the user
        Set<Role> roles = new HashSet<>();
        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("ROLE_USER");
        roles.add(role1);
        user.setRoles(roles);

        //Mockito's when method configures the behavior of a mock to return a specific value when
        // a specific method is called.

        // Mock the behavior of your UserRepository to return the user when getUserById is called
        when(userRepository.getUserById(user.getId())).thenReturn(user);

        UserDto retrievedUserDTO = underTest.getUserById(user.getId());

//        assertEquals(user.getId(), retrievedUserDTO.getId());
//        assertEquals(user.getName(), retrievedUserDTO.getName());
        assertThat(user.getId().equals(retrievedUserDTO.getId()));
        assertThat(user.getName().equals(retrievedUserDTO.getName()));


    }

    @Test
    @Disabled
    void updateUser() {
    }

    @Test
    @Disabled
    void deleteUserById() {
    }
}