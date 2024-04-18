package com.hirecars.hire.models.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class RegisterResponse extends MessageResponse {
  private String account_token;
  private String email;
  private String fullName;
}
