package com.amnil.invbackend.repository;

import com.amnil.invbackend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Role repository.
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     */
//Optional<Role> findByName(String name);
    Optional<Role> findByName(String name);

    /**
     * Exists by name boolean.
     *
     * @param name the name
     * @return the boolean
     */
    boolean existsByName(String name);

}
