--------------------------------------------
--listCodeSort
SELECT t.*,c.ORG_NAME FROM BC_CODE_SORT t LEFT JOIN Bc_Org c ON t.org_id =c.org_no where (t.CODE_SORT_TEXT like '%'||:keyword||'%' or t.CODE_SORT_KEY like '%'||:keyword||'%')
AND  t.HAS_CHILD='1'
 --<dynamic>
  --<isNotNull property="orgId" prepend="AND">
        t.ORG_ID =:orgId
  --</isNotNull>
--</dynamic>

AND  t.STATUS='1';



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
INSERT INTO BC_CODE_SORT(CODE_SORT_ID,CODE_SORT_KEY,
CODE_SORT_TEXT,STATUS,ORG_ID,P_CODE_SORT_ID,
HAS_CHILD,CREATE_TIME,IS_PUBLIC)
VALUES(:codeSortId,:codeSortKey,:codeSortText,
:status,:orgId,:pCodeSortId,:hasChild,
to_date(:createTime,'yyyy-MM-dd HH24:mi:ss'),:isPublic);

--------------------------------------------
--updateCodeSort
update BC_CODE_SORT set
CODE_SORT_KEY=:codeSortKey,
CODE_SORT_TEXT=:codeSortText,
IS_PUBLIC=:isPublic,
ORG_ID=:orgId,
UPDATE_TIME=to_date(:updateTime,'yyyy-MM-dd HH24:mi:ss')
where CODE_SORT_ID=:codeSortId;

--------------------------------------------
--deleteCodeSort
update BC_CODE_SORT set STATUS='0' where CODE_SORT_ID=:codeSortId;

--------------------------------------------
--listCode
SELECT * from BC_CODE where CODE_SORT_ID=:codeSortId AND STATUS='1';


--------------------------------------------
--getCodeSortByPId
SELECT * from BC_CODE_SORT where P_CODE_SORT_ID =:codeSortId AND STATUS='1';

--------------------------------------------
--getCodeSortDetailByKey
SELECT * from BC_CODE_SORT where
 P_CODE_SORT_ID=( SELECT CODE_SORT_ID FROM BC_CODE_SORT WHERE  CODE_SORT_KEY=:codeSortKey)  AND STATUS='1' AND HAS_CHILD='0'
