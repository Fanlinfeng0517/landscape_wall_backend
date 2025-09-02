package com.bit.landscapewall.model.request.comment;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class addCommentRequest implements Serializable {

    /**
     * 评论所属文章Id
     */
    @TableField(value = "article_id")
    private Long articleId;

    /**
     * 评论内容
     */
    @TableField(value = "content")
    private String content;
}
