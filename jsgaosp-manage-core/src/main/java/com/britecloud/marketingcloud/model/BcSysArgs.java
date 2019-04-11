package com.britecloud.marketingcloud.model;

import lombok.Data;

/**
 * 系统参数
 */
@Data
public class BcSysArgs {

    private String id;
    private String system;
    private String argsKey;
    private String argsValue;
    private String status;
    private String memo;

}
