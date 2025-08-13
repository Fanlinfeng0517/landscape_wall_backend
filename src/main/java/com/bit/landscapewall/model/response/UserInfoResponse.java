package com.bit.landscapewall.model.response;

import com.baomidou.mybatisplus.annotation.TableField;
import com.bit.landscapewall.model.entity.User;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.util.Date;

@AutoMapper(target = User.class)
@Data
public class UserInfoResponse {
    /**
     *
     */
    @TableField(value = "username")
    private String username;

    /**
     *
     */
    @TableField(value = "email")
    private String email;

    /**
     *
     */
    @TableField(value = "avatar_url")
    private String avatar_url;

    /**
     *
     */
    @TableField(value = "role")
    private String role;

    /**
     *
     */
    @TableField(value = "created_at")
    private Date created_at;

    /**
     *
     */
    @TableField(value = "updated_at")
    private Date updated_at;
}
