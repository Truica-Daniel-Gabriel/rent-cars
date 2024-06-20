package com.hirecars.hire.models.dto.mappers;

import com.hirecars.hire.models.AccountActivationToken;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

public class UserTokenMapper {


    public AccountActivationToken getUserToken(Long id) {
        return AccountActivationToken
                .builder()
                .accountToken(generateRandomToken())
                .createdAt(LocalDateTime.now())
                .userId(id)
                .build();
    }
    private String generateRandomToken() {

        String uuid = UUID.randomUUID().toString();

        return Base64.getEncoder().encodeToString((uuid + LocalDateTime.now()).getBytes());
    }
}
