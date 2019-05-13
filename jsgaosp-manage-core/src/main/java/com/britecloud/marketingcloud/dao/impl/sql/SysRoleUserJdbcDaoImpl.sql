--------------------------------------------
--addUserRole
INSERT
    INTO
        BC_USER_ROLE(
            USER_ID,
            ROLE_ID
        )
    VALUES
        (
            :userId,
            :roleId
        );
 --------------------------------------------
--countUserByRoleId
SELECT COUNT(*)
  FROM BC_USER_ROLE a JOIN bc_user b
  ON  a.user_id=b.user_id
  AND a.role_id=:roleId
  AND b.status='1';

--------------------------------------------
--removeUserRole
DELETE
    FROM
        BC_USER_ROLE
    WHERE
        ROLE_ID = :roleId
        AND
        USER_ID = :userId
--------------------------------------------
--removeUserRoleByRoleId
DELETE
    FROM
        BC_USER_ROLE
    WHERE
        ROLE_ID = :roleId
--------------------------------------------
--listDistributedUser
SELECT
		BC_USER.*,
		ROLE_ID
FROM
    BC_USER
LEFT JOIN
		BC_USER_ROLE
ON  BC_USER.USER_ID=BC_USER_ROLE.USER_ID AND ROLE_ID IS NOT NULL
WHERE ROLE_ID = :roleId
AND (BC_USER.USER_TYPE = 'ADMIN' OR BC_USER.USER_TYPE = 'USER' )

--------------------------------------------
--listRoleByUserId
SELECT
	BC_ROLE.*
FROM
	BC_ROLE
JOIN BC_USER_ROLE ON BC_ROLE.ROLE_ID = BC_USER_ROLE.ROLE_ID
WHERE
	USER_ID = :userId AND BC_ROLE.ROLE_TYPE !='MAINTAIN'
--------------------------------------------
--addAdminRole
INSERT INTO BC_USER_ROLE (USER_ID,ROLE_ID) VALUES(:userId,(SELECT ROLE_ID FROM BC_ROLE WHERE COMPANY_ID=:companyId AND ROLE_TYPE = 'ADMIN'));

--------------------------------------------
--listUserByRoleId
SELECT b.*
  FROM BC_USER_ROLE a JOIN bc_user b
  ON  a.user_id=b.user_id
  AND a.role_id=:roleId
  AND b.status='1'
     --<dynamic>
  --<isNotNull property="orgNo" prepend="AND">
       a.ROLE_NO=:orgNo;
  --</isNotNull>
--</dynamic>
  AND b.user_name like '%'||:keyword||'%';


 --------------------------------------------
--getrUserListNotInThisRole
SELECT
  a.*,
  b.ROLE_ID
FROM
  BC_USER a
    LEFT JOIN
  BC_USER_ROLE b
  ON  a.USER_ID=b.USER_ID AND b.ROLE_ID = :roleId
WHERE
1=1
 --<dynamic>
  --<isNotNull property="orgNo" prepend="AND">
       a.ROLE_NO=:orgNo;
  --</isNotNull>
--</dynamic>

and b.ROLE_ID IS NULL and a.status='1';
