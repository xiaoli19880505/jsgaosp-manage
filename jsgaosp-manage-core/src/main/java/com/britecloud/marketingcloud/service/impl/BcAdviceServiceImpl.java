package com.britecloud.marketingcloud.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.britecloud.marketingcloud.dao.BcAdviceDao;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcAdviceEntity;
import com.britecloud.marketingcloud.service.BcAdviceService;

@Service
public class BcAdviceServiceImpl implements BcAdviceService{

	@Autowired
	private BcAdviceDao bcAdviceDao;
	
	@Override
	public PageDataResult<BcAdviceEntity> listAdvice(Map params) {
		// TODO Auto-generated method stub
		return bcAdviceDao.listAdvice(params);
	}
	
}
