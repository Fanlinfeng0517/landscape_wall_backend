package com.bit.landscapewall.model.request.comment;


import lombok.Data;

@Data
public class UpdateCommentRequest {
    private Long id;
    private String content;
}
