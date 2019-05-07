/**
 * 项目名称:91营销云
 * 文件名：BcUser.java
 * author:Administrator
 * 版本信息：
 * 日期：2015-12-14
 * Copyright 颢云科技 2015 版权所有
 */
package com.britecloud.marketingcloud.model;

import lombok.Data;

@Data
public class PayAppConf {

    private String id;
    private String appId;
    private String businessName;
    private String clientPublicKey;
    private String clientPrivateKey;
    private String serverPublicKey;
    private String serverPrivateKey;
    private String description;
    private String valid;
    private String createTime;

}
