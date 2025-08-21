package com.bit.landscapewall.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bit.landscapewall.common.BaseResponse;
import com.bit.landscapewall.common.ResultUtils;
import com.bit.landscapewall.exception.ErrorCode;
import com.bit.landscapewall.exception.ThrowUtils;
import com.bit.landscapewall.model.entity.Image;
import com.bit.landscapewall.model.request.image.ImageSearchRequest;
import com.bit.landscapewall.model.request.image.AddImageRequest;
import com.bit.landscapewall.model.request.image.AuditImage;
import com.bit.landscapewall.model.request.image.ImageUpdateRequest;
import com.bit.landscapewall.model.response.ImageInfoResponse;
import com.bit.landscapewall.service.ImageService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Resource
    private ImageService imageService;

    /**
     * 创建图片
     * @param request
     * @return
     */
    @PostMapping("/addImage")
    public BaseResponse<?> addImage(@RequestBody @Validated AddImageRequest request) {
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        Long result = imageService.addImage(request);
        return ResultUtils.success(result);
    }

    /**
     * 审核图片
     * @param request
     * @return
     */
    @SaCheckRole("ADMIN")
    @PostMapping("/auditImage")
    public BaseResponse<?> auditImage(@RequestBody @Validated AuditImage request) {
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        Boolean result = imageService.auditImage(request);
        return ResultUtils.success(result);
    }

    /**
     * 获取图片
     * @param request 根据条件查询
     */
    @PostMapping("/getImage")
    public BaseResponse<Page<ImageInfoResponse>> getImage(@RequestBody(required = false) @Validated ImageSearchRequest request) {
//        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        if (request == null) {
            request = new ImageSearchRequest();
        }
        long current = request.getCurrent();
        long pageSize = request.getPageSize();
        Page<Image> imagePage = imageService.page(new Page<>(current, pageSize),
                imageService.getQueryWrapper(request));
        Page<ImageInfoResponse> imageInfoResponsePage = new Page<>(current,pageSize,imagePage.getTotal());
        List<ImageInfoResponse> imageInfoResponseList = imageService.getImageInfoResponseList(imagePage.getRecords());
        imageInfoResponsePage.setRecords(imageInfoResponseList);
        return ResultUtils.success(imageInfoResponsePage);
    }

    @PostMapping("/updateImage")
    public BaseResponse<?> updateImage(@RequestBody @Validated ImageUpdateRequest request) {
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);
        Boolean result = imageService.updateImage(request);
        return ResultUtils.success(result);
    }





}
