package com.hirecars.hire.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterRequest {
    @NotBlank(message = "Full name required")
    String fullName;
    @NotBlank(message = "Password required")
    @Size(min = 8, message = "Password too short, minimum 8 characters required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&*])[^\\s]+$", message = "Password should have at least one capital letter, number and symbol")
    String password;
    @NotBlank(message = "Email required")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email")
    String email;
}
