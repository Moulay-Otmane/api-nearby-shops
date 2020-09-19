package com.codingchallenge.api_nearby_shops.service;


import com.codingchallenge.api_nearby_shops.security.configuration.JwtTokenUtil;
import com.codingchallenge.api_nearby_shops.service.arg.LoginArg;
import com.codingchallenge.api_nearby_shops.service.arg.UserArg;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserDetailsServiceImpl userDetailsService;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    public String authenticate(LoginArg loginArg){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginArg.getEmail(), loginArg.getPassword()));
        }catch(DisabledException e){
            throw new IllegalArgumentException("USER_DISABLED",e);
        }catch (BadCredentialsException e){
            throw new IllegalArgumentException("INVALID_CREDENTIALS",e);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginArg.getEmail());

        return jwtTokenUtil.generateToken(userDetails);
    }

    public boolean checkTokenValidity(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtTokenUtil.extractUsernameFromToken(token));
        return jwtTokenUtil.isTokenValid(token, userDetails);
    }
}
