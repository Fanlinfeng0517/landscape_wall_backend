package com.bit.landscapewall.model.request.image;

import lombok.Data;


@Data
public class AddImageRequest {

    /**
     * 发布者ID
     */
//    private Long userId;

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
     * 拍摄地点
     */
    private String location;

    /**
     * 分类
     */
    private String category;

}
