    package com.amnil.invbackend.entity;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.time.LocalDateTime;
    import java.util.HashSet;
    import java.util.Set;
    /**
     * The type Local user.
     */
    @Getter
    @Setter
    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "users")
    public class LocalUser {
        /**
         * id
         */
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "id", nullable = false)
        private Long id;

        /**
         * name
         */
        @NotBlank(message = "Name is required")
        @Column(name = "name", nullable = false)
        private String name;

        /**
         * username
         */
        @NotBlank(message = "Username is required")
        @Column(name = "username", nullable = false)
        private String username;

        /**
         * email
         */
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Column(name = "email", unique = true, nullable = false)
        private String email;

        /**
         * password
         */
        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        @Column(name = "password", nullable = false, length = 1000)
        private String password;

        /**
         * address
         */
        @NotBlank(message = "Address is required")
        @Column(name = "address", nullable = false)
        private String address;

        /**
         * phoneNumber
         */
        @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
        @NotBlank(message = "Phone number is required")
        @Column(name = "phone_number", nullable = false, length = 10)
        private String phoneNumber;

        /**
         * active
         */
        private Boolean active;

        /**
         * date
         */
        private LocalDateTime date;


        /**
         * roles
         */
        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
        private Set<Role> roles;

        /**
         * order
         */
        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<Order> order = new HashSet<>();


    }

    /*
     Cascading: Cascading in JPA allows operations performed on a parent entity to automatically propagate
            to its associated child entities, such as persisting, merging, removing, or refreshing them,
            based on specified CascadeType options.

     Orphan Removal: Orphan removal, a feature of cascading in JPA, ensures that child entities removed
            from a collection of a parent entity are deleted from the database if they are no longer
            associated with any parent, helping maintain data integrity in parent-child relationships.


     */