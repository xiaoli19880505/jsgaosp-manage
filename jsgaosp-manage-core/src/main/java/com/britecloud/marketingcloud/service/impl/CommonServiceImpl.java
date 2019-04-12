package com.britecloud.marketingcloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.britecloud.marketingcloud.dao.BcAreaDao;
import com.britecloud.marketingcloud.dao.CommonDao;
import com.britecloud.marketingcloud.model.BcArea;
import com.britecloud.marketingcloud.service.CommonService;
import com.britecloud.marketingcloud.utils.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommonServiceImpl implements CommonService{

    @Autowired
    private CommonDao commonDao;
    @Override
    public List<Map<String, Object>> getList(String tableName, String pColName, String pColValue) {
        return commonDao.getList(tableName,pColName,pColValue);
    }


    /**
     * 获取JSONArray
     * @param tableName 表名
     * @param pColName 父字段名
     * @param colName 编号对应字段名
     * @param pColValue 父字段名的值
     * @return
     * @throws Exception
     */
    public JSONArray getJSONArray(String tableName, String pColName,String colName,String pColValue) throws Exception{
        JSONArray array = new JSONArray();

        List<Map<String, Object>> areaList = getList(tableName,pColName,pColValue);
        for (Map<String, Object> map : areaList){
            String newPvalue = map.get(colName).toString();
            Map<String,Object> newMap = MapUtils.transToLowerCase(map);
            JSONObject object = JSONObject.parseObject(JSON.toJSONString(newMap));
            object.put("children",getAreaArrayByPAreaNo(tableName,pColName,colName,newPvalue));
            array.add(object);
        }
        return array;
    }

    /**
     *
     * @param tableName
     * @param pColName
     * @param colName
     * @param pColValue
     * @return
     * @throws IllegalAccessException
     */
    List<Map<String,Object>> getAreaArrayByPAreaNo(String tableName, String pColName,String colName, String pColValue) throws IllegalAccessException{
        JSONObject object = new JSONObject();
        List<Map<String,Object>> list = new ArrayList<>();
        JSONArray tmp = new JSONArray();
        List<Map<String,Object>> resultList = getList(tableName,pColName,pColValue);
        if(resultList != null && resultList.size()>0){
            for (Map<String,Object> map : resultList){
                String newPvalue = map.get(colName).toString();
                Map<String,Object> newMap = MapUtils.transToLowerCase(map);
                List<Map<String, Object>> nodeList = getAreaArrayByPAreaNo(tableName,pColName,colName,newPvalue);
                if (nodeList != null && nodeList.size()>0) {
                    newMap.put("children", nodeList);
                }
                list.add(newMap);
            }
        }

        return list;
    }

}
