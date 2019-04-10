package com.britecloud.marketingcloud.service.impl;

import com.britecloud.marketingcloud.dao.BcAreaDao;
import com.britecloud.marketingcloud.model.BcArea;
import com.britecloud.marketingcloud.service.BcAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BcAreaServiceImpl implements BcAreaService {

    @Autowired
    private BcAreaDao bcAreaDao;

    @Override
    public List<BcArea> listArea(BcArea area) {
        return bcAreaDao.listArea(area);
    }

    @Override
    public void saveArea(BcArea area) {
        bcAreaDao.saveArea(area);
    }

    @Override
    public void updateArea(BcArea area) {
        bcAreaDao.updateArea(area);
    }

    @Override
    public void deleteArea(BcArea area) {
        bcAreaDao.deleteArea(area);
    }

    @Override
    public BcArea getAreaById(String id) {
        return bcAreaDao.getAreaById(id);
    }
}
