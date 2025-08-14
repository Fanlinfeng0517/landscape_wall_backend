package com.bit.landscapewall.common;

import cn.dev33.satoken.exception.NotRoleException;
import com.bit.landscapewall.exception.BusinessException;
import com.bit.landscapewall.exception.ErrorCode;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }

    @ExceptionHandler(NotRoleException.class)
    public BaseResponse<?> handleNotRoleException(NotRoleException e, HttpServletRequest request) {
        log.error("权限不足: 请求路径={}, 异常信息={}", request.getRequestURI(), e.getMessage());

        // 直接使用自定义错误码和通用提示信息（无需解析具体角色）
        return ResultUtils.error(ErrorCode.NO_AUTH_ERROR, "无权限访问当前资源");
    }
}
