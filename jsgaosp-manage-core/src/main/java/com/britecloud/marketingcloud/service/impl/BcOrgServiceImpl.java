package com.britecloud.marketingcloud.service.impl;

import com.britecloud.marketingcloud.consants.Constants;
import com.britecloud.marketingcloud.dao.BcAreaDao;
import com.britecloud.marketingcloud.dao.BcOrgDao;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcArea;
import com.britecloud.marketingcloud.model.BcOrg;
import com.britecloud.marketingcloud.service.BcAreaService;
import com.britecloud.marketingcloud.service.BcOrgService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BcOrgServiceImpl implements BcOrgService {

    @Autowired
    private BcOrgDao bcOrgDao;

    @Override
    public List<BcOrg> listOrg(String pOrgNo) {
        return bcOrgDao.listOrg(pOrgNo);
    }

    @Override
    public PageDataResult<BcOrg> listDepartmentByOrgId(Map params) {
        return bcOrgDao.listDepartmentByOrgId(params);
    }

    @Override
    public void saveOrg(BcOrg org) {
       bcOrgDao.saveOrg(org);
    }

    @Override
    public void updateOrg(BcOrg org) {
        bcOrgDao.updateOrg(org);
    }

    @Override
    public void deleteOrg(BcOrg org) {
        bcOrgDao.deleteOrg(org);
    }

    @Override
    public BcOrg getOrgById(String id) {
        return bcOrgDao.getOrgById(id);
    }

    @Override
    public Map<String, Object> getOrgByOrgNo(String orgNo) {
        return bcOrgDao.getOrgByOrgNo(orgNo);
    }

    @Override
    public int existsOrgName(BcOrg org) {
        return bcOrgDao.existsOrgName(org);
    }

    @Override
    public String getOrgAreaNameList() {
        JSONObject result = new JSONObject();
        try{
            List<BcOrg> list = bcOrgDao.getOrgAreaNameList();
            JSONArray orgList = new JSONArray();
            getChildOrg("111", list, orgList);
            result.put("orgList",orgList);
            result.put("code", Constants.RESPONSE_SUCCESS_CODE);
            result.put("message","成功");
        }catch(Exception e){
            result.put("code", Constants.RESPONSE_BAD_CODE);
            result.put("message","异常");
        }
        return result.toString();
    }

    /**
     * 获取子组织
     * @param pOrgNo
     * @param list
     * @param orgList
     */
    private void getChildOrg(String pOrgNo, List<BcOrg> list, JSONArray orgList){
        for(BcOrg org: list){
            if(org.getPOrgNo() != null && org.getPOrgNo().equals(pOrgNo)){
                JSONObject obj = JSONObject.fromObject(org);
                JSONArray childOrgList = new JSONArray();
                getChildOrg(org.getOrgNo(), list, childOrgList);
                if(!childOrgList.isEmpty()){
                    obj.put("childOrg",childOrgList);
                }
                orgList.add(obj);
            }
        }
    }
}
