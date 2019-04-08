/** 
 * 项目名称:91营销云
 * 文件名：RowNumberColumnMapRowMapper.java 
 * author:Administrator
 * 版本信息： 
 * 日期：2015-12-14 
 * Copyright 颢云科技 2015 版权所有 
 */
package com.britecloud.marketingcloud.core.dao.jdbc.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.core.ColumnMapRowMapper;

public class RowNumberColumnMapRowMapper extends ColumnMapRowMapper {

	public Map mapRow(ResultSet rs, int row) throws SQLException {
		
		Map map = (Map) super.mapRow(rs, row);
		map.put("ROW_NO",new Integer(row));
		return map;
	}

}
