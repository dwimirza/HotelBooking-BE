package com.group3.model;

import java.time.LocalDateTime;  // ← Change from LocalDate

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ← Auto increment
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "name")
    private String userName;

    @Column(name = "email")
    private String userEmail;
    
    @Column(name = "password_hash")
    private String userPass;

    @Column(name = "role")
    private String userRole;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;  // ← Changed to LocalDateTime

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;  // ← Changed to LocalDateTime
}
