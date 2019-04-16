package com.britecloud.marketingcloud.dao.impl;

import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.CommonDao;
import com.britecloud.marketingcloud.model.BcArea;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommonDaoImpl extends BaseJdbcDao implements CommonDao{

    /**
     * 根据父ID获取子数据
     * @param tableName 表名
     * @param pColName 父字段名
     * @param pColValue 父字段名的值
     * @return
     */
    @Override
    public List<Map<String, Object>> getList(String tableName, String pColName, String pColValue) {
        String sql = "select * from "+tableName+" where "+pColName+"=:pColValue";

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("pColValue",pColValue);
        return getNamedParameterJdbcTemplate().queryForList(sql, paramMap);
    }




}
