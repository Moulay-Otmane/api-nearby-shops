package com.codingchallenge.api_nearby_shops.service;

import com.codingchallenge.api_nearby_shops.Exception.UserNotFoundException;
import com.codingchallenge.api_nearby_shops.security.configuration.JwtTokenUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    JwtTokenUtil jwtTokenUtil;

    @Mock
    UserDetailsServiceImpl userDetailsService;

    @InjectMocks
    AuthenticationService authenticationService;

    @Test
    public void should_authenticate(){

        Authentication authentication = new UsernamePasswordAuthenticationToken(Fixture.loginArg().getEmail(), Fixture.loginArg().getPassword());
        UserDetails userDetails = new User(Fixture.user().getEmail(), Fixture.user().getPassword(), new ArrayList<>());
        when(userDetailsService.loadUserByUsername(Fixture.loginArg().getEmail())).thenReturn(userDetails);
        when(authenticationManager.authenticate(authentication)).thenReturn(authentication);
        when(jwtTokenUtil.generateToken(userDetails)).thenReturn(Fixture.token());

        String token = authenticationService.authenticate(Fixture.loginArg());

        verify(userDetailsService).loadUserByUsername(Fixture.loginArg().getEmail());
        verify(authenticationManager).authenticate(authentication);
        verify(jwtTokenUtil).generateToken(userDetails);
        assertThat(token).isEqualTo(Fixture.token());
    }

    @Test(expected = UserNotFoundException.class)
    public void should_throw_user_not_found_exception_when_a_not_registered_user_try_to_authenticate(){
        Authentication authentication = new UsernamePasswordAuthenticationToken(Fixture.loginArg().getEmail(), Fixture.loginArg().getPassword());
        when(userDetailsService.loadUserByUsername(Fixture.loginArg().getEmail())).thenThrow(new UserNotFoundException("USER_NOT_FOUND"));
        when(authenticationManager.authenticate(authentication)).thenReturn(authentication);
        authenticationService.authenticate(Fixture.loginArg());
    }

}
