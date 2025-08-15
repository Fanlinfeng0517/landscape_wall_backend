package com.bit.landscapewall.model.request.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateAvatarRequest implements Serializable {
    private String avatarUrl;
}
