package com.bit.landscapewall.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePasswordRequest {
    @NotNull(message = "用户ID不能为空")
    private String id;

    @NotBlank(message = "原密码不能为空")
    @Size(min = 8, message = "密码必须大于8位")
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    @Size(min = 8, message = "密码必须大于8位")
    private String newPassword;

}
