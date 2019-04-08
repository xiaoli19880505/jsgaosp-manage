--------------------------------------------
--getCompanyList
	SELECT * FROM BC_COMPANY ;
--------------------------------------------
--getCompanyInfo
	SELECT * FROM BC_COMPANY WHERE COMPANY_ID = :companyId;
--------------------------------------------
--addCompany
	INSERT INTO BC_COMPANY(COMPANY_ID,NAME,DESCRIPTION,CREATE_DATE,CREATE_BY,UPDATE_DATE,UPDATE_BY) VALUES(:companyId,:name,:description,:createDate,:createBy,:updateDate,:updateBy);
--------------------------------------------
--deleteCompany
	DELETE FROM BC_COMPANY WHERE COMPANY_ID = :companyId;
--------------------------------------------
--saveCompany
	UPDATE BC_COMPANY SET 
