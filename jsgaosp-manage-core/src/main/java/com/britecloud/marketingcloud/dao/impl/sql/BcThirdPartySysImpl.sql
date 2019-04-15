------------------------------------------
--listThirdPartySys
SELECT * FROM BC_DECLARE_SYS  where sys_name like '%'||:sysName||'%' and STATUS =:status

--------------------------------------------
--saveThirdPartySys
INSERT INTO bc_declare_sys(id,sys_name,area_no,sys_type,sys_url,memo,qr_code,status,approval_opinion,create_date,create_user_id,approval_date,approval_user_id)
VALUES(:id,:sysName,:areaNo,:sysType,:sysUrl,:memo,:qrCode,:status,:approvalOpinion,:createDate,:createUserId,:approvalDate,:approvalUserId);

--------------------------------------------
--updateThirdPartySys
update bc_declare_sys set
sys_name=:sysName,
area_no=:areaNo,
sys_type=:sysType,
sys_url=:sysUrl,
memo=:memo,
qr_code=:qrCode,
status=:status,
approval_opinion=:approvalOpinion,
create_date=:createDate,
create_user_id=:createUserId,
approval_date=:approvalDate,
approval_user_id=:approvalUserId,
where id=:id;

--------------------------------------------
--deleteThirdPartySys
delete from bc_declare_sys where id=:id;

--------------------------------------------
--getThirdPartySysById
SELECT * from bc_declare_sys WHERE  id=:id;

--------------------------------------------
--existsArgsKey
SELECT COUNT(0) from bc_declare_sys WHERE sys_name=:sysName;