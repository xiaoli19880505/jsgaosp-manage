package com.britecloud.marketingcloud.model;

import lombok.Data;

@Data
public class BcOrg {

    private String orgNo;
    private String orgCode;
    private String orgName;
    private String pOrgNo;
    private String status;
    private String orgType;
    private String memo;
    private String chargePerson;
    private String officeTel;
    private String mobileTel;
    private String address;
    private String areaName;
    private String areaNo;
    private String qrCodeImgUrl;
    private String qrCodeUrl;
}
