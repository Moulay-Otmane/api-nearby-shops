package com.codingchallenge.api_nearby_shops.service;

import com.codingchallenge.api_nearby_shops.Exception.UserNotFoundException;
import com.codingchallenge.api_nearby_shops.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserDetailsServiceImpl userDetailsService;

    @Test
    public void should_load_user_by_username(){
        String email = Fixture.loginArg().getEmail();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(Fixture.user()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        verify(userRepository).findByEmail(email);
        assertThat(userDetails.getUsername()).isEqualTo(Fixture.user().getEmail());
        assertThat(userDetails.getPassword()).isEqualTo(Fixture.user().getPassword());
        assertThat(userDetails.getAuthorities()).isEmpty();
    }

    @Test(expected = UserNotFoundException.class)
    public void should_throw_user_not_found_exception_when_trying_to_load_user_using_invalid_username(){
        String email = Fixture.loginArg().getEmail();
        when(userRepository.findByEmail(email)).thenReturn(Optional.ofNullable(null));
        userDetailsService.loadUserByUsername(email);
    }
}
