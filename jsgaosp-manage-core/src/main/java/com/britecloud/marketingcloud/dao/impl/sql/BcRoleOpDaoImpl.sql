--------------------------------------------
--setRoleOp
insert into BC_ROLE_OP(
id,
role_id,
perm,
op_code,
op_name
)
values (
:id,
:roleId,
:perm,
:opCode,
:opName
);

--------------------------------------------
--cleanRoleOpByRoleId
delete from BC_ROLE_OP  where role_id=:roleId;



--------------------------------------------
--listDepartByOrgId
SELECT * FROM BC_ROLE_OP where role_id=:roleId;

