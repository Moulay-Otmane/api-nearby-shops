package com.codingchallenge.api_nearby_shops.service;

import com.codingchallenge.api_nearby_shops.Exception.UserAlreadyExistException;
import com.codingchallenge.api_nearby_shops.model.User;
import com.codingchallenge.api_nearby_shops.repository.UserRepository;
import com.codingchallenge.api_nearby_shops.service.arg.UserArg;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(UserArg userArg){
        if (userRepository.findByEmail(userArg.getEmail()).isPresent()){
            throw new UserAlreadyExistException("EMAIL_ALREADY_USED");
        }else{
            userRepository.save(new User(userArg.getEmail(), passwordEncoder.encode(userArg.getPassword())));
        }
    }
}
