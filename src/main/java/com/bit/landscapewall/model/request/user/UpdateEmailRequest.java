package com.bit.landscapewall.model.request.user;

import lombok.Data;

@Data
public class UpdateEmailRequest {
    private String username;
    private String email;
}
