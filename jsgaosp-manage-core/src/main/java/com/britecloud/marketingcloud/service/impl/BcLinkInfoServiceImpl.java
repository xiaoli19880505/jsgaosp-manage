package com.britecloud.marketingcloud.service.impl;

import com.britecloud.marketingcloud.consants.Constants;
import com.britecloud.marketingcloud.dao.BcLinkInfoDao;
import com.britecloud.marketingcloud.model.BcLinkInfo;
import com.britecloud.marketingcloud.service.BcLinkInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BcLinkInfoServiceImpl implements BcLinkInfoService {

    @Autowired
    private BcLinkInfoDao bcLinkInfoDao;

    @Override
    public String getLinkInfoList(Integer channel, String linkType) {
        JSONObject result = new JSONObject();
        try{
            List<BcLinkInfo> links = bcLinkInfoDao.getLinkInfoList(linkType);
            result.put("links", JSONArray.fromObject(links));
            result.put("code", Constants.RESPONSE_SUCCESS_CODE);
            result.put("message","成功");
        }catch(Exception e){
            result.put("code", Constants.RESPONSE_BAD_CODE);
            result.put("message","异常");
        }
        return result.toString();
    }
}
