package com.hirecars.hire.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "userRole")
    @Enumerated(EnumType.STRING)
    UserRole userRole;
}
