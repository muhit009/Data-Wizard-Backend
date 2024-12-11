package org.example.userapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "created_at", nullable = false, updatable = false)
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;

    @Column(name = "suspended_at")
    private java.time.LocalDateTime suspendedAt;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('active', 'suspended') default 'active'")
    private Status status;

    @Column
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('Job Seeker', 'Employer')")
    private Role role;

    public enum Status {
        ACTIVE,
        SUSPENDED
    }

    public enum Role {
        JOB_SEEKER,
        EMPLOYER
    }
}