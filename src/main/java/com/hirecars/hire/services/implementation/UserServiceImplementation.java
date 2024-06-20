package com.hirecars.hire.services.implementation;

import com.hirecars.hire.core.exceptions.AccountTokenExpired;
import com.hirecars.hire.core.exceptions.BusinessException;
import com.hirecars.hire.models.enums.AccountStatus;
import com.hirecars.hire.models.User;
import com.hirecars.hire.models.AccountActivationToken;
import com.hirecars.hire.models.dto.mappers.UserMapper;
import com.hirecars.hire.models.dto.mappers.UserTokenMapper;
import com.hirecars.hire.models.dto.request.LoginRequest;
import com.hirecars.hire.models.dto.request.RegisterRequest;
import com.hirecars.hire.models.dto.response.LoginResponse;
import com.hirecars.hire.models.dto.response.MessageResponse;
import com.hirecars.hire.models.dto.response.RegisterResponse;
import com.hirecars.hire.repositories.UserRepository;
import com.hirecars.hire.repositories.UserTokenRepository;
import com.hirecars.hire.services.JwtService;
import com.hirecars.hire.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;
    private final JwtService jwtService;
    private final AuthenticationProvider authenticationProvider;
    private final UserMapper userMapper;

    @Override
    public RegisterResponse register(RegisterRequest userCredentials) {
        if(userRepository.findByEmail(userCredentials.getEmail()).isPresent()) {
            throw new BusinessException("This user already exists", HttpStatus.BAD_REQUEST);
        }

        User savedUser = userRepository.save(userMapper.getUserFrom(userCredentials));

        AccountActivationToken savedToken = userTokenRepository.save(new UserTokenMapper().getUserToken(savedUser.getId()));

        if(savedUser.getId() == null || savedToken.getUserId() == null){
           throw new RuntimeException("Something goes wrong");
        }

        RegisterResponse userRegister = RegisterResponse
                .builder()
                .account_token(savedToken.getAccountToken())
                .message("The user has been successfully registered")
                .fullName(savedUser.getFullName())
                .email(savedUser.getEmail())
                .build();

        return userRegister;
    }

    @Override
    public LoginResponse login(LoginRequest payload) {

        authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        payload.getEmail(),
                        payload.getPassword()
                )
        );

        String token = jwtService.generateToken(payload.getEmail());

        return LoginResponse
                .builder()
                .jwtToken(token)
                .email(payload.getEmail())
                .build();
    }


    @Override
    public User update() {
        return null;
    }

    @Override
    @Transactional
    public MessageResponse activateAccount(String userToken) {
        AccountActivationToken token = userTokenRepository.findByAccountToken(userToken).orElseThrow(() -> new BusinessException("Account token not found", HttpStatus.NOT_FOUND));
        User user= userRepository.findById(token.getId()).orElseThrow(()-> new BusinessException("User not found", HttpStatus.NOT_FOUND));
        Duration duration = Duration.between(token.getCreatedAt(), LocalDateTime.now());

        if(duration.getSeconds() >= 86400){
            throw new AccountTokenExpired("This token is expired, try to make another one");
        }

        user.setAccountStatus(AccountStatus.ACTIVE);
        userTokenRepository.deleteByUserId(user.getId());
        userRepository.save(user);

       return MessageResponse.builder().message(user.getFullName()).build();

    }

    @Override
    public MessageResponse updateUserRole(String userRole) {
        return null;
    }

}
