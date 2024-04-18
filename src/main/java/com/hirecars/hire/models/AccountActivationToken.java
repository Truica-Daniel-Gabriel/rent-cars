package com.hirecars.hire.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@Table(name = "user_tokens")
public class AccountActivationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "user_id")
    Long user_id;
    @Column(name = "created_at")
    LocalDateTime created_at;

    @Column(name = "account_token")
    String account_token;
}
