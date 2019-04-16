package com.britecloud.marketingcloud.model;

import lombok.Data;

import java.util.Date;

@Data
public class BcLoginLog {

    private String loginId;
    private String type;
    private String title;
    private String content;
    private String triggerTime;
    private String source;

}
