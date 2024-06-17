package com.hirecars.hire.controllers;

import com.hirecars.hire.core.exceptions.BusinessException;
import com.hirecars.hire.core.exceptions.ExceptionResponse;
import com.hirecars.hire.models.dto.request.LoginRequest;
import com.hirecars.hire.models.dto.request.RegisterRequest;
import com.hirecars.hire.models.dto.response.MessageResponse;
import com.hirecars.hire.models.dto.response.RegisterResponse;
import com.hirecars.hire.services.EmailService;
import com.hirecars.hire.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication")
@RequiredArgsConstructor
@EnableTransactionManagement
public class AuthController {

    private final UserService userService;
    private final EmailService emailService;

    @Operation(
            summary = "Login user by user credentials",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User was authenticated", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Invalid credentials", content = @Content)
            }
    )
    @PostMapping("/login")
    public ResponseEntity<MessageResponse> login(@RequestBody LoginRequest user){
       return new ResponseEntity<>(MessageResponse.builder().message("Created").build(), HttpStatus.OK);
    }



    @Operation(
            summary = "Register user by user credentials",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User was created", content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RegisterResponse.class)
                    )),
                    @ApiResponse(responseCode = "400", description = "That user already exists", content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)
                    )),
                    @ApiResponse(responseCode = "500", description = "If something is wrong with the server", content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class)
                    ))
            }
    )
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest userCredentials)  {

        RegisterResponse registerResponse = userService.register(userCredentials);
        Map<String, Object> variables = new HashMap<>();
        variables.put("userToken", registerResponse.getAccount_token());
        variables.put("userName", registerResponse.getFullName());


        try{
            emailService.sendEmail(registerResponse.getEmail(), "Email confirmation", "templates/activateAccountEmail.vm", variables);
        }catch (Exception e){
            throw new BusinessException("Something goes wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(registerResponse, HttpStatus.CREATED);
    }

    @GetMapping("/activateAccount/{usertoken}")
    public ResponseEntity<MessageResponse> activate(@PathVariable(name = "usertoken") String userToken) {
        return new ResponseEntity<>(userService.activateAccount(userToken), HttpStatus.OK);
    }
}
