package com.bit.landscapewall.model.request.article;



import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AddArticleRequest implements Serializable {


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
