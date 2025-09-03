package com.bit.landscapewall.controller;


import com.bit.landscapewall.common.BaseResponse;
import com.bit.landscapewall.common.ResultUtils;
import com.bit.landscapewall.exception.ErrorCode;
import com.bit.landscapewall.exception.ThrowUtils;
import com.bit.landscapewall.model.request.comment.UpdateCommentRequest;
import com.bit.landscapewall.model.request.comment.addCommentRequest;
import com.bit.landscapewall.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
        boolean result = commentService.addComment(request);
        ThrowUtils.throwIf(!result, ErrorCode.valueOf("评论失败"));
        return ResultUtils.success("评论成功");
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @RequestMapping("/deleteComment/{id}")
    public BaseResponse<?> deleteComment(@PathVariable Long id){
        boolean result = commentService.deleteComment(id);
        ThrowUtils.throwIf(!result, ErrorCode.valueOf("删除评论失败"));
        return ResultUtils.success("删除评论成功");
    }

    /**
     * 更新评论
     * @param request
     * @return
     */
    @PostMapping("/updateComment")
    public BaseResponse<?> updateComment(@RequestBody @Validated UpdateCommentRequest request){
        boolean result = commentService.updateComment(request);
        ThrowUtils.throwIf(!result, ErrorCode.valueOf("更新评论失败"));
        return ResultUtils.success("更新评论成功");
    }


//    /**
//     * 回复评论
//     * @param request
//     * @return
//     */
//    @PostMapping("/replyComment")
//    public BaseResponse<?> replyComment(@RequestBody @Validated addCommentRequest request){
//        boolean result = commentService.replyComment(request);
//        ThrowUtils.throwIf(!result, ErrorCode.valueOf("回复评论失败"));
//        return ResultUtils.success("回复评论成功");
//    }
}
