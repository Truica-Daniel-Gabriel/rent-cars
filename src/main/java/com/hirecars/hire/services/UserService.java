package com.hirecars.hire.services;

import com.hirecars.hire.core.exceptions.AccountTokenExpired;
import com.hirecars.hire.core.exceptions.BusinessException;
import com.hirecars.hire.models.User;
import com.hirecars.hire.models.dto.request.LoginRequest;
import com.hirecars.hire.models.dto.request.RegisterRequest;
import com.hirecars.hire.models.dto.response.LoginResponse;
import com.hirecars.hire.models.dto.response.MessageResponse;
import com.hirecars.hire.models.dto.response.RegisterResponse;

public interface UserService {

    RegisterResponse register(RegisterRequest userCredentials);

    LoginResponse login(LoginRequest payload);

    User update();

    MessageResponse activateAccount(String userToken) throws BusinessException, AccountTokenExpired;

    MessageResponse updateUserRole(String userRole);

}
