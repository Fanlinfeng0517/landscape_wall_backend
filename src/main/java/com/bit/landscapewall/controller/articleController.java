package com.bit.landscapewall.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.bit.landscapewall.common.BaseResponse;
import com.bit.landscapewall.common.ResultUtils;
import com.bit.landscapewall.exception.ErrorCode;
import com.bit.landscapewall.exception.ThrowUtils;
import com.bit.landscapewall.model.entity.Article;
import com.bit.landscapewall.model.request.article.AddArticleRequest;
import com.bit.landscapewall.model.request.article.UpdateArticleRequest;
import com.bit.landscapewall.model.response.ArticleResponse;
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
        Article article = articleService.addArticle(request,files);
        ThrowUtils.throwIf(article == null, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success( "添加文章成功");
    }

    /**
     * 获取文章列表
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<?> getArticleList() {
        // 获取当前用户Id
        Long userId = StpUtil.getLoginIdAsLong();
        // 获取文章列表信息
        List<ArticleResponse> articleResponse = articleService.getArticleList(userId);
        return ResultUtils.success(articleResponse);
    }
    /**
     * 获取文章详情
     * @param articleId
     * @return
     */
    @GetMapping("/detail/{articleId}")
    public BaseResponse<?> getArticleDetail(@PathVariable Long articleId) {
        List<ArticleResponse> articleResponse = articleService.getArticleDetail(articleId);
        return ResultUtils.success(articleResponse);
    }

    /**
     * 删除文章
     * @param articleId
     * @return
     */
    @PostMapping("/delete/{articleId}")
    public BaseResponse<?> deleteArticle(@PathVariable Long articleId) {
        boolean result = articleService.deleteArticle(articleId);
        if (!result) {
            throw new RuntimeException("删除文章失败");
        }
        return ResultUtils.success("删除文章成功");
    }


//    @PostMapping("/update")
//    public BaseResponse<?> updateArticle(@RequestBody UpdateArticleRequest request) {
//        boolean result = articleService.updateArticle(request);
//        if (!result) {
//            throw new RuntimeException("更新文章失败");
//        }
//        return ResultUtils.success("更新文章成功");
//    }
}

