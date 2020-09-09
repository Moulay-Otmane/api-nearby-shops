package com.codingchallenge.api_nearby_shops.controller;

import com.codingchallenge.api_nearby_shops.service.AuthenticationService;
import com.codingchallenge.api_nearby_shops.service.arg.UserArg;
import com.codingchallenge.api_nearby_shops.service.dto.JwtTokenDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/rest-service")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtTokenDTO> authenticate(@RequestBody @Valid UserArg userArg){
        final JwtTokenDTO jwtTokenDTO = new JwtTokenDTO(authenticationService.authenticate(userArg));
        return ResponseEntity.ok(jwtTokenDTO);
    }
}