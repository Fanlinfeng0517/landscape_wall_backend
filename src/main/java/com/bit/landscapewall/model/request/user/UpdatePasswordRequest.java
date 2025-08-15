package com.bit.landscapewall.model.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class UpdatePasswordRequest implements Serializable {
    @NotNull(message = "用户ID不能为空")
    private String id;

    @NotBlank(message = "原密码不能为空")
    @Size(min = 8, message = "密码必须大于8位")
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    @Size(min = 8, message = "密码必须大于8位")
    private String newPassword;

}
