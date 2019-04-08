--------------------------------------------
--createAccessKey
INSERT INTO BC_ACCESS_KEY
(API_KEY, COMPANY_ID, PUBLIC_KEY, PRIVATE_KEY, NAME, DESCRIPTION, CREATE_DATE, CREATE_BY) 
VALUES (:apiKey, :companyId, :publicKey, :privateKey, :name, :description, :createDate, :createBy);
	
--------------------------------------------
--findByApiKey
SELECT * FROM BC_ACCESS_KEY where API_KEY=:apiKey;

--------------------------------------------
--deleteAccessKey
DELETE FROM BC_ACCESS_KEY where API_KEY=:apiKey;