package com.hirecars.hire.repositories;

import com.hirecars.hire.models.AccountActivationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<AccountActivationToken, String> {
    Optional<AccountActivationToken> findByAccountToken(String token);
}
