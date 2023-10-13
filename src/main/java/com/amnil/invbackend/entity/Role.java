package com.amnil.invbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * The type Role.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * name
     */
    @NotBlank(message = "Role name is required")
    @Column(unique = true)
    private String name;

    /**
     * users
     */
    @ManyToMany(mappedBy = "roles")
    private Set<LocalUser> users = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }
}
