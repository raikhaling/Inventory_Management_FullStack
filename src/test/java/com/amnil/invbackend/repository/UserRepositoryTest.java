package com.amnil.invbackend.repository;

import static org.assertj.core.api.Assertions.assertThat;
import com.amnil.invbackend.entity.LocalUser;
import com.amnil.invbackend.entity.Order;
import com.amnil.invbackend.entity.Role;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository underTest;
    @Test
    void findByUsername() {
        //given

//        Role role = new Role(1L, "ROLE_USER", null);
//        Order order = new Order();

        LocalUser user = new LocalUser(1L,"peter", "peter45", "peter@gmail.com",
                "password", "kapan", "9818563542", true, LocalDateTime.now(),
                null, null);

        underTest.save(user);

        //when
        Optional<LocalUser> expected = underTest.findByUsername(user.getUsername());

        //then
        assertThat(expected).isNotEmpty();
    }
}