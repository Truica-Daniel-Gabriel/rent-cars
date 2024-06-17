package com.hirecars.hire.models.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Schema(name = "Login payload")
public class LoginRequest {
    @NotBlank(message = "This field shouldn't be null")
    @Schema(example = "exemple@gmail.com", type = "string")
    String email;
    @NotBlank(message = "This field shouldn't be null")
    @Schema(example = "Passw0rd$", type = "string", description = "Account password")
    String password;
}
