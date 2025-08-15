package com.bit.landscapewall.model.request.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateEmailRequest implements Serializable {
    private String username;
    private String email;
}
