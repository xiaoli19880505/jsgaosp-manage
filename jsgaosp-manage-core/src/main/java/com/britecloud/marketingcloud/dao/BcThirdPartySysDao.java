package com.britecloud.marketingcloud.dao;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcThirdPartySysEntity;
import com.britecloud.marketingcloud.model.expand.BcThirdPartySysEntityExpand;

import java.util.Map;

public interface BcThirdPartySysDao {

	 PageDataResult<BcThirdPartySysEntityExpand> listThirdPartySys(Map params);
	
	void saveSysArgs(BcThirdPartySysEntity args);
	
	int existsArgsKey(BcThirdPartySysEntity args);
	
	void updateThirdPartySys(BcThirdPartySysEntity args);
	
	void deleteThirdPartySys(BcThirdPartySysEntity args);

	void approveSysAppliant(BcThirdPartySysEntity args);
}
