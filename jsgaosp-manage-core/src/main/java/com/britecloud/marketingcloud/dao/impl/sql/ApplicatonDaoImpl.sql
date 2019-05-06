--------------------------------------------
--insertApplication
INSERT INTO bc_declare_app(id,app_name,org_id,sys_type,status,create_date,create_user_id,access_type)
VALUES(:id,:app_name,:org_id,:sys_type,:status,to_date(:create_date,'yyyy-MM-dd HH24:mi:ss'),:create_user_id,:access_type);
--------------------------------------------
--insertApplicationInfo
INSERT INTO bc_declare_app_info(id, app_id,guide_addr, online_addr,online_qaq_addr,yw_type, xz_type, memo,
status,approval_opinion,create_date,create_user_id, approval_date, approval_user_id, bl_type,
version, working_status, server_type,icon_url,approval_status) 
VALUES(:info_id, :app_id,:guide_addr, :online_addr,:online_qaq_addr,:yw_type, :xz_type, :memo,
:status,:approval_opinion,to_date(:create_date,'yyyy-MM-dd HH24:mi:ss'),:create_user_id, :approval_date, :approval_user_id, :bl_type,
:version, :working_status,:server_type,:icon_url,:approval_status);
--------------------------------------------
--existsArgsKey
SELECT COUNT(0) from bc_declare_app WHERE id=:id;
--------------------------------------------
--existsApplicationInfo
SELECT COUNT(0) from bc_declare_app_info WHERE id=:id;
--------------------------------------------
--updateApplicationInfo
update bc_declare_app_info 
set
   guide_addr = :guide_addr,
   online_addr = :online_addr,
   online_qaq_addr = :online_qaq_addr,
   yw_type = :yw_type,
   xz_type = :xz_type,
   memo = :memo,
   approval_date = sysdate,
   approval_user_id = :approval_user_id,
   bl_type = :bl_type,
   server_type = :server_type,
   icon_url = :icon_url,
   approval_opinion = :approval_opinion,
   approval_status = :approval_status
   where  id = :id
   
--------------------------------------------
--updateApplication
update bc_declare_app set  status = :status  where id=:id;
--------------------------------------------
--listApplication
   select a.id,
       a.app_name,
       GET_ORG_NAME(a.org_id) as org_id,
       GET_CODE_SORT_TEXT('sys_type', a.sys_type) as sys_type,
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
       i.server_type,
       i.icon_url,
       GET_CODE_SORT_TEXT('app_status', i.approval_status) as approval_status
  from bc_declare_app a, bc_declare_app_info i
 where a.id = i.app_id and a.status ='1'
 --<dynamic>
   --<isNotEmpty prepend="AND" property="app_name">
       a。app_name  = :app_name
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
      org_id  = :orgNo
   --</isNotEmpty>
   --</dynamic>
      
--------------------------------------------
--updateInfoStatus
update bc_declare_app_info set 
    status = :version_status,
   approval_date = sysdate,
   approval_user_id = :approval_user_id
   where  app_id = :app_id
   
--------------------------------------------   
--updateAudit
update bc_declare_app_info 
set
   approval_opinion = :approval_opinion,
   approval_status = :approval_status,
   approval_date = sysdate,
   approval_user_id =:approval_user_id
   where id = :id