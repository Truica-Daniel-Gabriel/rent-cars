package com.hirecars.hire.models.dto.mappers;

import com.hirecars.hire.models.enums.AccountStatus;
import com.hirecars.hire.models.User;
import com.hirecars.hire.models.enums.UserRole;
import com.hirecars.hire.models.dto.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public User getUserFrom(RegisterRequest registerRequest) {
        return User
                .builder()
                .fullName(registerRequest.getFullName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .userRole(UserRole.USER)
                .accountStatus(AccountStatus.INACTIVE)
                .build();
    }

}
