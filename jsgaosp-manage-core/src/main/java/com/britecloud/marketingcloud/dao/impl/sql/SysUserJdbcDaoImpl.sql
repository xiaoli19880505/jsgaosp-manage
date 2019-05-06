--------------------------------------------
--createUser
INSERT
    INTO
        BC_USER(
            USER_ID,
            COMPANY_ID,
            USER_NAME,
            USER_PWD,
            USER_EMAIL,
            USER_OFFICE_PHONE,
            USER_MOBILE,
            USER_QQ,
            RETIRED,
            USER_TYPE,
            CREATE_DATE,
            CREATE_BY,
            UPDATE_DATE,
            UPDATE_BY,
            ORG_NO
        )
    VALUES
        (
            :userId
            ,:companyId
            ,:userName
            ,:userPwd
            ,:userEmail
            ,:userOfficePhone
            ,:userMobile
            ,:userQq
            ,:retired
            ,:userType
            ,:createDate
            ,:createBy
            ,:updateDate
            ,:updateBy
            ,:orgNo
        );
--------------------------------------------
--updateUser
UPDATE
        BC_USER
    SET
        USER_NAME =:userName
        ,USER_EMAIL = :userEmail
        ,USER_OFFICE_PHONE = :userOfficePhone
        ,USER_MOBILE = :userMobile
        ,USER_QQ = :userQq
        ,USER_TYPE =:userType
        ,UPDATE_DATE =:updateDate
        ,UPDATE_BY =:updateBy
        ,AVATAR =:avatar
        ,ORG_NO =:orgNo
    WHERE
        USER_ID = :userId;
--------------------------------------------
--deleteUser
	UPDATE 
        BC_USER
    SET 
    	RETIRED = '0'
    WHERE
        USER_ID = :userId;
--------------------------------------------
--getUser
SELECT
        *
    FROM
        BC_USER
    WHERE
        USER_ID = :userId;
--------------------------------------------
--listUser
SELECT 
		a.*,b.ORG_NAME
    FROM
       	BC_USER a LEFT JOIN BC_ORG b ON
       	a.ORG_NO=b.ORG_NO
    WHERE 
    	1=1
    	and a.RETIRED = '1'
    	and a.USER_TYPE <> 'MAINTAIN'
	--<dynamic>
	  --<isNotNull property="keyword" prepend="AND">
	        a.USER_NAME like '%'||:keyword||'%'
	  --</isNotNull>
	--</dynamic>

		--<dynamic>
	  --<isNotNull property="orgNo" prepend="AND">
	        a.ORG_NO=:orgNo
	  --</isNotNull>
	--</dynamic>
	ORDER BY a.CREATE_DATE DESC
--------------------------------------------
--resetPwd
UPDATE
        BC_USER
    SET
        USER_PWD = :pwd
    WHERE
        USER_ID = :userId;
--------------------------------------------
--getUserByEmail
SELECT
        *
    FROM
        BC_USER
    WHERE
        USER_EMAIL = :email;
--------------------------------------------
--getCountByCompanyId
SELECT
        COUNT(*)
    FROM
        BC_USER
    WHERE
        COMPANY_ID = :companyId;
--------------------------------------------
--checkEmail
SELECT
        COUNT(*)
    FROM
        BC_USER
    WHERE
        USER_EMAIL = :email;
--------------------------------------------
--getUserInfo
SELECT * FROM BC_USER WHERE USER_ID =:userId;