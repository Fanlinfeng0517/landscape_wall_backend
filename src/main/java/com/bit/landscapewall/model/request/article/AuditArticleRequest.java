package com.bit.landscapewall.model.request.article;


import lombok.Data;

import java.io.Serializable;

@Data
public class AuditArticleRequest implements Serializable {
    private Long articleId;
    private Integer auditStatus;
    private String auditComment;
}
