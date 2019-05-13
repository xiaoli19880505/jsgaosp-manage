package com.britecloud.marketingcloud.model;

import lombok.Data;
@Data
public class BcAdviceEntity {
	private String id ;
	private String user_id;
	private String content;
	private String create_date;
	private String deal_status;
	private String deal_user_id;
	private String deal_date;
	private String status;
}
