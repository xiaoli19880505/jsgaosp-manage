package com.britecloud.marketingcloud.model;

import lombok.Data;
@Data
public class ApplicationEntity {

	private String id;
	private String app_name;
	private String org_id;
	private String org_name;
	private String sys_type;
	private String sys_type_name;
	private String status;//应用状态
	private String create_date;
	private String create_user_id;
	private String access_type;
	//-----------------------------------------------//
	private String info_id;
	private String app_id ;
	private String guide_addr ;
	private String online_addr ;
	private String online_qaq_addr ;
	private String yw_type ;
	private String xz_type ;
	private String memo ;
	private String version_status ;//版本状态
	private String approval_opinion ;
	private String ver_create_date; //版本创建时间
	private String ver_create_user_id; //版本创建人
	private String approval_date ;
	private String approval_user_id ;
	private String bl_type ;
	private String version ;
	private String working_status ;
	private String working_status_name;
	private String server_type ;
	private String icon_url ;
	private String approval_status;
	private String approval_status_name;
	private String edit;
}
