--------------------------------------------
--getCountByCompanyId
SELECT COUNT(*) FROM BC_PARA WHERE COMPANY_ID = :companyId AND CODE = :code;
--------------------------------------------
--updatePara
UPDATE BC_PARA SET VALUE = :value WHERE COMPANY_ID = :companyId AND CODE = :code;
--------------------------------------------
--insetPara
INSERT INTO BC_PARA (COMPANY_ID,CODE,VALUE) VALUES(:companyId,:code,:value);