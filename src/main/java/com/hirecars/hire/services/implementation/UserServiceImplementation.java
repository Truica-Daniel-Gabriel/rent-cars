package com.hirecars.hire.services.implementation;

import com.hirecars.hire.models.User;
import com.hirecars.hire.models.dto.mappers.UserMapper;
import com.hirecars.hire.models.dto.request.LoginRequest;
import com.hirecars.hire.models.dto.request.RegisterRequest;
import com.hirecars.hire.models.dto.response.MessageResponse;
import com.hirecars.hire.repositories.UserRepository;
import com.hirecars.hire.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public MessageResponse userRegistration(RegisterRequest userCredentials) {
        User savedUser = userRepository.save(new UserMapper().getUserFrom(userCredentials));

        if(savedUser.getId() == null){
           throw new RuntimeException("Something goes wrong");
        }

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("User saved");

        return messageResponse;
    }

    @Override
    public MessageResponse userAuthentication(LoginRequest userCredentials) {
        return null;
    }

    @Override
    public User userUpdate() {
        return null;
    }

    @Override
    public MessageResponse updateUserRole(String userRole) {
        return null;
    }
}
