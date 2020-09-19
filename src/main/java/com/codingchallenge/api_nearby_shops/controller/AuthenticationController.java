package com.codingchallenge.api_nearby_shops.controller;

import com.codingchallenge.api_nearby_shops.service.AuthenticationService;
import com.codingchallenge.api_nearby_shops.service.arg.LoginArg;
import com.codingchallenge.api_nearby_shops.service.dto.JwtTokenDTO;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
    public ResponseEntity<JwtTokenDTO> authenticate(@RequestBody @Valid LoginArg loginArg){
        final JwtTokenDTO jwtTokenDTO = new JwtTokenDTO(authenticationService.authenticate(loginArg));
        return ResponseEntity.ok(jwtTokenDTO);
    }

    @PostMapping("/check-token-validity")
    public ResponseEntity<Boolean> checkTokenValidity(@RequestBody ObjectNode token){
        return ResponseEntity.ok(authenticationService.checkTokenValidity(token.get("token").asText()));
    }
}