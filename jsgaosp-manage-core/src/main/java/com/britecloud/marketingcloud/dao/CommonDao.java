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


    /**
     * 查询单个节点数据
     * @param tableName 表名
     * @param ColName 字段名
     * @param ColValue 字段名的值
     * @return
     */
    List<Map<String,Object>> getNode(String tableName,String ColName,String ColValue);

    /**
     * 查询当前登录人，已经添加的系统列表
     * @param params 登录人的areaNO
     * @return
     */
    List<Map<String,Object>> getSysList(Map<String,String> params);


}
