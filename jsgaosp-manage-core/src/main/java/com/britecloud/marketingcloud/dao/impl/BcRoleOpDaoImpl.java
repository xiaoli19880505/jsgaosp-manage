package com.britecloud.marketingcloud.dao.impl;

import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.BcRoleOpDao;
import com.britecloud.marketingcloud.model.BcRoleOp;
import com.britecloud.marketingcloud.utils.UUIDUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BcRoleOpDaoImpl extends BaseJdbcDao implements BcRoleOpDao {
    @Override
    public void setRoleOp(BcRoleOp bcRoleOp) {
        bcRoleOp.setId(UUIDUtils.generateUUID());
        String sql = loadSQL("setRoleOp");
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(bcRoleOp);
        getNamedParameterJdbcTemplate().update(sql, parameters);

    }

    @Override
    public List<BcRoleOp> getRoleOp(Map params) {
        String sql = loadSQL("listDepartByOrgId");
        return getNamedParameterJdbcTemplate().query(sql, params, new BeanPropertyRowMapper<BcRoleOp>(BcRoleOp.class));

    }


    @Override
    public void cleanRoleOpByRoleId(String roleId) {
        String sql = loadSQL("cleanRoleOpByRoleId");
//        Map params=new HashMap();
//        params.put("roleId",roleId);
        BcRoleOp bcRoleOp=new BcRoleOp();
        bcRoleOp.setRoleId(roleId);
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(bcRoleOp);
        getNamedParameterJdbcTemplate().update(sql, parameters);

    }


}
