--------------------------------------------
--createAccessKey
INSERT INTO BC_ACCESS_KEY
(API_KEY, COMPANY_ID, PUBLIC_KEY, PRIVATE_KEY, NAME, DESCRIPTION, CREATE_DATE, CREATE_BY) 
VALUES (:apiKey, :companyId, :publicKey, :privateKey, :name, :description, :createDate, :createBy);
	
--------------------------------------------
--findByApiKey
SELECT * FROM BC_ACCESS_KEY where API_KEY=:apiKey and COMPANY_ID=:companyId;
--------------------------------------------
--findByCompanyId
SELECT * FROM BC_ACCESS_KEY where COMPANY_ID=:companyId;

--------------------------------------------
--deleteAccessKey
DELETE FROM BC_ACCESS_KEY where API_KEY in #apiKeys# and COMPANY_ID=:companyId;

--------------------------------------------
--findAllAccessKey
SELECT * FROM BC_ACCESS_KEY
WHERE COMPANY_ID=:companyId 
--<dynamic>
    --<isNotNull property="query">
        and ( NAME like #query# or DESCRIPTION like #query#) 
    --</isNotNull>
    --<isNull property="query">
        and ( NAME IS NOT NULL ) 
    --</isNull>
--</dynamic>

--------------------------------------------
--updateAccesskey
UPDATE 
	BC_ACCESS_KEY 
SET
	NAME =:name,
	DESCRIPTION =:description
WHERE  API_KEY=:apiKey and COMPANY_ID=:companyId;
       
 
