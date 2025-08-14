package com.bit.landscapewall.model.request.user;


import lombok.Data;

@Data
public class UserRegisterRequest {

    private String username;

    private String password;

    private String checkPassword;
}

