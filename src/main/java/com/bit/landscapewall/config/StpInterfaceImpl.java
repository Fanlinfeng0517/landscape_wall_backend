package com.bit.landscapewall.config;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bit.landscapewall.model.entity.User;
import com.bit.landscapewall.service.UserService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@RequiredArgsConstructor
@Component    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private UserService userService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return null;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {

        // 将loginId转换为Long类型
        Long userId = Long.valueOf(loginId.toString());

        // 根据userId查询用户信息，获取角色
        String role = userService.getObj(new LambdaQueryWrapper<>(User.class)
                .eq(User::getId, userId)
                .select(User::getRole), obj -> (String) obj);

        // 返回角色集合
        return new ArrayList<>(Collections.singletonList(role));
    }

}
