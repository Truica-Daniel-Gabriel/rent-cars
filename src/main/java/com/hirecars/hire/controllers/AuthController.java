package com.hirecars.hire.controllers;

import com.hirecars.hire.models.dto.request.LoginRequest;
import com.hirecars.hire.models.dto.request.RegisterRequest;
import com.hirecars.hire.models.dto.response.RegisterResponse;
import com.hirecars.hire.services.EmailService;
import com.hirecars.hire.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final EmailService emailService;

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest user){

    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest userCredentials)  {

        RegisterResponse registerResponse = userService.register(userCredentials);
        Map<String, Object> variables = new HashMap<>();
        variables.put("userToken", registerResponse.getAccount_token());
        variables.put("userName", registerResponse.getFullName());


        try{
            emailService.sendEmail(registerResponse.getEmail(), "Email confirmation", "templates/activateAccountEmail.vm", variables);
        }catch (Exception e){
            return new ResponseEntity<>("Something goes wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }



        return new ResponseEntity<>(registerResponse.getMessage(), HttpStatus.OK);
    }

    @GetMapping("/activateAccount/{usertoken}")
    public ResponseEntity<String> activate(@PathVariable(name = "usertoken") String userToken) {

        userService.activateAccount(userToken);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
