package com.bit.landscapewall.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
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
     * 获取文章列表（仅获取当前用户的文章）
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
     * 获取所有文章列表
     * @return
     */
    @GetMapping("/getAll")
    public BaseResponse<?> getAllArticleList() {
        // 获取文章列表信息
        List<ArticleResponse> articleResponse = articleService.getAllArticleList();
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

    /**
     * 更新文章
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<?> updateArticle(@Validated @RequestBody UpdateArticleRequest request) {
        // 校验文章是否存在
        Article article = articleService.getById(request.getId());
        if (article == null) {
            return ResultUtils.error(ErrorCode.NOT_FOUND_ERROR, "文章不存在");
        }

        // 校验用户权限（确保当前用户是作者）
        Long currentUserId = StpUtil.getLoginIdAsLong();
        if (!article.getUserId().equals(currentUserId)) {
            return ResultUtils.error(ErrorCode.NO_AUTH_ERROR, "无权修改该文章");
        }

        // 更新文章
        boolean result = articleService.updateArticle(request);
        if (!result) {
            return ResultUtils.error(ErrorCode.OPERATION_ERROR, "更新失败");
        }
        return ResultUtils.success("更新成功");
    }

    /**
     * 审核文章
     * @param articleId
     * @return
     */
    @PostMapping("/audit")
    public BaseResponse<?> audit(@RequestBody Long articleId) {
        // 权限验证，判断是否是作者是否是当前用户
        Long userId = StpUtil.getLoginIdAsLong();
        Article article = articleService.getById(articleId);
        if (!article.getUserId().equals(userId)) {
            return ResultUtils.error(ErrorCode.NO_AUTH_ERROR, "无权审核该文章");
        }
        // 审核文章
        article.setStatus(1);
        articleService.updateById(article);
        return ResultUtils.success("审核成功");
    }


}

