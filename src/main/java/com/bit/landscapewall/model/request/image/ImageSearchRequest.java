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
    private String title;

    /**
     * 拍摄地点
     */
    private String location;

    /**
     * 分类
     */
    private String category;

    /**
     * 发布时间
     */
    private Date createdAt;
}
