/*
 * @项目名称   Marketing Cloud 91营销云平台
 * @文件名称   NameDao.java
 * @日期      2015-12-7
 * 版权所有     北京颢云信息科技股份有限公司
 */
package com.britecloud.marketingcloud.dao;

public interface NameDao {

	int SegmentNameExist(String companyId,String name);
	
	int OfferTmplNameExist(String companyId,String name);
	
	int FlowChartNameExist(String companyId,String name);
	
	int OfferNameExist(String companyId,String name);
	
	int OfferListNameExist(String companyId,String name);
	
	int optimizeNameExist(String companyId,String name);
	
}
