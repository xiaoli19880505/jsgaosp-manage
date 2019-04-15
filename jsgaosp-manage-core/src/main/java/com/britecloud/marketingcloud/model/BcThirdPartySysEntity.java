package com.britecloud.marketingcloud.model;

import lombok.Data;

import java.util.Date;

@Data
public class BcThirdPartySysEntity {

	private String id;
	private String sysName;
	private String areaNo;
	private String sysType;
	private String sysUrl;
	private String memo;
	private String qrCode;
	private String status;
	private String approvalOpinion;
	private Date createDate;
	private String createUserId;
	private Date approvalDate;
	private String approvalUserId;

}
