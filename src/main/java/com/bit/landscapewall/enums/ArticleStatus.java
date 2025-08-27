package com.bit.landscapewall.enums;


// 文章状态枚举类
public enum ArticleStatus {
    DRAFT(1, "草稿"),    // 状态码1表示草稿
    PUBLISHED(2, "已发布"), // 状态码2表示已发布
    DELETED(3, "已删除");   // 状态码3表示已删除
    
    private final Integer code;  // 状态编码（数字）
    private final String desc;   // 状态描述
    
    // 构造方法、getter等
    ArticleStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public String getDesc() {
        return desc;
    }
}