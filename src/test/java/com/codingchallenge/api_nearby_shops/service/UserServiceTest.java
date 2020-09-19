package com.codingchallenge.api_nearby_shops.service;


import com.codingchallenge.api_nearby_shops.Exception.UserAlreadyExistException;
import com.codingchallenge.api_nearby_shops.model.User;
import com.codingchallenge.api_nearby_shops.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserService userService;

    @Test
    public void should_create_user(){
        ArgumentCaptor<User> UserCaptor = ArgumentCaptor.forClass(User.class);
        when(userRepository.findByEmail(Fixture.userArg().getEmail())).thenReturn(Optional.ofNullable(null));
        when(passwordEncoder.encode("123456789")).thenReturn(Fixture.user().getPassword());
        when(userRepository.save(any(User.class))).thenReturn(Fixture.user());

        userService.createUser(Fixture.userArg());

        verify(userRepository).findByEmail(Fixture.userArg().getEmail());
        verify(userRepository).save(UserCaptor.capture());
        User savedUser = UserCaptor.getValue();
        assertThat(savedUser.getEmail()).isEqualTo(Fixture.userArg().getEmail());
        assertThat(savedUser.getPassword()).isEqualTo("$2a$10$vUUe4wKvpvKSKbjxQt47jeMnx/W9xO2jn89vblUCLEhztv6kd8q7u");
        assertThat(savedUser.getCity()).isEqualTo(Fixture.userArg().getCity());
        assertThat(savedUser.getLocation().getLatitude()).isEqualTo(Fixture.userArg().getLocation().getLatitude());
        assertThat(savedUser.getLocation().getLongitude()).isEqualTo(Fixture.userArg().getLocation().getLongitude());
    }

    @Test(expected = UserAlreadyExistException.class)
    public void should_throw_an_exception_when_creating_a_user_with_used_email(){
        when(userRepository.findByEmail(Fixture.userArg().getEmail())).thenReturn(Optional.of(Fixture.user()));

        userService.createUser(Fixture.userArg());

        verify(userRepository).findByEmail(Fixture.userArg().getEmail());
    }
}
