package com.britecloud.marketingcloud.dao;

import java.util.List;
import java.util.Map;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcAdviceEntity;

public interface BcAdviceDao {
	PageDataResult<BcAdviceEntity> listAdvice(Map params);
	
}
