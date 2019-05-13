--------------------------------------------
--create
INSERT
    INTO
        JSGAOSP_PAY_ACCESS_CLIENT_INFO(
            ID,
            ACCESS_ID,
            AREA,
            ORG_CODE,
            ACCESS_NAME,
            CONTACTS_NAME,
            CONTACTS_PHONE,
            FILE_ID
        )
    VALUES
        (
            :id
            ,:accessId
            ,:area
            ,:orgCode
            ,:accessName
            ,:contactsName
            ,:contactsPhone
            ,:fileId
        );
--------------------------------------------
--update
UPDATE
        JSGAOSP_PAY_ACCESS_CLIENT_INFO
    SET
        APP_ID =:appId
        ,BUSINESS_NAME = :businessName
        ,CLIENT_PUBLIC_KEY = :clientPublicKey
        ,CLIENT_PRIVATE_KEY = :clientPrivateKey
        ,SERVER_PUBLIC_KEY = :serverPublicKey
        ,SERVER_PRIVATE_KEY =:serverPrivateKey
        ,DESCRIPTION =:description
    WHERE
        id = :id;
--------------------------------------------
--delete
	delete
	  FROM
        JSGAOSP_PAY_ACCESS_CLIENT_INFO
    WHERE
        id = :id;
--------------------------------------------
--get
SELECT
        *
    FROM
        JSGAOSP_PAY_ACCESS_CLIENT_INFO
    WHERE
        id = :id;
--------------------------------------------
--list
SELECT 
		*
    FROM
       	JSGAOSP_PAY_ACCESS_CLIENT_INFO
	ORDER BY CREATE_TIME DESC
