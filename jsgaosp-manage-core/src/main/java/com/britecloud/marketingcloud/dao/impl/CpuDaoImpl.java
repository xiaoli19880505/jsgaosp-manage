package com.britecloud.marketingcloud.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.CpuDao;
import com.britecloud.marketingcloud.model.Cpu;

@Repository
public class CpuDaoImpl extends BaseJdbcDao implements CpuDao{

	@Override
	public void addCpu(Cpu cpu) {
		String sql = loadSQL("addCpu");
		getNamedParameterJdbcTemplate().update(sql, new BeanPropertySqlParameterSource(cpu));		
	}

	@Override
	public List<?> getCpuRunningInfo() {
		String sql = loadSQL("getCpuRunningInfo");
		Map<String, String> paramMap = new HashMap<String, String>();
		return getNamedParameterJdbcTemplate().query(sql, paramMap, new BeanPropertyRowMapper<Cpu>(Cpu.class));
	}

}
