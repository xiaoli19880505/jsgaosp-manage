package com.britecloud.marketingcloud.service;

import com.alibaba.fastjson.JSONArray;

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
     * 根据父节点id获取公共树,获取下属所有节点，不包含父节点
     * @param colName 编号对应字段名
     * @param tableName 表名
     * @param pColName 父字段名
     * @param pColValue 父字段名的值
     * @return
     */
    public JSONArray getJSONArray(String tableName, String pColName, String colName, String pColValue) throws Exception;

    /**
     * 获根据父节点Id,获取父节点及其所有子节点
     * @param colName 编号对应字段名
     * @param tableName 表名
     * @param pColName 父字段名
     * @param pColValue 父字段名的值
     * @return
     */

//    List<BcCodeSort> getCodeList(BcCodeSort codeSort);

    /**
     * 查询当前登录人，已经添加的系统列表
     * @param params 登录人的areaNO
     * @return
     */
    public List<Map<String, Object>> getSysList(Map<String, String> params);
}
