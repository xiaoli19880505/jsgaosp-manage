package com.britecloud.marketingcloud.service;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcThirdPartySysEntity;
import com.britecloud.marketingcloud.model.expand.BcThirdPartySysEntityExpand;

import java.util.Map;

public interface BcThirdPartySysService {

	PageDataResult<BcThirdPartySysEntityExpand> listThirdPartySys(Map params);
	
	void saveSysArgs(BcThirdPartySysEntity args);
	int existsArgsKey(BcThirdPartySysEntity args);
	void updateBcThirdPartySysEntity(BcThirdPartySysEntity args);
	void deleteBcThirdPartySysEntity(BcThirdPartySysEntity args);
}
