package com.amnil.invbackend.repository;

import com.amnil.invbackend.entity.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<LocalUser, Long> {
    @Query("select u from LocalUser u where u.email = :email")//:email is a parameter placeholder in the JPQL
    Optional<LocalUser> findByEmail(String email);

    @Query("select u from LocalUser u") //get all users
    List<LocalUser> findAllUsers();
    @Query("select u from LocalUser u where u.id = :id")
    LocalUser getUserById(Long id);

    @Modifying
    @Query("INSERT INTO LocalUser (name, email, password, address, phoneNumber, active, date) " +
            "VALUES (:#{#user.name}, :#{#user.email}, :#{#user.password}, " +
            ":#{#user.address}, :#{#user.phoneNumber}, :#{#user.active}, :#{#user.date})")
    void customSave(@Param("user") LocalUser user);

    //@Query("select u from LocalUser u where u.username = :username")
    Optional<LocalUser> findByUsername(String username);
    Boolean existsByEmail(String email);

    Optional<LocalUser> findByUsernameOrEmail(String username, String email);

    Boolean existsByUsername(String username);

    @Query("SELECT u FROM LocalUser u WHERE NOT EXISTS (SELECT r FROM u.roles r WHERE r.name = 'ROLE_ADMIN')")
    List<LocalUser> findAllUsersExceptAdmin();


}
