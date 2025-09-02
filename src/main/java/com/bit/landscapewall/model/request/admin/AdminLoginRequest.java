package com.bit.landscapewall.model.request.admin;

import lombok.Data;

@Data
public class AdminLoginRequest {
    private String username;
    private String password;
}
