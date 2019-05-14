------------------------------------------
--listSysApproves
 select a.id,
       a.app_name,
       GET_ORG_NAME(a.org_id) as org_name,
       a.org_id,
       a.sys_type,
       GET_CODE_SORT_TEXT('sys_type', a.sys_type) as sys_type_name,
       a.status,
       a.create_date,
       a.create_user_id,
       a.access_type,
       i.id as info_id,
       i.app_id as app_id,
       i.guide_addr,
       i.online_addr,
       i.online_qaq_addr,
       i.yw_type,
       i.xz_type,
       i.memo,
       i.status as version_status,
       i.approval_opinion,
       i.create_date as ver_create_date,
       i.create_user_id as ver_create_user_id,
       i.approval_date,
       i.approval_user_id,
       i.bl_type,
       i.version,
       i.working_status,
       GET_CODE_SORT_TEXT('working_status', i.working_status) as working_status_name,
       i.server_type,
       i.icon_url,
       i.approval_status,
       i.conditions,
       i.legal_basis,
       b.qr_code_url,
       b.qr_code_img_url,
       b.id as code_Id,
       GET_CODE_SORT_TEXT('app_status', i.approval_status) as approval_status_name
  from bc_declare_app a, bc_declare_app_info i,bc_qrcode b
 where a.id = i.app_id and a.status ='1' and b.app_id(+) = a.id and  i.approval_status = '00'
 --<dynamic>
   --<isNotEmpty prepend="AND" property="app_name">
       a.app_name  = :app_name
   --</isNotEmpty>
   --<isNotEmpty prepend="AND" property="sys_type">
       a.sys_type  = :sys_type
   --</isNotEmpty>
   --<isNotEmpty prepend="AND" property="status">
      t.status  = :status
   --</isNotEmpty>
   --<isNotEmpty prepend="AND" property="create_date">
       to_char(i.create_date, 'yyyy-MM-dd HH24:mi:ss')  >= :createDate
   --</isNotEmpty>
   --<isNotEmpty prepend="AND" property="orgNo">
     a.org_id like '%'||:orgNo||'%' 
   --</isNotEmpty>
   --</dynamic>
      order by i.create_date desc


--------------------------------------------
--updateApprove
update bc_declare_app_info 
set
   approval_opinion = :approval_opinion,
   approval_status = :approval_status,
   approval_date = sysdate,
   working_status = :working_status,
   approval_user_id =:approval_user_id
   where id = :id



--------------------------------------------
--getSysApplicationById
SELECT * from bc_declare_app WHERE  id=:Id;

--------------------------------------------
--existsArgsKey
SELECT COUNT(0) from bc_declare_app WHERE app_name=:appName;