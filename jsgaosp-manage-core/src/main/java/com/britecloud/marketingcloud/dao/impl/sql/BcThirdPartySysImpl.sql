------------------------------------------
--listThirdPartySys
SELECT s.*,
       GET_CODE_TEXT('sys_type', s.sys_type) as sys_type_text,
       GET_CODE_TEXT('sys_status', s.status) as status_type_text,
       GET_CODE_TEXT('access_type', s.access_type) as access_type_text
  FROM BC_DECLARE_SYS s
 where s.sys_name like '%'||:sysName||'%'

 --<dynamic>
  --<isNotNull property="createUserId" prepend="AND">
         create_user_id=:createUserId
  --</isNotNull>
--</dynamic>

--<dynamic>
  --<isNotNull property="status" prepend="AND">
         STATUS =:status
  --</isNotNull>
--</dynamic>


--------------------------------------------
--saveThirdPartySys
INSERT INTO bc_declare_sys(id,sys_name,area_no,sys_type,sys_url,memo,qr_code,status,approval_opinion,create_date,create_user_id,access_type)
VALUES(:id,:sysName,:areaNo,:sysType,:sysUrl,:memo,:qrCode,:status,:approvalOpinion,sysdate,:createUserId,:accessType);

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
approval_date=:approvalDate,
approval_user_id=:approvalUserId,
access_type=:accessType
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

--------------------------------------------
--approveSysApplicant

UPDATE bc_declare_sys SET
status=:status,
approval_opinion=:approvalOpinion,
approval_user_id=:approvalUserId,
approval_date=sysdate
WHERE id=:id;

