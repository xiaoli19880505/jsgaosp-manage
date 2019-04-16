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

--------------------------------------------
--listCode
SELECT * from BC_CODE where CODE_SORT_ID=:codeSortId;

--------------------------------------------
--saveCode
INSERT INTO BC_CODE(CODE_ID,CODE_SORT_ID,CODE_KEY,CODE_TEXT,STATUS)
VALUES(:codeId,:codeSortId,:codeKey,:codeText,:status);

--------------------------------------------
--updateCode
update BC_CODE set CODE_KEY=:codeKey,CODE_TEXT=:codeText where CODE_ID=:codeId;

--------------------------------------------
--deleteCode
delete from BC_CODE where CODE_ID=:codeId;

--------------------------------------------
--getCodeSortById
SELECT * from BC_CODE_SORT where CODE_SORT_ID =:codeSortId

--------------------------------------------
--getCodeList
SELECT *
FROM BC_CODE C
WHERE C.CODE_SORT_ID =
      (SELECT S.CODE_SORT_ID
       FROM BC_CODE_SORT S
       WHERE S.CODE_SORT_KEY = :codeSortKey);
