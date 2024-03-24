package com.hirecars.hire.services;

import com.hirecars.hire.models.User;
import com.hirecars.hire.models.dto.request.LoginRequest;
import com.hirecars.hire.models.dto.request.RegisterRequest;
import com.hirecars.hire.models.dto.response.MessageResponse;

public interface UserService {

    MessageResponse userRegistration(RegisterRequest userCredentials);

    MessageResponse userAuthentication(LoginRequest userCredentials);

    User userUpdate();

    MessageResponse updateUserRole(String userRole);

}
