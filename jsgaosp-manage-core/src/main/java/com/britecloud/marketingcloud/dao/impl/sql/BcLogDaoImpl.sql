--------------------------------------------
--saveLoginLog
INSERT INTO BC_LOGIN_LOG(log_id,type,title,content,trigger_time,source)
VALUES(:loginId,:type,:title,:content,to_date(:triggerTime,'YYYY-MM-DD HH24:MI:SS'),:source);

--------------------------------------------
--saveOperationLog
INSERT INTO BC_OPERATION_LOG(id,USER_LOGIN_NAME,OPERATION,METHOD,PARAMS,IP,OPERATION_TIME)
VALUES(:id,:userLoginName,:operation,:method,:params,:ip,sysdate);
