/*
 * @项目名称   Marketing Cloud 91营销云平台
 * @文件名称   NameDaoImpl.java
 * @日期      2015-12-7
 * 版权所有     北京颢云信息科技股份有限公司
 */
package com.britecloud.marketingcloud.dao.impl;

import org.springframework.stereotype.Repository;

import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.NameDao;

@Repository
public class NameDaoImpl extends BaseJdbcDao implements NameDao{
	@Override
	public int SegmentNameExist(String companyId, String name) {
		String sql="select count(*) from BC_SEGMENT where COMPANY_ID='"+companyId +"' and NAME='"+name+"'";
		int count=getJdbcTemplate().queryForInt(sql); 
		return count;
	}

	@Override
	public int OfferTmplNameExist(String companyId, String name) {
		String sql="select count(*) from BC_OFFER_TMPL where COMPANY_ID='"+companyId +"' and NAME='"+name+"'";
		int count=getJdbcTemplate().queryForInt(sql); 
		return count;
	}

	@Override
	public int FlowChartNameExist(String companyId, String name) {
		String sql="select count(*) from BC_FLOWCHART where COMPANY_ID='"+companyId +"' and NAME='"+name+"'";
		int count=getJdbcTemplate().queryForInt(sql); 
		return count;
	}

	@Override
	public int OfferNameExist(String companyId, String name) {
		String sql="select count(*) from BC_OFFER where COMPANY_ID='"+companyId +"' and NAME='"+name+"'";
		int count=getJdbcTemplate().queryForInt(sql); 
		return count;
	}

	@Override
	public int OfferListNameExist(String companyId, String name) {
		String sql="select count(*) from BC_OFFER_LIST where COMPANY_ID='"+companyId +"' and NAME='"+name+"'";
		int count=getJdbcTemplate().queryForInt(sql); 
		return count;
	}

	@Override
	public int optimizeNameExist(String companyId, String name) {
		String sql="select count(*) from BC_OPTIMIZE where COMPANY_ID='"+companyId +"' and NAME='"+name+"'";
		int count=getJdbcTemplate().queryForInt(sql); 
		return count;
	}

}
