package com.bit.landscapewall.model.request.article;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateArticleRequest {
    /**
     * 文章ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;


    /**
     * 文章标题（可选，允许空）
     */
    private String title;

    /**
     * 文章正文（可选，允许空）
     */
    private String content;


    /**
     * 文章分类（如生活、科技、旅行）
     */
    private String category;


}
