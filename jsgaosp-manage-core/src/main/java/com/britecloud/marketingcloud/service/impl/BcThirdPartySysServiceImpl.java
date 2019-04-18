package com.britecloud.marketingcloud.service.impl;

import com.britecloud.marketingcloud.dao.BcThirdPartySysDao;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcThirdPartySysEntity;
import com.britecloud.marketingcloud.model.expand.BcThirdPartySysEntityExpand;
import com.britecloud.marketingcloud.service.BcThirdPartySysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BcThirdPartySysServiceImpl implements BcThirdPartySysService {

	@Autowired
	private BcThirdPartySysDao bcThirdPartySysDao;


	@Override
	public PageDataResult<BcThirdPartySysEntityExpand> listThirdPartySys(Map params) {
		return bcThirdPartySysDao.listThirdPartySys(params);
	}

	@Override
	public void saveSysArgs(BcThirdPartySysEntity args) {
		bcThirdPartySysDao.saveSysArgs(args);
	}

	@Override
	public int existsArgsKey(BcThirdPartySysEntity args) {
		return bcThirdPartySysDao.existsArgsKey(args);
	}

	@Override
	public void updateBcThirdPartySysEntity(BcThirdPartySysEntity args) {
		bcThirdPartySysDao.updateThirdPartySys(args);
	}

	@Override
	public void deleteBcThirdPartySysEntity(BcThirdPartySysEntity args) {
		bcThirdPartySysDao.deleteThirdPartySys(args);
	}
}
