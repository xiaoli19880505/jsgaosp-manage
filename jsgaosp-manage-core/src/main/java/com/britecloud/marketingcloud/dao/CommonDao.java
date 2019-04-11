package com.britecloud.marketingcloud.dao;

import java.util.List;
import java.util.Map;

public interface CommonDao {

    /**
     * 查询树形表数据
     * @param tableName 表名
     * @param pColName 父字段名
     * @param pColValue 父字段名的值
     * @return
     */
    List<Map<String,Object>> getList(String tableName,String pColName,String pColValue);
}
