package com.amnil.invbackend.repository;

import static org.assertj.core.api.Assertions.assertThat;
import com.amnil.invbackend.entity.LocalUser;
import com.amnil.invbackend.entity.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest annotation indicates that this is
// a JPA repository test and sets up the required configuration for testing JPA repositories.
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository underTest;

//    @AfterEach in JUnit ensures the tearDown method runs after each test to clean the test environment,
//    typically by deleting records from a UserRepository, maintaining a clean state for each test.
    @AfterEach
    void tearDown(){
      //  underTest.deleteAll();
    }
    @Test
    void findByUsernameShouldReturnUser() {
        //given

        LocalUser user = new LocalUser(1L,"peter", "peter45", "peter@gmail.com",
                "password", "kapan", "9818563542", true, LocalDateTime.now(),
                null, null);

        underTest.save(user);

        //when
        Optional<LocalUser> expected = underTest.findByUsername(user.getUsername());

        //then
        assertThat(expected).isNotEmpty();
    }

    @Test
    void findByEmailShouldReturnUser() {
        //given
        LocalUser user = new LocalUser(1L,"peter", "peter45", "peter@gmail.com",
                "password", "kapan", "9818563542", true, LocalDateTime.now(),
                null, null);
        underTest.save(user);

        String email = user.getEmail();

        //when
        Optional<LocalUser> expected = underTest.findByEmail(email);

        //then
        assertThat(expected).isNotEmpty();

    }

    @Test
    void findAllUsersShouldReturnAllUsers() {

        //given
        LocalUser user1 = new LocalUser(1L,"peter", "peter45", "peter@gmail.com",
                "password", "kapan", "9818563542", true, LocalDateTime.now(),
                null, null);
        LocalUser user2 = new LocalUser(2L,"parker", "parker45", "parker@gmail.com",
                "password", "kapan", "9818263542", true, LocalDateTime.now(),
                null, null);

        underTest.save(user1);
        underTest.save(user2);

        //when

        List<LocalUser> results = underTest.findAllUsers();

        //then
        assertThat(results).isNotEmpty();
    }

    @Test
    void getUserByIdShouldReturnUser() {

        //given
        LocalUser user1 = new LocalUser(1L,"peter", "peter45", "peter@gmail.com",
                "password", "kapan", "9818563542", true, LocalDateTime.now(),
                null, null);
        underTest.save(user1);

        Long userId  = user1.getId();

        //when
        LocalUser expected = underTest.getUserById(userId);

        //then
        assertThat(expected)
                .as("Check if getUserById returns a non-null User")
                .isNotNull();
    }
    @Test
    @Disabled //not working yet
    void findAllUsersExceptAdmin() {

        //given

        //user with admin role
        LocalUser admin = new LocalUser(1L,"peter", "peter45", "peter@gmail.com",
                "password", "kapan", "9818563542", true, LocalDateTime.now(),
                null, null);
        Role adminRole = new Role("ROLE_ADMIN");
        admin.getRoles().add(adminRole);
        underTest.save(admin);

        //user with user role
        LocalUser user = new LocalUser(2L,"parker", "parker45", "parker@gmail.com",
                "password", "kapan", "9818263542", true, LocalDateTime.now(),
                null, null);
        Role userRole = new Role("ROLE_USER");
        user.getRoles().add(userRole);
        underTest.save(user);

        //when
        List<LocalUser> nonAdminUsers = underTest.findAllUsersExceptAdmin();

        //then
        assertThat(nonAdminUsers)
                .as("user only contains user without role Admin")
                .hasSize(1);
        assertThat(nonAdminUsers).contains(user);
    }
}