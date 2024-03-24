package com.hirecars.hire.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginRequest {
    @NotBlank(message = "This field shouldn't be null")
    String email;
    @NotBlank(message = "This field shouldn't be null")
    String password;
}
