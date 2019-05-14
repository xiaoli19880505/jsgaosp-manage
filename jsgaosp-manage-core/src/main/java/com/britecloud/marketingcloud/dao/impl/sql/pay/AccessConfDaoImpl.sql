--------------------------------------------
--create
INSERT
    INTO
        JSGAOSP_PAY_ACCESS(
            ID,
            APP_ID,
            BUSINESS_NAME,
            CLIENT_PUBLIC_KEY,
            CLIENT_PRIVATE_KEY,
            SERVER_PUBLIC_KEY,
            SERVER_PRIVATE_KEY,
            DESCRIPTION,
            APPLICANT_ID,
            PAY_TYPE
        )
    VALUES
        (
            :id
            ,:appId
            ,:businessName
            ,:clientPublicKey
            ,:clientPrivateKey
            ,:serverPublicKey
            ,:serverPrivateKey
            ,:description
            ,:applicantId
            ,:payType
        );
--------------------------------------------
--update
UPDATE
        JSGAOSP_PAY_ACCESS
    SET

        STATUS = :status
        ,SERVER_PUBLIC_KEY = :serverPublicKey
        ,SERVER_PRIVATE_KEY =:serverPrivateKey
    WHERE
        id = :id;
--------------------------------------------
--delete
	delete
	  FROM
        JSGAOSP_PAY_ACCESS
    WHERE
        id = :id;
--------------------------------------------
--get
SELECT
        *
    FROM
        JSGAOSP_PAY_ACCESS
    WHERE
        id = :id;
--------------------------------------------
--list
SELECT 
		a.*,b.ORG_CODE,b.AREA,b.ACCESS_NAME,b.id as ACCESS_ID
    FROM
      (	select * FROM JSGAOSP_PAY_ACCESS
        --<dynamic>
        --<isNotNull property="businessName" prepend="WHERE">
            BUSINESS_NAME like '%'||:businessName||'%'
        --</isNotNull>
        --</dynamic>
       ) a
    LEFT JOIN JSGAOSP_PAY_ACCESS_CLIENT_INFO b
    ON
        a.id = b.ACCESS_ID
	ORDER BY a.CREATE_TIME DESC
