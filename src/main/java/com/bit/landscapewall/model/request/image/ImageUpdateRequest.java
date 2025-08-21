package com.bit.landscapewall.model.request.image;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ImageUpdateRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 图片标题
     */
    private String title;

    /**
     * 拍摄地点
     */
    private String location;

    /**
     * 分类
     */
    private String category;
}
