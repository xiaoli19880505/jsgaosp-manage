--------------------------------------------
--createRole
INSERT
    INTO
        BC_ROLE(
            ROLE_ID,
            COMPANY_ID,
            NAME,
            DESCRIPTION,
            ROLE_TYPE,
            PERM
        )
    VALUES
        (
            :roleId,
            :companyId,
            :name,
            :description,
            :roleType,
            :perm
        );
--------------------------------------------
--updateRole
UPDATE
        BC_ROLE
    SET
        NAME = :name,
        DESCRIPTION = :description,
        PERM = :perm
    WHERE
        ROLE_ID = :roleId;
--------------------------------------------
--deleteRole
DELETE
    FROM
        BC_ROLE
    WHERE
        ROLE_ID = :roleId;
--------------------------------------------
--queryRole
SELECT 
	*
	FROM
    	BC_ROLE
	WHERE
		(COMPANY_ID = :companyId OR ISNULL(COMPANY_ID)=1 OR COMPANY_ID = '')
	AND
		ROLE_TYPE !='MAINTAIN'; 
--------------------------------------------
--getRole
SELECT
        BC_ROLE.*
    FROM
        BC_ROLE
    WHERE
        ROLE_ID = :roleId;
--------------------------------------------
--getRoleByUserId
SELECT
        r.*
    FROM
        BC_ROLE r,BC_USER_ROLE ur
    WHERE
        r.ROLE_ID=ur.ROLE_ID and ur.USER_ID=:userId;
-------------------------------------------
--initRole
INSERT INTO BC_ROLE (ROLE_ID, COMPANY_ID, NAME, DESCRIPTION, PERM, ROLE_TYPE) VALUES
(:roleId,:companyId, '公司管理员', '公司管理员', '0-0,1-0,1-1,2-0,2-1,2-2,3-0,3-1,4-0,4-1,5-0,5-1,5-2,5-3,5-4,5-5,6-0,6-1,6-2,7-0,7-1,7-2', 'ADMIN'),
(:roleId1, :companyId, '普通用户', '普通用户', '0-0,1-0,1-2,2-0,2-1,2-2,3-0,3-1,5-0,5-1,5-2,5-3,5-4,5-5,6-0,6-1,6-2', 'USER');        