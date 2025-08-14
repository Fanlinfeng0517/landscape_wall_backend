package com.bit.landscapewall.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bit.landscapewall.common.BaseResponse;
import com.bit.landscapewall.common.ResultUtils;
import com.bit.landscapewall.exception.ErrorCode;
import com.bit.landscapewall.exception.ThrowUtils;
import com.bit.landscapewall.model.request.image.ImageSearchRequest;
import com.bit.landscapewall.model.request.image.AddImageRequest;
import com.bit.landscapewall.model.request.image.AuditImage;
import com.bit.landscapewall.model.response.ImageInfoResponse;
import com.bit.landscapewall.service.ImageService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Resource
    private ImageService imageService;

    /**
     * 创建图片
     * @param request
     * @return
     */
    @PostMapping("/addImage")
    public BaseResponse<?> addImage(@RequestBody @Validated AddImageRequest request) {
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        Long result = imageService.addImage(request);
        return ResultUtils.success(result);
    }

    /**
     * 审核图片
     * @param request
     * @return
     */
    @SaCheckRole("ADMIN")
    @PostMapping("/auditImage")
    public BaseResponse<?> auditImage(@RequestBody @Validated AuditImage request) {
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        Boolean result = imageService.auditImage(request);
        return ResultUtils.success(result);
    }


    @PostMapping("/getImage")
    public BaseResponse<Page<ImageInfoResponse>> getImage(@RequestBody (required = false) ImageSearchRequest request) {
        // 获取所有图片



    }

    /*    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        ThrowUtils.throwIf(userRegisterRequest == null, ErrorCode.PARAMS_ERROR);
        String username = userRegisterRequest.getUsername();
        String password = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        Long result = userService.userRegister(username, password, checkPassword);
        return ResultUtils.success(result,"注册成功");
    }*/

}
