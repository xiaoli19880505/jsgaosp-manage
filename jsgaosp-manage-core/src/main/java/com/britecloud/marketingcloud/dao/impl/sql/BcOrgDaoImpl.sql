--------------------------------------------
--listOrg
SELECT * FROM bc_org where P_ORG_NO=:pOrgNo
AND ORG_TYPE='01'
AND STATUS='1';


--------------------------------------------
--listOrgWithNoType
SELECT * FROM bc_org where P_ORG_NO=:pOrgNo
AND STATUS='1';

--------------------------------------------
--listDepartByOrgId
SELECT * FROM bc_org where P_ORG_NO=:pOrgNo
AND ORG_TYPE='02'
AND STATUS='1';

--------------------------------------------
--getOrgAreaNameList
SELECT c.* FROM bc_org a,bc_org b, bc_org c where a.org_no = b.p_org_no and b.org_no = c.p_org_no
and a.org_no = '111' and c.org_type = '01' and b.status = '1' and c.status = '1'
union
SELECT * FROM bc_org a where a.org_no = '111'
union
SELECT b.* FROM bc_org a,bc_org b where a.org_no = b.p_org_no
and a.org_no = '111' and b.org_type = '01' and b.status = '1';

--------------------------------------------
--saveOrg
INSERT INTO bc_org(org_no,org_name,p_org_no,status,org_type,memo,charge_person,office_tel,mobile_tel,address)
VALUES(:orgNo,:orgName,:pOrgNo,:status,:orgType,:memo,:chargePerson,:officeTel,:mobileTel,:address);

--------------------------------------------
--updateOrg
update bc_org set
org_no=:orgNo,
org_name=:orgName,
p_org_no=:pOrgNo,
org_type=:orgType,
memo=:memo,
status=:status,
charge_person=:chargePerson,
office_tel=:officeTel,
mobile_tel=:mobileTel,
address=:address
where org_no=:orgNo

--------------------------------------------
--deleteOrg
update bc_org set
status='0'
where org_no=:orgNo

--------------------------------------------
--getOrgById
SELECT * from bc_org WHERE  org_no = :orgNo
and status='1';

--------------------------------------------
--existsOrgName
SELECT COUNT(1) from bc_org WHERE org_name=:orgName
and status='1';

--------------------------------------------
--existsChildrenOrgName
SELECT COUNT(1) from bc_org WHERE org_name=:orgName
and P_ORG_NO=:pOrgNo
and status='1';

--------------------------------------------
--getOrgByOrgNo
SELECT * from bc_org WHERE  org_no = :orgNo
AND STATUS='1';

