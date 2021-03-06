package com.britecloud.marketingcloud.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.britecloud.marketingcloud.model.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.britecloud.marketingcloud.core.dao.jdbc.BaseJdbcDao;
import com.britecloud.marketingcloud.dao.ApplicatonDao;
import com.britecloud.marketingcloud.domain.PageDataResult;
import com.britecloud.marketingcloud.model.ApplicationEntity;
import com.britecloud.marketingcloud.utils.PageUtils;
import com.britecloud.marketingcloud.utils.UUIDUtils;

@Repository
public class ApplicatonDaoImpl extends BaseJdbcDao implements ApplicatonDao {

	@Override
	public void saveApplication(ApplicationEntity args) {
		String id=UUIDUtils.generateUUID();
		args.setId(id);
		args.setStatus("1");
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		args.setCreate_date(format.format(date));
		String sql = loadSQL("insertApplication");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		getNamedParameterJdbcTemplate().update(sql, parameters);
		
		String sql2 = loadSQL("insertApplicationInfo");
		args.setApp_id(id);
		args.setVersion_status("1");
		args.setApproval_status("00");
		args.setWorking_status("00");
		args.setVersion("1.0");
		args.setInfo_id(UUIDUtils.generateUUID());
		SqlParameterSource parameters2 = new BeanPropertySqlParameterSource(args);
		getNamedParameterJdbcTemplate().update(sql2, parameters2);
		insertBcQrcode(args);
	}

	public void saveAppInfo(ApplicationEntity args) {
		String sql = loadSQL("insertApplicationInfo");
		args.setVersion_status("1");
		args.setApproval_status("00");
		args.setWorking_status("00");
		args.setApproval_user_id("");
		args.setApproval_date("");
		String version = args.getVersion();
		args.setInfo_id(UUIDUtils.generateUUID());
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		getNamedParameterJdbcTemplate().update(sql, parameters);
	}
	
	
	public void insertAppInfo(ApplicationEntity args) {
		String sql = loadSQL("insertApplicationInfo");
		args.setInfo_id(UUIDUtils.generateUUID());
		args.setApproval_status("00");
		args.setWorking_status("00");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		getNamedParameterJdbcTemplate().update(sql, parameters);
	}
	public void insertBcQrcode(ApplicationEntity args) {
		String sql = loadSQL("insertBcQrcode");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id",UUIDUtils.generateUUID());
		paramMap.put("org_id",args.getOrg_id());
		paramMap.put("app_id",args.getApp_id());
		paramMap.put("qr_code_url",args.getQr_code_url());
		paramMap.put("qr_code_img_url",args.getQr_code_img_url());
		paramMap.put("qr_code_type","1");
		paramMap.put("create_user_id",args.getCreate_user_id());
		paramMap.put("status","1");
		getNamedParameterJdbcTemplate().update(sql, paramMap);
	}

	public void updateAppInfo(ApplicationEntity args) {
		String sql = loadSQL("updateAppWorkingStatus");

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("app_id",args.getApp_id());
		getNamedParameterJdbcTemplate().update(sql, paramMap);
	}

	public void rollbackVersion(ApplicationEntity args) {
//		//下架老的应用数据
//		updateAppInfo(args);
		//info 表插入新的版本数据
		saveAppInfo(args);
	}


	@Override
	public int existsArgsKey(ApplicationEntity args) {
		// TODO Auto-generated method stub
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		String sql = loadSQL("existsArgsKey");
		return getNamedParameterJdbcTemplate().queryForInt(sql, parameters);
	}

	@Override
	public int existsApplicationInfo(ApplicationEntity args) {
		// TODO Auto-generated method stub
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(args);
		String sql = loadSQL("existsApplicationInfo");
		return getNamedParameterJdbcTemplate().queryForInt(sql, parameters);
	}

	private void updateBcQrcode(ApplicationEntity args) {
		String sql = loadSQL("updateBcQrcode");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id",args.getCode_Id());
		paramMap.put("qr_code_url",args.getQr_code_url());
		paramMap.put("qr_code_img_url",args.getQr_code_img_url());
		paramMap.put("update_user_id",args.getApproval_user_id());
		paramMap.put("id",args.getCode_Id());
		
		getNamedParameterJdbcTemplate().update(sql, paramMap);
	}
	
