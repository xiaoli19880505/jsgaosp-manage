------------------------------------------
--listSysAppliactions
SELECT * FROM bc_declare_app  where (app_name like '%'||:keyword||'%' 
or sys_id like '%'||:keyword||'%')

--------------------------------------------
--saveSysApplication
INSERT INTO bc_declare_app(id,sys_id,app_name,guide_addr,online_addr,online_qaq_addr,area_no,yw_type,xz_type,memo,status,approval_opinion,create_date,create_user_id,approval_date,approval_user_id,bl_type)
VALUES(:Id,:sysId,:appName,:guideAddr,:onlineAddr,:onlineQaqAddr,:areaNo,:ywType,:xzType,:memo,:status,:approvalOpinion,:createDate,:createUserId,:approvalDate,:approvalUserId,:blType);

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
create_date=:createDate,
create_user_id=:createUserId,
approval_date=:approvalDate,
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