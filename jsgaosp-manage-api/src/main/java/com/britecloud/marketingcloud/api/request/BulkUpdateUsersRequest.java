package com.britecloud.marketingcloud.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BulkUpdateUsersRequest {
	UpdateUserRequest[] users;


	@ApiModelProperty(value="用户列表",required=true)
	public UpdateUserRequest[] getUsers() {
		return users;
	}

	public void setUsers(UpdateUserRequest[] users) {
		this.users = users;
	}

	
}
