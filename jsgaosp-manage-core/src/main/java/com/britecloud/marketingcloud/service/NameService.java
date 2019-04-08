/*
 * @项目名称   Marketing Cloud 91营销云平台
 * @文件名称   NameService.java
 * @日期      2015-12-7
 * 版权所有     北京颢云信息科技股份有限公司
 */
package com.britecloud.marketingcloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.britecloud.marketingcloud.dao.NameDao;

@Service
public class NameService {

	@Autowired
	private NameDao nameDao;

	public int NameExist(String companyId,char sign,String name){
		int count=9999;
		switch(sign){
		//客户分群
		case 's':
			count=SegmentNameExist(companyId,name);
			break;
			//推荐品模板
		case 't':
			count=OfferTmplNameExist(companyId,name);
			break;
			//流程
		case 'f':
			count=FlowChartNameExist(companyId,name);
			break;
			//推荐品
		case 'o':
			count=OfferNameExist(companyId,name);
			break;
			//推荐品列表
		case 'l':
			count=OfferListNameExist(companyId,name);
			break;
			//营销优化
		case 'p':
			count=OptimizeNameExist(companyId,name);
			break;
		default:
			break;
		}
		return count;
	}

	public int SegmentNameExist(String companyId,String name){
		return nameDao.SegmentNameExist(companyId, name);
	}

	public int OfferTmplNameExist(String companyId,String name){
		return nameDao.OfferTmplNameExist(companyId, name);
	}
	public int FlowChartNameExist(String companyId,String name){
		return nameDao.FlowChartNameExist(companyId, name);
	}

	public int OfferNameExist(String companyId,String name){
		return nameDao.OfferNameExist(companyId, name);
	}

	public int OfferListNameExist(String companyId,String name){
		return nameDao.OfferListNameExist(companyId, name);
	}

	public int OptimizeNameExist(String companyId,String name){
		return nameDao.optimizeNameExist(companyId, name);
	}
}
