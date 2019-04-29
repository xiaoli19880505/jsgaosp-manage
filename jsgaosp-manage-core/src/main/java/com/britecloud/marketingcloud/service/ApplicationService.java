package com.britecloud.marketingcloud.service;

import java.util.Map;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.ApplicationEntity;

public interface ApplicationService {

	boolean existsArgsKey(ApplicationEntity args);
	void saveApplication(ApplicationEntity args);
    void updateApplication(ApplicationEntity args);
    PageDataResult<ApplicationEntity> listSysApproves(Map params);
}
