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
    private String title;

    /**
     * 图片描述
     */
    private String description;

    /**
     * 图片URL
     */
    private String url;

    /**
     * 缩略图URL
     */
    private String thumbnailUrl;

    /**
     * 拍摄地点
     */
    private String location;

    /**
     * 分类
     */
    private String category;

    /**
     * 浏览量
     */
    private Integer viewCount;

    /**
     * 发布时间
     */
    private Date createdAt;

}
