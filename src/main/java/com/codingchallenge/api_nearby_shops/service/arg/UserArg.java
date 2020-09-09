package com.codingchallenge.api_nearby_shops.service.arg;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserArg {

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    public UserArg() {
    }

    public UserArg(@NotNull @Email String email, @NotNull String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
