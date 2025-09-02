package com.bit.landscapewall.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.util.SaResult;
import com.bit.landscapewall.common.BaseResponse;
import com.bit.landscapewall.common.ResultUtils;
import com.bit.landscapewall.exception.ErrorCode;
import com.bit.landscapewall.model.request.article.AuditArticleRequest;
import com.bit.landscapewall.service.ArticleService;
import com.bit.landscapewall.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class adminController {

    @Resource
    private UserService userService;
    @Resource
    private ArticleService articleService;

//    @PostMapping("/login")
//    public BaseResponse<?> login(@RequestBody @Validated AdminLoginRequest request){
//        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
//        String username = request.getUsername();
//        String password = request.getPassword();
//        Long result = userService.adminLogin(username, password);
//        return ResultUtils.success(result,"登录成功");
//
//    }

    /**
     * 审核文章
     * @param request
     * @return
     */
    @SaCheckRole("admin")
    @PostMapping("/audit{articleId}")
    public BaseResponse<?> audit(@RequestBody @Validated AuditArticleRequest request){
        // 审核文章
        String result = articleService.audit(request);
        // 根据审核状态返回结果
        if (result.equals("审核通过")){
            return ResultUtils.success(result);
        }else {
            return ResultUtils.error(ErrorCode.OPERATION_ERROR, request.getAuditComment());
        }
    }


}
