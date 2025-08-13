package com.bit.landscapewall.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.convert.Convert;
import com.bit.landscapewall.common.BaseResponse;
import com.bit.landscapewall.common.ResultUtils;
import com.bit.landscapewall.exception.ErrorCode;
import com.bit.landscapewall.exception.ThrowUtils;
import com.bit.landscapewall.model.entity.User;
import com.bit.landscapewall.model.request.*;
import com.bit.landscapewall.model.response.UserInfoResponse;
import com.bit.landscapewall.service.UserService;
import io.github.linpeilie.Converter;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class userController {

    @Resource
    private UserService userService;
    @Resource
    private Converter converter;

    /**
     * 用户注册
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        ThrowUtils.throwIf(userRegisterRequest == null, ErrorCode.PARAMS_ERROR);
        String username = userRegisterRequest.getUsername();
        String password = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        Long result = userService.userRegister(username, password, checkPassword);
        return ResultUtils.success(result,"注册成功");
    }

    /**
     * 用户登录
     * @param userLoginRequest
     * @param remember
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<Long> userLogin(@RequestBody UserLoginRequest userLoginRequest,
                                        @RequestParam (name = "remember", required = false, defaultValue = "false") Boolean remember
    ) {
        ThrowUtils.throwIf(userLoginRequest == null, ErrorCode.PARAMS_ERROR);
        String username = userLoginRequest.getUsername();
        String password = userLoginRequest.getPassword();
        Long result = userService.userLogin(username, password);

        StpUtil.login(result, remember);

        return ResultUtils.success(result,"登录成功");

    }

    /**
     * 获取当前用户信息
     * @return
     */
    @GetMapping("/current")
    public BaseResponse<?> getCurrentUserId() {
        // 获取当前用户Id
        Long userId = StpUtil.getLoginIdAsLong();
        // 查询该用户信息
        User user = userService.getById(userId);
        if (user == null) {
            return ResultUtils.error(ErrorCode.NOT_FOUND_ERROR, "用户不存在");
        }
        UserInfoResponse userInfo = converter.convert(user, UserInfoResponse.class);
        return ResultUtils.success(userInfo);
    }

    /**
     * 修改密码
     * @param Request
     * @return
     */
    @PostMapping("/updatePassword")
    public BaseResponse<?> updatePassword(@RequestBody @Validated UpdatePasswordRequest Request) {
        ThrowUtils.throwIf(Request == null, ErrorCode.PARAMS_ERROR);
        Long userId = StpUtil.getLoginIdAsLong();
        Boolean b = userService.updatePassword(userId, Request);
        if (!b) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "修改密码失败");
        }
        return ResultUtils.success("修改密码成功");
    }

    /**
     * 修改邮箱
     * @param request
     * @return
     */
    @PostMapping("/updateEmail")
    public BaseResponse<?> updateEmail(@RequestBody @Validated UpdateEmailRequest request) {
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        if (!request.getUsername().isEmpty()){
            userService.getById(StpUtil.getLoginIdAsLong());
        }
        Long userId = StpUtil.getLoginIdAsLong();
        Boolean b = userService.updateEmail(userId, request);
        if (!b) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "修改邮箱失败");
        }
        return ResultUtils.success("修改邮箱成功");
    }

    @PostMapping("/updateAvatar")
    public BaseResponse<?> updateAvatar(@RequestBody @Validated UpdateAvatarRequest request) {
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        Long userId = StpUtil.getLoginIdAsLong();
        Boolean b = userService.updateAvatar(userId, request);
        if (!b) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "修改头像失败");
        }
        return ResultUtils.success("修改头像成功");
    }

    /**
     * 用户登出
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<String> userLogout() {
        ThrowUtils.throwIf(!StpUtil.isLogin(), ErrorCode.NOT_LOGIN_ERROR);
        StpUtil.logout();
        return ResultUtils.success("退出登录成功");
    }
}
