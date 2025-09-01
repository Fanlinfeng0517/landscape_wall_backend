package com.bit.landscapewall.model.request.article;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateArticleRequest {

    /**
     * 文章标题（可选，允许空）
     */
    private String title;

    /**
     * 文章正文（可选，允许空）
     */
    private String content;

    /**
     * 文章状态: 0-草稿, 1-已发布
     */
    private Integer status;

    /**
     * 文章分类（如生活、科技、旅行）
     */
    private String category;


}
