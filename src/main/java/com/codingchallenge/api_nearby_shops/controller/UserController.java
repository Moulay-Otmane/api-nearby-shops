package com.codingchallenge.api_nearby_shops.controller;

import com.codingchallenge.api_nearby_shops.service.UserService;
import com.codingchallenge.api_nearby_shops.service.arg.UserArg;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/rest-service")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody @Valid UserArg userArg){
        this.userService.createUser(userArg);
    }
}
