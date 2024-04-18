package com.hirecars.hire.services.implementation;

import com.hirecars.hire.core.exceptions.AccountTokenExpired;
import com.hirecars.hire.core.exceptions.BusinessException;
import com.hirecars.hire.models.AccountStatus;
import com.hirecars.hire.models.User;
import com.hirecars.hire.models.AccountActivationToken;
import com.hirecars.hire.models.dto.mappers.UserMapper;
import com.hirecars.hire.models.dto.mappers.UserTokenMapper;
import com.hirecars.hire.models.dto.request.RegisterRequest;
import com.hirecars.hire.models.dto.response.MessageResponse;
import com.hirecars.hire.models.dto.response.RegisterResponse;
import com.hirecars.hire.repositories.UserRepository;
import com.hirecars.hire.repositories.UserTokenRepository;
import com.hirecars.hire.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;

    @Override
    public RegisterResponse register(RegisterRequest userCredentials) {
        User savedUser = userRepository.save(new UserMapper().getUserFrom(userCredentials));

        AccountActivationToken savedToken = userTokenRepository.save(new UserTokenMapper().getUserToken(savedUser.getId()));

        if(savedUser.getId() == null || savedToken.getUser_id() == null){
           throw new RuntimeException("Something goes wrong");
        }

        RegisterResponse userRegister = RegisterResponse
                .builder()
                .account_token(savedToken.getAccount_token())
                .message("Created")
                .fullName(savedUser.getFullName())
                .email(savedUser.getEmail())
                .build();

        return userRegister;
    }



    @Override
    public User update() {
        return null;
    }

    @Override
    public String activateAccount(String userToken) throws BusinessException, AccountTokenExpired {
        AccountActivationToken token =userTokenRepository.findByAccountToken(userToken).orElseThrow(() -> new BusinessException("Account token", HttpStatus.NOT_FOUND));
        User user= userRepository.findById(token.getId()).orElseThrow(()-> new BusinessException("User not found", HttpStatus.NOT_FOUND));
        Duration duration = Duration.between(token.getCreated_at(), LocalDateTime.now());

        if(duration.getSeconds() >= 86400){
            throw new AccountTokenExpired("This token is expired, try to make another one");
        }

        user.setAccountStatus(AccountStatus.ACTIVE);
        userRepository.save(user);

       return "Your account was activated";

    }

    @Override
    public MessageResponse updateUserRole(String userRole) {
        return null;
    }

}
