package com.hirecars.hire.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_tokens")
public class AccountActivationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "user_id")
    Long userId;
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(name = "account_token")
    String accountToken;
}
