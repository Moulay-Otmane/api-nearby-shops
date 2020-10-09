package com.codingchallenge.api_nearby_shops.service;

import com.codingchallenge.api_nearby_shops.Exception.UserNotFoundException;
import com.codingchallenge.api_nearby_shops.model.User;
import com.codingchallenge.api_nearby_shops.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /*
    * This function return userDetails of the user whose email passed as parameter.
    * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username).orElseThrow(()-> new UserNotFoundException("USER_NOT_FOUND") );
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
