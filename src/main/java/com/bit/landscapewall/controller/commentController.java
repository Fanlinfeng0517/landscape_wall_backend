package com.bit.landscapewall.controller;


import com.bit.landscapewall.common.BaseResponse;
import com.bit.landscapewall.model.request.comment.addCommentRequest;
import com.bit.landscapewall.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/comment")
public class commentController {

    @Resource
    private CommentService commentService;


    /**
     * 创建评论
     * @RequestBody Comment comment
     * @return
     */
    @RequestMapping("/addComment")
    public BaseResponse<?> addComment(@RequestBody @Validated addCommentRequest request){
        return null;
    }
}
