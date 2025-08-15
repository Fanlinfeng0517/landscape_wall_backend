package com.bit.landscapewall.model.request.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

public class UserQueryRequest implements Serializable {

    @TableField(value = "username")
    private String username;


    @TableField(value = "email")
    private String email;

}
