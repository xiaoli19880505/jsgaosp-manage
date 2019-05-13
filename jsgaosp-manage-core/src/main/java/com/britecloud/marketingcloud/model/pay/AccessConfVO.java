/**
 * 项目名称:91营销云
 * 文件名：BcUser.java
 * author:Administrator
 * 版本信息：
 * 日期：2015-12-14
 * Copyright 颢云科技 2015 版权所有
 */
package com.britecloud.marketingcloud.model.pay;

import lombok.Data;

/**
 * 支付结束
 */
@Data
public class AccessConfVO {

    private String id;
    private String appId;
    private String businessName;
    private String clientPublicKey;
    private String clientPrivateKey;
    private String serverPublicKey;
    private String serverPrivateKey;
    private String description;
    private String status;
    private String applicantId;
    private String payType;
    private String area;
    //用户资料
    private String accessId;
    private String orgCode;

    private String accessName;

    private String contactsName;

    private String contactsPhone;

    private String fileId;
    private String createTime;
    private String updateTime;

}
