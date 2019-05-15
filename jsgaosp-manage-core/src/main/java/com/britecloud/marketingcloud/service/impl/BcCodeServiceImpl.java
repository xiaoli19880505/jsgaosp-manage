package com.britecloud.marketingcloud.service.impl;

import com.britecloud.marketingcloud.consants.Constants;
import com.britecloud.marketingcloud.dao.BcCodeDao;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.BcArea;
import com.britecloud.marketingcloud.model.BcCodeSort;
import com.britecloud.marketingcloud.service.BcCodeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public PageDataResult<BcCodeSort> listCodeSortDetailBypCodeSortId(Map params) {
        return bcCodeDao.listCodeSortDetailBypCodeSortId(params);
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



    @Override
    public BcCodeSort getCodeSortById(BcCodeSort codeSort) {
        return bcCodeDao.getCodeSortById(codeSort);
    }

    @Override
    public String getCodeList(Integer channel, String sortCode) {
        JSONObject result = new JSONObject();
        try{
            List<BcCodeSort> codeList = bcCodeDao.getCodeList(sortCode);
            result.put("codeList", JSONArray.fromObject(codeList));
            result.put("code", Constants.RESPONSE_SUCCESS_CODE);
            result.put("message","成功");
        }catch(Exception e){
            result.put("code", Constants.RESPONSE_BAD_CODE);
            result.put("message","异常");
        }
        return result.toString();
    }
}
