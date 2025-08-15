package com.bit.landscapewall.model.request.image;

import com.baomidou.mybatisplus.annotation.TableField;
import com.bit.landscapewall.common.PageRequest;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ImageSearchRequest extends PageRequest implements Serializable {
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
     * 发布时间
     */
    @TableField(value = "created_at")
    private Date created_at;
}
