package com.bit.landscapewall.model.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticleResponse {
    /**
     * 文章ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

//    /**
//     * 作者ID（关联用户表，此处简化）
//     */
//    @TableField(value = "user_id")
//    private Long userId;

    private String username;

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

    /**
     * 浏览量（审核通过后累计）
     */
    private Integer viewCount;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 关联的图片列表（审核通过的图片）
     */
    private List<ImageResponse> images;
}
