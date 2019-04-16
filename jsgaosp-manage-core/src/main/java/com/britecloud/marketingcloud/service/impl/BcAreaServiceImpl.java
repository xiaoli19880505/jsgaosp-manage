package com.britecloud.marketingcloud.service.impl;

import com.britecloud.marketingcloud.dao.BcAreaDao;
import com.britecloud.marketingcloud.model.BcArea;
import com.britecloud.marketingcloud.service.BcAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BcAreaServiceImpl implements BcAreaService {

    @Autowired
    private BcAreaDao bcAreaDao;

    @Override
    public List<BcArea> listArea(String pAreaNo) {
        return bcAreaDao.listArea(pAreaNo);
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

    @Override
    public Map<String,Object> getAreaByAreaNo(String areaNo) {
        return bcAreaDao.getAreaByAreaNo(areaNo);
    }

    /**
     * 根据areaNo判断是否存在
     * @param area
     * @return
     */
    @Override
    public int existsAreaNo(BcArea area){
        return bcAreaDao.existsAreaNo(area);
    }
}
