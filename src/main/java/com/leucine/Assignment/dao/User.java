package com.leucine.Assignment.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leucine.Assignment.enums.UserRole;
import com.leucine.Assignment.service.PasswordEncoderListener;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EntityListeners(PasswordEncoderListener.class)
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @JsonIgnore
    @NotBlank(message = "Password is mandatory")
    private String password;


    @Enumerated(EnumType.STRING)
    private UserRole userRole;



}