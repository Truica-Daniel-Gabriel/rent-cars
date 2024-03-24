package com.hirecars.hire.controllers;

import com.hirecars.hire.models.dto.request.LoginRequest;
import com.hirecars.hire.models.dto.request.RegisterRequest;
import com.hirecars.hire.models.dto.response.MessageResponse;
import com.hirecars.hire.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest user){

    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody @Valid RegisterRequest userCredentials){
        return new ResponseEntity<>(userService.userRegistration(userCredentials), HttpStatus.OK);
    }
}
