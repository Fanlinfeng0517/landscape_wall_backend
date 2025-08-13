package com.bit.landscapewall.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bit.landscapewall.exception.BusinessException;
import com.bit.landscapewall.exception.ErrorCode;
import com.bit.landscapewall.model.entity.User;
import com.bit.landscapewall.model.request.UpdateAvatarRequest;
import com.bit.landscapewall.model.request.UpdateEmailRequest;
import com.bit.landscapewall.model.request.UpdatePasswordRequest;
import com.bit.landscapewall.service.UserService;
import com.bit.landscapewall.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
* @author 范林峰
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-07-20 12:53:46
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public Long userRegister(String username, String password, String checkPassword) {
        // 校验参数
        if (StrUtil.hasBlank(username, password, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (username.length() <4){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名长度不能小于4位");
        }
        if (password.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度不能小于8位");
        }
        if (!password.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次密码不一致");
        }
        // 检查数据库中是否有重复字段
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        Long count = this.baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
        }
        // 密码加密
        String encryptPassword = getEncryptPassword(password);

        // 插入数据库
        User user = new User();
        user.setUsername(username);
        user.setPassword(encryptPassword);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "注册失败");
        }
        return user.getId();
    }

    @Override
    public Long userLogin(String username, String password) {
        // 判断是否已经登录
        if (StpUtil.isLogin()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户已经登录");
        }
        // 校验参数
        if (StrUtil.hasBlank(username, password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (username.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名长度不能小于4位");
        }
        if (password.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度不能小于8位");
        }
        // 对用户输入密码进行加密
        String encryptPassword = getEncryptPassword(password);
        // 查询数据库
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", encryptPassword);
        User user = this.baseMapper.selectOne(queryWrapper);
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名或密码错误");
        }

        return user.getId();

    }

    @Override
    public Boolean updatePassword(Long userId, UpdatePasswordRequest request) {
        // 查询用户
        User user = this.getById(userId);
        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "用户不存在");
        }
        // 校验密码
        if (getEncryptPassword(request.getNewPassword()).equals(user.getPassword())){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "新密码不能与旧密码一致");
        }
        if (request.getNewPassword().length() < 8 || request.getOldPassword().length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度不能小于8位");
        }
        if (!request.getNewPassword().equals(request.getOldPassword())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次密码不一致");
        }
        // 对密码进行加密
        String encryptPassword = getEncryptPassword(request.getOldPassword());
        // 更新密码
        User updatePassword = new User();
        updatePassword.setId(userId);
        updatePassword.setPassword(encryptPassword);
        return this.updateById(updatePassword);
    }

    @Override
    public Boolean updateEmail(Long userId, UpdateEmailRequest request) {
        User user = this.getById(userId);
        System.out.println(user);
        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "用户不存在");
        }
        if (request.getEmail().equals("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "邮箱格式不正确");
        }
        if(request.getEmail().equals(user.getEmail())){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "邮箱不能与原邮箱相同");
        }
        // 更新邮箱
        User updateEmail = new User();
        updateEmail.setId(userId);
        updateEmail.setEmail(request.getEmail());
        return this.updateById(updateEmail);
    }

    @Override
    public Boolean updateAvatar(Long userId, UpdateAvatarRequest request) {
        return null;
    }


    /**
     * 加密密码
     * @param userPassword
     * @return
     */
    @Override
    public String getEncryptPassword(String userPassword) {
        // 加盐，混淆密码
        final String SALT = "16bit";
        return DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
    }


}




