package com.britecloud.marketingcloud.model;

import java.util.Date;

import lombok.Data;
@Data
public class BcSysApplicationEntity {

	private String Id;
	private String sysId;
	private String appName;
	private String guideAddr;
	private String onlineAddr;
	private String onlineQaqAddr;
	private String areaNo;
	private String ywType;
	private String xzType;
	private String memo;
	private String status;
	private String approvalOpinion;
	private Date createDate;
	private String createUserId;
	private Date approvalDate;
	private String approvalUserId;
	private String blType;
}
