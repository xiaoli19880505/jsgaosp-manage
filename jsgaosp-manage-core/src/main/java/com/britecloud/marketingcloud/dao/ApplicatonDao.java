package com.britecloud.marketingcloud.dao;


import java.util.Map;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.ApplicationEntity;


public interface ApplicatonDao {

	int existsArgsKey(ApplicationEntity args);
	int existsApplicationInfo(ApplicationEntity args);
	void saveApplication(ApplicationEntity args);
	void updateApplication(ApplicationEntity args);
	public PageDataResult<ApplicationEntity> listSysApplications(Map params);
}
