package com.britecloud.marketingcloud.service.impl;

import com.britecloud.marketingcloud.dao.BcCodeDao;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcCodeSort;
import com.britecloud.marketingcloud.service.BcCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BcCodeServiceImpl implements BcCodeService {

    @Autowired
    private BcCodeDao bcCodeDao;

    @Override
    public PageDataResult<BcCodeSort> listCodeSort(Map params) {
        return bcCodeDao.listCodeSort(params);
    }

    @Override
    public void saveCodeSort(BcCodeSort codeSort) {
        bcCodeDao.saveCodeSort(codeSort);
    }

    @Override
    public void updateCodeSort(BcCodeSort codeSort) {
        bcCodeDao.updateCodeSort(codeSort);
    }

    @Override
    public void deleteCodeSort(BcCodeSort codeSort) {
        bcCodeDao.deleteCodeSort(codeSort);
    }
}
