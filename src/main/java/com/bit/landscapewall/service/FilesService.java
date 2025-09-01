// 文件路径示例：com.bit.landscapewall.service.FilesService
package com.bit.landscapewall.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service // 必须添加此注解
public class FilesService {
    public boolean deleteFiles(List<String> urls) {
        // 实现文件删除逻辑
        return true;
    }
}