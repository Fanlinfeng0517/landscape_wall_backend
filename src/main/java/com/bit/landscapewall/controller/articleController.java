package com.bit.landscapewall.controller;


import com.bit.landscapewall.common.BaseResponse;
import com.bit.landscapewall.common.ResultUtils;
import com.bit.landscapewall.model.entity.Article;
import com.bit.landscapewall.model.request.article.AddArticleRequest;
import com.bit.landscapewall.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/article")
public class articleController {
    @Resource
    private ArticleService articleService;


    /**
     * 创建文章
     * @param request
     * @param files
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<?> addArticle(@Validated AddArticleRequest request,
                                      @RequestParam("files") List<MultipartFile> files) {
        articleService.addArticle(request,files);
        return ResultUtils.success( "添加文章成功");
    }

    /**
     * 获取文章列表
     * @return
     */
//    @GetMapping("/list")
//    public BaseResponse<?> getArticleList() {
//        return ResultUtils.success(articleService.getArticleList());
//    }
}

