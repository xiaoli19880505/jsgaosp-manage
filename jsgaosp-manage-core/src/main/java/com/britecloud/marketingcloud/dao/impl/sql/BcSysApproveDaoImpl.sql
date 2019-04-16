------------------------------------------
--listSysApproves
SELECT id as id,
       sys_id as sys_id,
       app_name as app_name,
       guide_addr as guide_addr,
       online_addr as online_addr,
       online_qaq_addr as online_qaq_addr,
       area_no as area_no,
       yw_type as yw_type,
       xz_type as xz_type,
       memo as memo,
       status as status,
       approval_opinion as approval_opinion,
       to_char(create_date,'yyyy-MM-dd HH24:mi:ss') as create_date,
       create_user_id as create_user_id,
       to_char(approval_date,'yyyy-MM-dd HH24:mi:ss') as approval_date,
       approval_user_id as approval_user_id,
       bl_type as bl_type
  FROM bc_declare_app
where (app_name like '%'||:keyword||'%' 
or sys_id like '%'||:keyword||'%') and status ='02' order by create_date asc

--------------------------------------------
--saveSysApplication
INSERT INTO bc_declare_app(id,sys_id,app_name,guide_addr,online_addr,online_qaq_addr,area_no,yw_type,xz_type,memo,status,approval_opinion,create_date,create_user_id,approval_date,approval_user_id,bl_type)
VALUES(:Id,:sysId,:appName,:guideAddr,:onlineAddr,:onlineQaqAddr,:areaNo,:ywType,:xzType,:memo,:status,:approvalOpinion,to_date(:createDate,'yyyy-MM-dd HH24:mi:ss'),:createUserId,to_date(:approvalDate,'yyyy-MM-dd HH24:mi:ss'),:approvalUserId,:blType);

--------------------------------------------
--updateApprove
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