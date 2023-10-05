package com.amnil.invbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "supplier")
public class Supplier {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id", nullable = false)
    private Long id;

    /**
     * name
     */
    @NotBlank(message = "Supplier name is required")
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * contactName
     */
    @NotBlank(message = "Contact name is required")
    @Column(name = "contact_name", nullable = false)
    private String contactName;

    /**
     * contactEmail
     */
    @NotBlank(message = "Contact email is required")
    @Email(message = "Invalid email format")
    @Column(name = "contact_email", nullable = false)
    private String contactEmail;

    /**
     * contactPhone
     */
    @NotBlank(message = "Contact phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact phone must be 10 digits")
    @Column(name = "contact_phone", nullable = false)
    private String contactPhone;

    /**
     * product
     */
    @OneToMany(mappedBy = "supplier",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Product> product;
}