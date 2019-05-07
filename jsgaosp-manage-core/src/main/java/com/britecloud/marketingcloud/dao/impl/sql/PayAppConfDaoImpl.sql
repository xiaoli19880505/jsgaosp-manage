--------------------------------------------
--createPayConf
INSERT
    INTO
        JSGAOSP_PAY_BUSINESS_INFO(
            ID,
            APP_ID,
            BUSINESS_NAME,
            CLIENT_PUBLIC_KEY,
            CLIENT_PRIVATE_KEY,
            SERVER_PUBLIC_KEY,
            SERVER_PRIVATE_KEY,
            DESCRIPTION,
            IS_VALID
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
            ,:valid
        );
        --------------------------------------------
--updatePayAppConf
UPDATE
        JSGAOSP_PAY_BUSINESS_INFO
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
--deletePayAppConf
	delete
	  FROM
        JSGAOSP_PAY_BUSINESS_INFO
    WHERE
        id = :id;
--------------------------------------------
--getPayAppConf
SELECT
        *
    FROM
        JSGAOSP_PAY_BUSINESS_INFO
    WHERE
        id = :id;
--------------------------------------------
--listPayConf
SELECT 
		*
    FROM
       	JSGAOSP_PAY_BUSINESS_INFO
	ORDER BY CREATE_TIME DESC
