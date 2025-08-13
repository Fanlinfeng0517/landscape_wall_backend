package com.bit.landscapewall.service;

import com.bit.landscapewall.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bit.landscapewall.model.request.UpdateAvatarRequest;
import com.bit.landscapewall.model.request.UpdateEmailRequest;
import com.bit.landscapewall.model.request.UpdatePasswordRequest;

/**
* @author 范林峰
* @description 针对表【user】的数据库操作Service
* @createDate 2025-07-20 12:53:46
*/
public interface UserService extends IService<User> {

    Long userRegister(String username, String password, String checkPassword);

    String getEncryptPassword(String userPassword);

    Long userLogin(String username, String password);

    Boolean updatePassword(Long userID,UpdatePasswordRequest request);

    Boolean updateEmail(Long userId, UpdateEmailRequest request);

    Boolean updateAvatar(Long userId, UpdateAvatarRequest request);
}
