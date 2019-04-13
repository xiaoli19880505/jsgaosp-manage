------------------------------------------
--listSysAppliactions
SELECT * FROM bc_declare_app

--------------------------------------------
--saveSysApplication
INSERT INTO bc_declare_app(id,sys_id,app_name,guide_addr,online_addr,online_qaq_addr,area_no,yw_type,xz_type,memo,status,approval_opinion,create_date,create_user_id,approval_date,approval_user_id,bl_type)
VALUES(:Id,:sysId,:appName,:guideAddr,:onlineAddr,:onlineQaqAddr,:areaNo,:ywType,:xzType,:memo,:status,:approvalOpinion,:createDate,:createUserId,:approvalDate,:approvalUserId,:blType);

--------------------------------------------
--updateSysArgs
update BC_SYS_ARGS set ARGS_KEY=:argsKey,SYSTEM=:system,ARGS_VALUE=:argsValue,status=:status,MEMO=:memo where id=:id;

--------------------------------------------
--deleteSysArgs
delete from BC_SYS_ARGS where id=:id;

--------------------------------------------
--getSysArgsById
SELECT * from BC_SYS_ARGS WHERE  ID = :id;

--------------------------------------------
--existsArgsKey
SELECT COUNT(0) from bc_declare_app WHERE app_name=:appName;