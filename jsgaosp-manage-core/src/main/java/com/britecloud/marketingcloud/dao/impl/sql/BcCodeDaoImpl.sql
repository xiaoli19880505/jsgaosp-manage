--------------------------------------------
--listCodeSort
SELECT * FROM BC_CODE_SORT where (CODE_SORT_TEXT like '%'||:keyword||'%' or CODE_SORT_KEY like '%'||:keyword||'%')

--------------------------------------------
--saveCodeSort
INSERT INTO BC_CODE_SORT(CODE_SORT_ID,CODE_SORT_KEY,CODE_SORT_TEXT,STATUS)
VALUES(:codeSortId,:codeSortKey,:codeSortText,:status);

--------------------------------------------
--updateCodeSort
update BC_CODE_SORT set CODE_SORT_KEY=:codeSortKey,CODE_SORT_TEXT=:codeSortText where CODE_SORT_ID=:codeSortId;

--------------------------------------------
--deleteCodeSort
delete from BC_CODE_SORT where CODE_SORT_ID=:codeSortId;

