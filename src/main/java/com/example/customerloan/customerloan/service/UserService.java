package com.example.customerloan.customerloan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.customerloan.customerloan.model.User;
import com.example.customerloan.customerloan.repository.UserRepository;
import com.example.customerloan.customerloan.request.UserRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String register(UserRequest userRequest){
       User user = new User();
       user.setUserName(userRequest.getUserName());
       user.setPassword(userRequest.getPassword());
       user.setEmail(userRequest.getEmail());
       userRepository.save(user);
       return "user registered successfully";
    }

    public String login(UserRequest userRequest){
        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        return "user login successfully";
    }
}
