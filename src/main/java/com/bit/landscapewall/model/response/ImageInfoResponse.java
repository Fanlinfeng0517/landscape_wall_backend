package com.bit.landscapewall.model.response;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.bit.landscapewall.model.entity.Image;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.util.Date;


@Data
@AutoMapper(target = Image.class)
public class ImageInfoResponse {

    /**
     * 发布者姓名
     */
    private String username;

    /**
     * 图片标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 图片描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 图片URL
     */
    @TableField(value = "url")
    private String url;

    /**
     * 缩略图URL
     */
    @TableField(value = "thumbnail_url")
    private String thumbnail_url;

    /**
     * 拍摄地点
     */
    @TableField(value = "location")
    private String location;

    /**
     * 分类
     */
    @TableField(value = "category")
    private String category;


    /**
     * 浏览量
     */
    @TableField(value = "view_count")
    private Integer view_count;

    /**
     * 发布时间
     */
    @TableField(value = "created_at")
    private Date created_at;

}
