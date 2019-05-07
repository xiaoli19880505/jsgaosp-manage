package com.britecloud.marketingcloud.service.impl;

import com.britecloud.marketingcloud.consants.Constants;
import com.britecloud.marketingcloud.dao.BcAreaDao;
import com.britecloud.marketingcloud.model.BcArea;
import com.britecloud.marketingcloud.model.BcOrg;
import com.britecloud.marketingcloud.service.BcAreaService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BcAreaServiceImpl implements BcAreaService {

    @Autowired
    private BcAreaDao bcAreaDao;

    @Override
    public List<BcArea> listArea(String pAreaNo) { return bcAreaDao.listArea(pAreaNo); }

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

    @Override
    public String getAreaList() {
        JSONObject result = new JSONObject();
        try{
            List<BcArea> cityList = bcAreaDao.listArea("324");
            JSONArray citys = new JSONArray();
            for(BcArea area:cityList){
                JSONObject city = JSONObject.fromObject(area);
                List<BcArea> districtList = bcAreaDao.listArea(area.getAreaNo());
                city.put("districts",JSONArray.fromObject(districtList)) ;
            }
            result.put("areaList",citys);
            result.put("code", Constants.RESPONSE_SUCCESS_CODE);
            result.put("message","成功");
        }catch(Exception e){
            result.put("code", Constants.RESPONSE_BAD_CODE);
            result.put("message","异常");
        }
        return result.toString();
    }


}
