/*
 * @项目名称   Marketing Cloud 91营销云平台
 * @文件名称   OverviewMgmtDao.java
 * @日期      2015-12-7
 * 版权所有     北京颢云信息科技股份有限公司
 */
package com.britecloud.marketingcloud.dao;

import java.util.Date;
import java.util.Map;

public interface OverviewMgmtDao {
	public Map loadCompaignsInOneWeek(Date dateOneWeekAgo,Date dateNow,Map params);
}
