package com.codingchallenge.api_nearby_shops.service;

import com.codingchallenge.api_nearby_shops.Exception.AuthenticationInformationsNotFound;
import com.codingchallenge.api_nearby_shops.Exception.UserAlreadyExistException;
import com.codingchallenge.api_nearby_shops.Exception.UserNotFoundException;
import com.codingchallenge.api_nearby_shops.model.User;
import com.codingchallenge.api_nearby_shops.repository.UserRepository;
import com.codingchallenge.api_nearby_shops.service.arg.UserArg;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    /*
    *  This function is used to create new user.
    * */
    public void createUser(UserArg userArg){
        if (userRepository.findByEmail(userArg.getEmail()).isPresent()){
            throw new UserAlreadyExistException("EMAIL_ALREADY_USED");
        }else{
            userRepository.save(
                    new User(userArg.getEmail(),
                            passwordEncoder.encode(userArg.getPassword()),
                            userArg.getCity(),
                            userArg.getLocation()
                    ));
        }
    }

    /*
    * This function returns the current user whose name is in the security context.
    * */
    public User getAuthenticatedUser(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String username = null;

        if(authentication != null){
            if (authentication.getPrincipal() instanceof UserDetails){
                username = ((UserDetails) authentication.getPrincipal()).getUsername();
            }else if(authentication.getPrincipal() instanceof String){
                username = (String) authentication.getPrincipal();
            }else {
                throw new AuthenticationInformationsNotFound("AUTHENTICATION_INFORMATIONS_NOT_FOUND");
            }
            return userRepository.findByEmail(username).orElseThrow(() -> new UserNotFoundException("USER_NOT_FOUND"));
        }
        throw new AuthenticationInformationsNotFound("AUTHENTICATION_INFORMATIONS_NOT_FOUND");

    }
}
