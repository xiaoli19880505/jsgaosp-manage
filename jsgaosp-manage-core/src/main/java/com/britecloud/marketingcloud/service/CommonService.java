package com.britecloud.marketingcloud.service;

import com.alibaba.fastjson.JSONArray;
import com.britecloud.marketingcloud.model.BcCode;
import com.britecloud.marketingcloud.model.BcCodeSort;

import java.util.List;
import java.util.Map;

public interface CommonService {

    /**
     * 查询树形表数据
     * @param tableName 表名
     * @param pColName 父字段名
     * @param pColValue 父字段名的值
     * @return
     */
    List<Map<String,Object>> getList(String tableName, String pColName, String pColValue);

    /**
     * 获取公共树
     * @param colName 编号对应字段名
     * @param tableName 表名
     * @param pColName 父字段名
     * @param pColValue 父字段名的值
     * @return
     */
    public JSONArray getJSONArray(String tableName, String pColName, String colName, String pColValue) throws Exception;

    List<BcCode> getCodeList(BcCodeSort codeSort);
}
