------------------------------------------
--listSysAppliactions
select t2.sys_name,
       get_code_text('sys_type', t2.sys_type) as sys_type,
       t1.id as id,
       t1.sys_id as sys_id,
       t1.app_name as app_name,
       t1.guide_addr as guide_addr,
       t1.online_addr as online_addr,
       t1.online_qaq_addr as online_qaq_addr,
       t1.area_no as area_no,
       t1.yw_type as yw_type,
       t1.xz_type as xz_type,
       t1.memo as memo,
       t1.status as status,
       t1.approval_opinion as approval_opinion,
       to_char(t1.create_date, 'yyyy-MM-dd HH24:mi:ss') as create_date,
       t1.create_user_id as create_user_id,
       to_char(t1.approval_date, 'yyyy-MM-dd HH24:mi:ss') as approval_date,
       t1.approval_user_id as approval_user_id,
       t1.bl_type as bl_type
  from bc_declare_app t1, bc_declare_sys t2
where t1.sys_id = t2.id and t1.create_user_id =:userId;

--------------------------------------------
--saveSysApplication
INSERT INTO bc_declare_app(id,sys_id,app_name,guide_addr,online_addr,online_qaq_addr,area_no,yw_type,xz_type,memo,status,approval_opinion,create_date,create_user_id,approval_date,approval_user_id,bl_type)
VALUES(:Id,:sysId,:appName,:guideAddr,:onlineAddr,:onlineQaqAddr,:areaNo,:ywType,:xzType,:memo,:status,:approvalOpinion,to_date(:createDate,'yyyy-MM-dd HH24:mi:ss'),:createUserId,to_date(:approvalDate,'yyyy-MM-dd HH24:mi:ss'),:approvalUserId,:blType);

--------------------------------------------
--updateSysApplication
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
create_user_id=:createUserId,
approval_date=to_date(:approvalDate,'yyyy-MM-dd HH24:mi:ss'),
approval_user_id=:approvalUserId,
bl_type=:blType
where id=:Id;

--------------------------------------------
--deleteSysApplication
delete from bc_declare_app where id=:Id;

--------------------------------------------
--getSysApplicationById
SELECT * from bc_declare_app WHERE  id=:Id;

--------------------------------------------
--existsArgsKey
SELECT COUNT(0) from bc_declare_app WHERE app_name=:appName;