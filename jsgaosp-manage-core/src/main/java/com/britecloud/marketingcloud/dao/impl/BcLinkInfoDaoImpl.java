package com.britecloud.marketingcloud.dao.impl;

import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.BcLinkInfoDao;
import com.britecloud.marketingcloud.model.BcLinkInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BcLinkInfoDaoImpl extends BaseJdbcDao implements BcLinkInfoDao {
    @Override
    public List<BcLinkInfo> getLinkInfoList(String linkType) {
        String sql = loadSQL("getLinkInfoList");
        Map params = new HashMap();
        params.put("linkType", linkType);
        if(linkType != null && !linkType.isEmpty()){
            sql += " AND link_type =:linkType";
        }
        List<BcLinkInfo> list = getNamedParameterJdbcTemplate().query(sql, params,
                new BeanPropertyRowMapper(BcLinkInfo.class));
        return list;
    }
}