	@Override
	public void updateApplicationInfo(ApplicationEntity args) {
		String sql = loadSQL("updateApplicationInfo");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id",args.getInfo_id());
		paramMap.put("guide_addr",args.getGuide_addr());
		paramMap.put("online_addr",args.getOnline_addr());
		paramMap.put("online_qaq_addr",args.getOnline_qaq_addr());
		paramMap.put("yw_type",args.getYw_type());
		paramMap.put("xz_type",args.getXz_type());
		paramMap.put("memo",args.getMemo());
		paramMap.put("bl_type",args.getBl_type());
		paramMap.put("server_type",args.getServer_type());
		paramMap.put("icon_url",args.getIcon_url());
		paramMap.put("legal_basis",args.getLegal_basis());
		paramMap.put("conditions",args.getConditions());
		/////////////////////////////////////////////////
		paramMap.put("approval_user_id",args.getApproval_user_id());
		paramMap.put("approval_status",args.getApproval_status());
		paramMap.put("approval_opinion",args.getApproval_opinion());
		getNamedParameterJdbcTemplate().update(sql, paramMap);
		updateBcQrcode(args);
	}
	
	
	
	@Override
	public void updateStatus(ApplicationEntity args) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("approval_user_id",args.getApproval_user_id());
		paramMap.put("version_status",args.getVersion_status());
		paramMap.put("id",args.getId());
		paramMap.put("status",args.getStatus());
		//更新主表状态
		String sql = loadSQL("updateApplication");
		getNamedParameterJdbcTemplate().update(sql, paramMap);
		/*//更新详细表状态
		String sqlinfo = loadSQL("updateInfoStatus");
		getNamedParameterJdbcTemplate().update(sqlinfo, paramMap);*/
	}
	
	
	@Override
	public void updateApplication(ApplicationEntity args) {
		String sql = loadSQL("updateApplication");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id",args.getId());
		paramMap.put("status",args.getStatus());
		getNamedParameterJdbcTemplate().update(sql, paramMap);
	}
	@Override
	public void updateAudit(ApplicationEntity args) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id",args.getInfo_id());
		paramMap.put("approval_status",args.getApproval_status());
		paramMap.put("working_status",args.getWorking_status());
		paramMap.put("approval_user_id",args.getApproval_user_id());
		paramMap.put("approval_opinion",args.getApproval_opinion());
		String sql = loadSQL("updateAudit");
		getNamedParameterJdbcTemplate().update(sql, paramMap);
	}

	@Override
	public void updateInfoWorkStatus(ApplicationEntity args,String method) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("id",args.getInfo_id());
		paramMap.put("app_id",args.getApp_id());
		paramMap.put("org_id",args.getOrg_id());
		paramMap.put("app_name",args.getApp_name());
		paramMap.put("approval_status",args.getApproval_status());
		paramMap.put("working_status",args.getWorking_status());
		paramMap.put("approval_user_id",args.getApproval_user_id());
		String sql ="";
		if("Audit".equals(method)) {
			 sql = loadSQL("updateInfoWorkStatus");
		}else if("disable".equals(method)){
			 sql = loadSQL("updateInfoWorkStatusdisable");
		}
		getNamedParameterJdbcTemplate().update(sql, paramMap);
	}

	@Override
	public PageDataResult<ApplicationEntity> listSysApplications(Map params) {
		PageDataResult<ApplicationEntity> pageData = new PageDataResult<ApplicationEntity>();
		String sql = loadSQL("listApplication", params);
		Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
		pageData.setTotalCount(totalCount);
		pageData.setTotalPage(PageUtils.getTotalPage(totalCount));

		sql = getPaginationString(sql, PageUtils.getStartNum((Integer) params.get("page")), PageUtils.pageSize);
		List<ApplicationEntity> list = getNamedParameterJdbcTemplate().query(sql, params,
				new BeanPropertyRowMapper(ApplicationEntity.class));
		pageData.setList(list);
		return pageData;
	}
	@Override
	public PageDataResult<ApplicationEntity> listHisVersion(Map params) {
		PageDataResult<ApplicationEntity> pageData = new PageDataResult<ApplicationEntity>();
		String sql = loadSQL("listHisVersion", params);
		Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
		pageData.setTotalCount(totalCount);
		pageData.setTotalPage(PageUtils.getTotalPage(totalCount));
		
		sql = getPaginationString(sql, PageUtils.getStartNum((Integer) params.get("page")), PageUtils.pageSize);
		List<ApplicationEntity> list = getNamedParameterJdbcTemplate().query(sql, params,
				new BeanPropertyRowMapper(ApplicationEntity.class));
		pageData.setList(list);
		return pageData;
	}

	public ApplicationEntity listInfoAppById(String Id ) {
		Map params = new HashMap();;
		params.put("info_id",Id);
		String sql = loadSQL("selectInfoAppById",params);
		List<ApplicationEntity> list = getNamedParameterJdbcTemplate().query(sql, params,
				new BeanPropertyRowMapper(ApplicationEntity.class));
		if(list.size()>0){
			return list.get(0);
		}
		else{
			return null;
		}
	}

	public ApplicationEntity queryInfoList(String appId,String orgId ) {
		Map params = new HashMap();
		params.put("org_id",orgId);
		params.put("app_id",appId);
		String sql = loadSQL("queryInfoList",params);
		List<ApplicationEntity> list = getNamedParameterJdbcTemplate().query(sql, params,
				new BeanPropertyRowMapper(ApplicationEntity.class));
		if(list.size()>0){
			return list.get(0);
		}
		else{
			return null;
		}
	}

	@Override
	public PageDataResult<ApplicationEntity> getCustomizeList(Map params) {
		PageDataResult<ApplicationEntity> pageData = new PageDataResult<ApplicationEntity>();
		String sql = loadSQL("getCustomizeList", params);
		Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
		pageData.setTotalCount(totalCount);
		pageData.setTotalPage(PageUtils.getTotalPage(totalCount, (Integer) params.get("pageSize")));
		sql = getPaginationString(sql, PageUtils.getStartNum((Integer) params.get("page"), (Integer) params.get("pageSize")), (Integer) params.get("pageSize"));
		List<ApplicationEntity> list = getNamedParameterJdbcTemplate().query(sql, params,
				new BeanPropertyRowMapper(ApplicationEntity.class));
		pageData.setList(list);
		return pageData;
	}

	@Override
	public PageDataResult<ApplicationEntity> getApplicationsByAreaNo(Map params) {
		PageDataResult<ApplicationEntity> pageData = new PageDataResult<ApplicationEntity>();
		String sql = loadSQL("getApplicationsByAreaNo", params);
		Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
		pageData.setTotalCount(totalCount);
		pageData.setTotalPage(PageUtils.getTotalPage(totalCount, (Integer) params.get("pageSize")));
		sql = getPaginationString(sql, PageUtils.getStartNum((Integer) params.get("page"), (Integer) params.get("pageSize")), (Integer) params.get("pageSize"));
		List<ApplicationEntity> list = getNamedParameterJdbcTemplate().query(sql, params,
				new BeanPropertyRowMapper(ApplicationEntity.class));
		pageData.setList(list);
		return pageData;
	}

	@Override
	public void addCustomize(Map params) {
		params.put("id",UUIDUtils.generateUUID());
		String sql = loadSQL("addCustomize");
		getNamedParameterJdbcTemplate().update(sql, params);
	}

	@Override
	public void updateCustomize(Map params) {
		String sql = loadSQL("updateCustomize");
		getNamedParameterJdbcTemplate().update(sql, params);
	}

	@Override
	public Integer existsCustomizeApp(Map params) {
		String sql = loadSQL("existsCustomizeApp");
		return getNamedParameterJdbcTemplate().queryForInt(sql, params);
	}

	@Override
	public PageDataResult<ApplicationEntity> queryApplications(Pageable page, String sysType, String keyWord, String ywType, String xzType, String blType, String serverType, String areaNo) {
		PageDataResult<ApplicationEntity> pageData = new PageDataResult<ApplicationEntity>();
		Map params = new HashMap();
		params.put("sysType", sysType);
		String sql = "";
		if(areaNo != null && !areaNo.isEmpty()){
			params.put("areaNo", areaNo);
			sql = loadSQL("queryApplicationsWithAreaNo", params);
		}else{
			sql = loadSQL("queryApplications", params);
		}
		if(keyWord != null && !keyWord.isEmpty()){
			sql += " AND (a.app_name LIKE '%" + keyWord + "%' OR i.memo LIKE '%" + keyWord + "%')";
		}
		if(ywType != null && !ywType.isEmpty()){
			params.put("ywType", ywType);
			sql += " AND i.yw_type = :ywType ";
		}
		if(xzType != null && !xzType.isEmpty()){
			params.put("xzType", xzType);
			sql += " AND i.xz_type = :xzType ";
		}
		if(blType != null && !blType.isEmpty()){
			params.put("blType", blType);
			sql += " AND i.bl_type = :blType ";
		}
		if(serverType != null && !serverType.isEmpty()){
			params.put("serverType", serverType);
			sql += " AND i.server_type  = :serverType ";
		}

		Integer totalCount = getNamedParameterJdbcTemplate().queryForInt(getTotalCountString(sql), params);
		pageData.setTotalCount(totalCount);
		pageData.setTotalPage(PageUtils.getTotalPage(totalCount, page.getSize()));
		sql = getPaginationString(sql, PageUtils.getStartNum(page.getPage(), page.getSize()), page.getSize());
		List<ApplicationEntity> list = getNamedParameterJdbcTemplate().query(sql, params,
				new BeanPropertyRowMapper(ApplicationEntity.class));
		pageData.setList(list);
		return pageData;
	}
}
