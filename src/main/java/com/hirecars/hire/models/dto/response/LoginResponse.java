package com.hirecars.hire.models.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {
    public String jwtToken;
    public String email;
}
