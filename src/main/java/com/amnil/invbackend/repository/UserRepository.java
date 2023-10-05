package com.amnil.invbackend.repository;

import com.amnil.invbackend.entity.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * The interface User repository.
 */
public interface UserRepository extends JpaRepository<LocalUser, Long> {
    /**
     * Find by email optional.
     *
     * @param email the email
     * @return the optional
     */
    @Query("select u from LocalUser u where u.email = :email")//:email is a parameter placeholder in the JPQL
    Optional<LocalUser> findByEmail(String email);

    /**
     * Find all users list.
     *
     * @return the list
     */
    @Query("select u from LocalUser u") //get all users
    List<LocalUser> findAllUsers();

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    @Query("select u from LocalUser u where u.id = :id")
    LocalUser getUserById(Long id);

    /**
     * Custom save.
     *
     * @param user the user
     */
    @Modifying
    @Query("INSERT INTO LocalUser (name, email, password, address, phoneNumber, active, date) " +
            "VALUES (:#{#user.name}, :#{#user.email}, :#{#user.password}, " +
            ":#{#user.address}, :#{#user.phoneNumber}, :#{#user.active}, :#{#user.date})")
    void customSave(@Param("user") LocalUser user);

    /**
     * Find by username optional.
     *
     * @param username the username
     * @return the optional
     */
//@Query("select u from LocalUser u where u.username = :username")
    Optional<LocalUser> findByUsername(String username);

    /**
     * Exists by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    Boolean existsByEmail(String email);

    /**
     * Find by username or email optional.
     *
     * @param username the username
     * @param email    the email
     * @return the optional
     */
    Optional<LocalUser> findByUsernameOrEmail(String username, String email);

    /**
     * Exists by username boolean.
     *
     * @param username the username
     * @return the boolean
     */
    Boolean existsByUsername(String username);

    /**
     * Find all users except admin list.
     *
     * @return the list
     */
    @Query("SELECT u FROM LocalUser u WHERE NOT EXISTS (SELECT r FROM u.roles r WHERE r.name = 'ROLE_ADMIN')")
    List<LocalUser> findAllUsersExceptAdmin();


}
