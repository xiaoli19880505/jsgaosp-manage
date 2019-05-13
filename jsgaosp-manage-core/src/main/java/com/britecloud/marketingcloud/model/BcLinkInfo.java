package com.britecloud.marketingcloud.model;

import lombok.Data;

import java.util.Date;

@Data
public class BcLinkInfo {
    private String id;
    private String linkName;
    private String linkUrl;
    private String iconUrl;
    private Date createDate;
    private String createUserId;
    private Date updateDate;
    private String updateUserId;
    private String status;
    private String linkType;
}
