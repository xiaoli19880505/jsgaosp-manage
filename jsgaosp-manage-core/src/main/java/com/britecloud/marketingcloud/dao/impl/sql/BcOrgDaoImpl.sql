--------------------------------------------
--listOrg
SELECT * FROM bc_org where P_ORG_NO=:pOrgNo
AND ORG_TYPE='01'
AND STATUS='1';

--------------------------------------------
--listDepartByOrgId
SELECT * FROM bc_org where P_ORG_NO=:pOrgNo
AND ORG_TYPE='02'
AND STATUS='1';

--------------------------------------------
--getOrgAreaNameList
SELECT * FROM bc_org where  ORG_TYPE='01'
AND STATUS='1';

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
SELECT * from bc_org WHERE  org_no = :orgNo;

--------------------------------------------
--existsOrgName
SELECT COUNT(1) from bc_org WHERE org_name=:orgName;

--------------------------------------------
--getOrgByOrgNo
SELECT * from bc_org WHERE  org_no = :orgNo
AND STATUS='1';

