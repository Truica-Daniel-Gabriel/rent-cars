package com.hirecars.hire.services;

import com.hirecars.hire.core.exceptions.AccountTokenExpired;
import com.hirecars.hire.core.exceptions.BusinessException;
import com.hirecars.hire.models.User;
import com.hirecars.hire.models.dto.request.LoginRequest;
import com.hirecars.hire.models.dto.request.RegisterRequest;
import com.hirecars.hire.models.dto.response.MessageResponse;
import com.hirecars.hire.models.dto.response.RegisterResponse;

public interface UserService {

    RegisterResponse register(RegisterRequest userCredentials);

    User update();

    String activateAccount(String userToken) throws BusinessException, AccountTokenExpired;

    MessageResponse updateUserRole(String userRole);

}
