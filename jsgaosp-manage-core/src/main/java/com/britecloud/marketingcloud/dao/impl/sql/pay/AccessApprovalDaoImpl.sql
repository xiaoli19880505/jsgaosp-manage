--------------------------------------------
--create
INSERT
    INTO
        JSGAOSP_PAY_ACCESS_APPROVAL(
            ID,
            APPLY_ID,
            APPLY_INFO_ID,
            APPROVAL_TIME,
            APPROVAL_STATUS,
            APPROVAL_OPINION
        )
    VALUES
        (
            :id
           ,:applyId
           ,:applyInfoId
           ,:approvalTime
           ,:approvalStatus
           ,:approvalOpinion
        );
--------------------------------------------
--update
UPDATE
        JSGAOSP_PAY_ACCESS_APPROVAL
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
        JSGAOSP_PAY_ACCESS_APPROVAL
    WHERE
        id = :id;
--------------------------------------------
--get
SELECT
        *
    FROM
        JSGAOSP_PAY_ACCESS_APPROVAL
    WHERE
        id = :id;
--------------------------------------------
--list
SELECT 
		*
    FROM
       	JSGAOSP_PAY_ACCESS_APPROVAL
	ORDER BY CREATE_TIME DESC
