package com.britecloud.marketingcloud.model.pay;

import lombok.Data;

import java.util.Date;
@Data
public class AccessApprovalPO {
    private String id;

    private String applyId;

    private String applyInfoId;

    private String approvalTime;

    private String approvalStatus;

    private String approvalOpinion;

    private String approvalUserId;

    private String auditTime;

    private String auditStatus;

    private String auditOpinion;

    private String auditUserId;

    private String createTime;
}