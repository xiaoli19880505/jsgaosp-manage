------------------------------------------
--listSysVersions
select s.sys_name,
       get_code_text('sys_type', s.sys_type) as sys_type,
       t.id as id,
       t.sys_id as sys_id,
       t.app_name as app_name,
       t.guide_addr as guide_addr,
       t.online_addr as online_addr,
       t.online_qaq_addr as online_qaq_addr,
       t.area_no as area_no,
       t.yw_type as yw_type,
       t.xz_type as xz_type,
       t.memo as memo,
       t.status as status,
       t.approval_opinion as approval_opinion,
       to_char(t.create_date, 'yyyy-MM-dd HH24:mi:ss') as create_date,
       t.create_user_id as create_user_id,
       to_char(t.approval_date, 'yyyy-MM-dd HH24:mi:ss') as approval_date,
       t.approval_user_id as approval_user_id,
       t.bl_type as bl_type,
       t.version as version
  from bc_declare_app_prd t, bc_declare_sys s
 where t.sys_id = s.id
 --<dynamic>
   --<isNotEmpty prepend="AND" property="appName">
       t。app_name  = :appName
   --</isNotEmpty>
   --<isNotEmpty prepend="AND" property="sysName">
       s.sys_name  = :sysName
   --</isNotEmpty>
   --<isNotEmpty prepend="AND" property="sysType">
       s.sys_type  = :sysType
   --</isNotEmpty>
   --<isNotEmpty prepend="AND" property="status">
      t.status  = :status
   --</isNotEmpty>
   --<isNotEmpty prepend="AND" property="createDate">
       to_char(t.create_date, 'yyyy-MM-dd HH24:mi:ss')  >= :createDate
   --</isNotEmpty>
   --<isNotEmpty prepend="AND" property="endDate">
       to_char(t.create_date, 'yyyy-MM-dd HH24:mi:ss')  >= :endDate
   --</isNotEmpty>
   --<isNotEmpty prepend="AND" property="userId">
      t.approval_user_id  = :userId
   --</isNotEmpty>
   --</dynamic>
   
--------------------------------------------
--updateVersion
update bc_declare_app set sys_id=:sysId,
app_name=:appName,
guide_addr=:guideAddr,
online_addr=:onlineAddr,
online_qaq_addr=:onlineQaqAddr,
area_no=:areaNo,
yw_type=:ywType,
xz_type=:xzType,
memo=:memo,
status=:status,
approval_opinion=:approvalOpinion,
create_date=to_date(:createDate,'yyyy-MM-dd HH24:mi:ss'),
create_user_id=:createUserId,
approval_date=to_date(:approvalDate,'yyyy-MM-dd HH24:mi:ss'),
approval_user_id=:approvalUserId,
bl_type=:blType,
version = :version
where id=:Id

--------------------------------------------
--getSysVersionById
SELECT * from bc_declare_app WHERE  id=:Id;

--------------------------------------------
--existsVersion
SELECT COUNT(0) from bc_declare_app WHERE app_name=:appName;

--------------------------------------------
--listHisSysVersions
select s.sys_name,
       get_code_text('sys_type', s.sys_type) as sys_type,
       t.id as id,
       t.sys_id as sys_id,
       t.app_name as app_name,
       t.guide_addr as guide_addr,
       t.online_addr as online_addr,
       t.online_qaq_addr as online_qaq_addr,
       t.area_no as area_no,
       t.yw_type as yw_type,
       t.xz_type as xz_type,
       t.memo as memo,
       t.status as status,
       t.approval_opinion as approval_opinion,
       to_char(t.create_date, 'yyyy-MM-dd HH24:mi:ss') as create_date,
       t.create_user_id as create_user_id,
       to_char(t.approval_date, 'yyyy-MM-dd HH24:mi:ss') as approval_date,
       t.approval_user_id as approval_user_id,
       t.bl_type as bl_type,
       t.version as version
  from bc_declare_app_prd t, bc_declare_sys s
 where t.sys_id = s.id
 --<dynamic>
   --<isNotEmpty prepend="AND" property="appName">
       t.app_name  = :appName
   --</isNotEmpty>
   --<isNotEmpty prepend="AND" property="sysName">
       s.sys_name  = :sysName
   --</isNotEmpty>
   --<isNotEmpty prepend="AND" property="sysType">
       s.sys_type  = :sysType
   --</isNotEmpty>
   --<isNotEmpty prepend="AND" property="status">
      t.status  = :status
   --</isNotEmpty>
   --<isNotEmpty prepend="AND" property="createDate">
       to_char(t.create_date, 'yyyy-MM-dd HH24:mi:ss')  >= :createDate
   --</isNotEmpty>
   --<isNotEmpty prepend="AND" property="endDate">
       to_char(t.create_date, 'yyyy-MM-dd HH24:mi:ss')  >= :endDate
   --</isNotEmpty>
   --<isNotEmpty prepend="AND" property="userId">
      t.approval_user_id  = :userId
   --</isNotEmpty>
   --<isNotEmpty prepend="AND" property="sysId">
      t.sys_id  = :sysId
   --</isNotEmpty>
   --<isNotEmpty prepend="AND" property="areaNo">
      t.area_no  = :areaNo
   --</isNotEmpty>
   --</dynamic>

--------------------------------------------
--insertVersion
INSERT INTO bc_declare_app_prd(id,sys_id,app_name,guide_addr,online_addr,online_qaq_addr,area_no,yw_type,xz_type,memo,status,approval_opinion,create_date,create_user_id,approval_date,approval_user_id,bl_type,version)
VALUES(:Id,:sysId,:appName,:guideAddr,:onlineAddr,:onlineQaqAddr,:areaNo,:ywType,:xzType,:memo,:status,:approvalOpinion,to_date(:createDate,'yyyy-MM-dd HH24:mi:ss'),:createUserId,to_date(:approvalDate,'yyyy-MM-dd HH24:mi:ss'),:approvalUserId,:blType,:version);

--------------------------------------------
--deleteVersion
delete from bc_declare_app_prd where id=:Id;
