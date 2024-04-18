package com.hirecars.hire.models.dto.mappers;

import com.hirecars.hire.models.AccountStatus;
import com.hirecars.hire.models.User;
import com.hirecars.hire.models.UserRole;
import com.hirecars.hire.models.dto.request.RegisterRequest;

public class UserMapper {

    public User getUserFrom(RegisterRequest registerRequest) {
        return User
                .builder()
                .fullName(registerRequest.getFullName())
                .password(registerRequest.getPassword())
                .email(registerRequest.getEmail())
                .userRole(UserRole.USER)
                .accountStatus(AccountStatus.INACTIVE)
                .build();
    }

}
