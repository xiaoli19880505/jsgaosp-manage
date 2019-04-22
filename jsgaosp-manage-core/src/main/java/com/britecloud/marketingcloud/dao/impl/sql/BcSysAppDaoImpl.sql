------------------------------------------
--listSysAppliactions
select t2.sys_name as sysName,
       get_code_text('sys_type', t2.sys_type) as sysType,
       t1.id as id,
       t1.sys_id as sysId,
       t1.app_name as appName,
       t1.guide_addr as guideAddr,
       t1.online_addr as onlineAddr,
       t1.online_qaq_addr as onlineQaqAddr,
       t1.area_no as areaNo,
       t1.yw_type as ywType,
       t1.xz_type as xzType,
       t1.memo as memo,
       t1.status as status,
       t1.approval_opinion as approvalOpinion,
       to_char(t1.create_date, 'yyyy-MM-dd HH24:mi:ss') as createDate,
       t1.create_user_id as createUserId,
       to_char(t1.approval_date, 'yyyy-MM-dd HH24:mi:ss') as approvalDate,
       t1.approval_user_id as approvalUserId,
       t1.bl_type as blType,
       t1.version as version
from bc_declare_app t1, bc_declare_sys t2
where t1.sys_id = t2.id
      AND t1.app_name LIKE '%'||:appName||'%'
      AND t1.status LIKE '%'||:status||'%'
      AND t1.create_date BETWEEN to_date(:startDate,'YYYY/MM/DD') AND to_date(:endDate,'YYYY/MM/DD')
      AND t2.id like '%'||:sysId||'%'
      AND t2.sys_type LIKE '%'||:sysType||'%'

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
create_date=:createDate,
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