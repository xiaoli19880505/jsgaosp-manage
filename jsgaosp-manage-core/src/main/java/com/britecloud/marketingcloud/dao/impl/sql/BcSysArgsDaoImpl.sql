--------------------------------------------
--listSysArgs
SELECT * FROM BC_SYS_ARGS

--------------------------------------------
--saveSysArgs
INSERT INTO BC_SYS_ARGS(id,SYSTEM,ARGS_KEY,ARGS_VALUE,status,MEMO)
VALUES(:id,:system,:argsKey,:argsValue,:status,:memo);

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
SELECT COUNT(1) from BC_SYS_ARGS WHERE ARGS_KEY=:argsKey;


