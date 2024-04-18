package com.hirecars.hire.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "fullName")
    String fullName;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    @Setter(AccessLevel.NONE)
    String password;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    UserRole userRole;

    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    AccountStatus accountStatus;

}
