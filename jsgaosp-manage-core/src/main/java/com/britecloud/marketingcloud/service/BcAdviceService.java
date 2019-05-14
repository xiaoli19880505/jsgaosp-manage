package com.britecloud.marketingcloud.service;



import java.util.Map;

import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcAdviceEntity;

public interface BcAdviceService {

	PageDataResult<BcAdviceEntity> listAdvice(Map params);
}
