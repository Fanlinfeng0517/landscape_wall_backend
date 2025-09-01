package com.bit.landscapewall.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class ImageResponse {
    /**
     * 图片唯一标识（非数据库字段，由业务生成或拼接）
     */
    private String imageUrl;

    /**
     * 缩略图URL（可选）
     */
//    private String thumbnailUrl;
}