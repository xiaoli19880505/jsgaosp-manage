--------------------------------------------
--listCodeSort
SELECT * FROM BC_CODE_SORT where (CODE_SORT_TEXT like '%'||:keyword||'%' or CODE_SORT_KEY like '%'||:keyword||'%')
 --<dynamic>
  --<isNotNull property="pCodeSortId" prepend="AND">
        P_CODE_SORT_ID =:pCodeSortId
  --</isNotNull>
--</dynamic>
AND  HAS_CHILD='1'
AND  STATUS='1'
AND  (ORG_ID IS NULL
 --<dynamic>
  --<isNotNull property="orgId" prepend="OR">
        ORG_ID =:orgId
  --</isNotNull>
--</dynamic>
)


--------------------------------------------
--listCodeSortDetailBypCodeSortId
SELECT * FROM BC_CODE_SORT where (CODE_SORT_TEXT like '%'||:keyword||'%' or CODE_SORT_KEY like '%'||:keyword||'%')

--  --<dynamic>
--   --<isNotNull property="pCodeSortId" prepend="AND">
--         P_CODE_SORT_ID =:pCodeSortId
--   --</isNotNull>
-- --</dynamic>
and  P_CODE_SORT_ID =:pCodeSortId

AND  HAS_CHILD='0'
AND  STATUS='1'
--------------------------------------------
--saveCodeSort
INSERT INTO BC_CODE_SORT(CODE_SORT_ID,CODE_SORT_KEY,CODE_SORT_TEXT,STATUS,ORG_ID,P_CODE_SORT_ID,HAS_CHILD)
VALUES(:codeSortId,:codeSortKey,:codeSortText,:status,:orgId,:pCodeSortId,:hasChild);

--------------------------------------------
--updateCodeSort
update BC_CODE_SORT set CODE_SORT_KEY=:codeSortKey,CODE_SORT_TEXT=:codeSortText where CODE_SORT_ID=:codeSortId;

--------------------------------------------
--deleteCodeSort
update BC_CODE_SORT set STATUS='0' where CODE_SORT_ID=:codeSortId;

--------------------------------------------
--listCode
SELECT * from BC_CODE where CODE_SORT_ID=:codeSortId AND STATUS='1';


--------------------------------------------
--getCodeSortByPId
SELECT * from BC_CODE_SORT where P_CODE_SORT_ID =:codeSortId AND STATUS='1' AND HAS_CHILD='0'

--------------------------------------------
--getCodeSortDetailByKey
SELECT * from BC_CODE_SORT where
 P_CODE_SORT_ID=( SELECT CODE_SORT_ID FROM BC_CODE_SORT WHERE  CODE_SORT_KEY=:codeSortKey)  AND STATUS='1' AND HAS_CHILD='0'
