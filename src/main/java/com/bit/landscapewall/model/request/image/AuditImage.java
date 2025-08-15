package com.bit.landscapewall.model.request.image;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;


@Data
public class AuditImage implements Serializable {
    /**
     * 图片ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 审核状态：0-待审核，1-通过，2-驳回
     */
    @TableField(value = "audit_status")
    private Integer audit_status;

    /**
     * 驳回原因
     */
    @TableField(value = "reject_reason")
    private String reject_reason;
}
