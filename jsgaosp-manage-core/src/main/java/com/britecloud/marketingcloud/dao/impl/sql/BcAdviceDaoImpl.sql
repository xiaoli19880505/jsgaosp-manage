--------------------------------------------
--listAdvice
select t.id,t.user_id,t.content,t.create_date,t.deal_status,t.deal_user_id,t.deal_date,t.status 
from bc_advice t order by t.create_date desc