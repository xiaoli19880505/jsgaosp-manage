--------------------------------------------
--insertApplication
INSERT INTO bc_declare_app(id,app_name,org_id,sys_type,status,create_date,create_user_id,access_type)
VALUES(:id,:app_name,:org_id,:sys_type,:status,to_date(:create_date,'yyyy-MM-dd HH24:mi:ss'),:create_user_id,:access_type);
--------------------------------------------
--insertApplicationInfo
INSERT INTO bc_declare_app_info(id, app_id,guide_addr, online_addr,online_qaq_addr,yw_type, xz_type, memo,
status,approval_opinion,create_date,create_user_id, approval_date, approval_user_id, bl_type,
version, working_status, server_type,icon_url,approval_status,legal_basis,conditions) 
VALUES(:info_id, :app_id,:guide_addr, :online_addr,:online_qaq_addr,:yw_type, :xz_type, :memo,
:status,:approval_opinion,sysdate,:create_user_id, '', '', :bl_type,
:version, :working_status,:server_type,:icon_url,:approval_status,:legal_basis,:conditions);
--------------------------------------------
--insertBcQrcode
insert into bc_qrcode(id,org_id,app_id,qr_code_url,qr_code_img_url,qr_code_type,
create_user_id,create_date,update_user_id,update_date,status)
values(:id,:org_id,:app_id,:qr_code_url,:qr_code_img_url,:qr_code_type,
:create_user_id,sysdate,'','',:status)

--------------------------------------------
--existsArgsKey
SELECT COUNT(0) from bc_declare_app WHERE id=:id;
--------------------------------------------
--existsApplicationInfo
SELECT COUNT(0) from bc_declare_app_info WHERE id=:id;
--------------------------------------------
--updateApplicationInfo
update bc_declare_app_info 
set
   guide_addr = :guide_addr,
   online_addr = :online_addr,
   online_qaq_addr = :online_qaq_addr,
   yw_type = :yw_type,
   xz_type = :xz_type,
   memo = :memo,
   approval_date = sysdate,
   approval_user_id = :approval_user_id,
   bl_type = :bl_type,
   server_type = :server_type,
   icon_url = :icon_url,
   approval_opinion = :approval_opinion,
   approval_status = :approval_status,
   conditions = :conditions,
   legal_basis = :legal_basis
   where  id = :id
   
--------------------------------------------
--updateApplication
update bc_declare_app set  status = :status  where id=:id;

--------------------------------------------
--updateBcQrcode
update bc_qrcode set  
qr_code_url = :qr_code_url ,
qr_code_img_url = :qr_code_img_url ,
update_user_id=:update_user_id,
update_date=sysdate
where id=:id;
--------------------------------------------
--listApplication
  WITH TMP AS
 (SELECT A.APP_NAME, A.ORG_ID, A.SYS_TYPE, MAX(I.CREATE_DATE) AS MDATE
    FROM BC_DECLARE_APP A, BC_DECLARE_APP_INFO I
   WHERE A.ID = I.APP_ID
   GROUP BY A.APP_NAME, A.ORG_ID, A.SYS_TYPE),
   tmp2 as (
SELECT t1.id,
       t1.app_name,
       GET_ORG_NAME(t1.org_id) as org_name,
       t1.org_id,
       t1.sys_type,
       GET_CODE_SORT_TEXT('sys_type', t1.sys_type) as sys_type_name,
       t1.status,
       t1.create_date,
       t1.create_user_id,
       t1.access_type,
       t2.id as info_id,
       t2.app_id as app_id,
       t2.guide_addr,
       t2.online_addr,
       t2.online_qaq_addr,
       t2.yw_type,
       t2.xz_type,
       t2.memo,
       t2.status as version_status,
       t2.approval_opinion,
       t2.create_date as ver_create_date,
       t2.create_user_id as ver_create_user_id,
       t2.approval_date,
       t2.approval_user_id,
       t2.bl_type,
       t2.version,
       t2.working_status,
       GET_CODE_SORT_TEXT('working_status', t2.working_status) as working_status_name,
       t2.server_type,
       t2.icon_url,
       t2.approval_status,
       GET_CODE_SORT_TEXT('app_status', t2.approval_status) as approval_status_name,
       t2.conditions,
       t2.legal_basis
  FROM BC_DECLARE_APP T1, BC_DECLARE_APP_INFO T2, TMP
 WHERE T1.ID = T2.APP_ID
   AND T1.APP_NAME = TMP.APP_NAME
   AND T1.ORG_ID = TMP.ORG_ID
   AND T1.SYS_TYPE = TMP.SYS_TYPE
   AND T2.CREATE_DATE = TMP.MDATE
   )
   select tmp2.*,bq.id as qrid,bq.qr_code_url,bq.qr_code_img_url 
   from tmp2 left join bc_qrcode  bq ON tmp2.id = bq.app_id
   WHERE 1=1 and tmp2.status = '1'
   and tmp2.org_id = :orgNo
   order by tmp2.create_date desc
--------------------------------------------
--updateInfoStatus
update bc_declare_app_info set 
    status = :version_status,
   approval_date = sysdate,
   approval_user_id = :approval_user_id
   where  app_id = :app_id
   
--------------------------------------------   
--updateAudit
update bc_declare_app_info 
set
   approval_opinion = :approval_opinion,
   approval_status = :approval_status,
   approval_date = sysdate,
   working_status = :working_status,
   approval_user_id =:approval_user_id
   where id = :id
   
--------------------------------------------   
--listHisVersion
   select a.id,
       a.app_name,
       GET_ORG_NAME(a.org_id) as org_name,
       a.org_id,
       a.sys_type,
       GET_CODE_SORT_TEXT('sys_type', a.sys_type) as sys_type_name,
       a.status,
       a.create_date,
       a.create_user_id,
       a.access_type,
       i.id as info_id,
       i.app_id as app_id,
       i.guide_addr,
       i.online_addr,
       i.online_qaq_addr,
       i.yw_type,
       i.xz_type,
       i.memo,
       i.status as version_status,
       i.approval_opinion,
       i.create_date as ver_create_date,
       i.create_user_id as ver_create_user_id,
       i.approval_date,
       i.approval_user_id,
       i.bl_type,
       i.version,
       i.working_status,
       GET_CODE_SORT_TEXT('working_status', i.working_status) as working_status_name,
       i.server_type,
       i.icon_url,
       i.approval_status,
       i.conditions,
       i.legal_basis,
       GET_CODE_SORT_TEXT('app_status', i.approval_status) as approval_status_name
  from bc_declare_app a, bc_declare_app_info i
 where a.id = i.app_id and a.status ='1' and i.approval_status = '01'
 and a.org_id =:org_id and a.app_name =:app_name and a.sys_type = :sys_type

--------------------------------------------------------------
--updateAppWorkingStatus
update bc_declare_app_info set
   working_status = '00'
   where  app_id = :app_id and approval_status ='01'
-----------------------------------------------------------
--selectInfoAppById
   select * from  bc_declare_app_info i ,bc_declare_app a  where  a.id = i.app_id  and i.id =:info_id

-----------------------------------------------------------
--queryInfoList
   select * from  bc_declare_app_info i,bc_declare_app a where i.app_id =:app_id and a.org_id =:org_id order by version desc

-----------------------------------------------------------
--updateInfoWorkStatus
   update bc_declare_app_info set
    working_status = :working_status,
   approval_date = sysdate,
   approval_user_id = :approval_user_id
   where  app_id = :app_id and approval_status =:approval_status and  id <> :id

-----------------------------------------------------------
--updateInfoWorkStatusdisable
   update bc_declare_app_info set
    working_status = :working_status,
   approval_date = sysdate,
   approval_user_id = :approval_user_id
   where    id = :id

--------------------------------------------
--getApplicationsByAreaNo
   select a.id as app_id,
          a.sys_type,
          a.app_name,
          c.area_name,
          d.qr_code_img_url,
          d.qr_code_url,
          i.memo,
          i.guide_addr,
          i.online_addr,
          i.online_qaq_addr,
          i.icon_url
  from bc_declare_app a, bc_declare_app_info i, bc_org b, bc_area c, bc_qrcode d
  where a.id = i.app_id and a.org_id = b.org_no and b.area_no = c.area_no and a.id = d.app_id(+)
      and a.status = '1' and i.working_status = '01' and b.area_no = :areaNo
      and a.sys_type = :sys_type
--------------------------------------------
--getCustomizeList
   select a.id as app_id,
          a.sys_type,
          a.app_name,
          c.area_name,
          d.qr_code_img_url,
          d.qr_code_url,
          i.memo,
          i.guide_addr,
          i.online_addr,
          i.online_qaq_addr,
          i.icon_url
  from bc_declare_app a, bc_declare_app_info i, bc_org b, bc_area c, bc_qrcode d, bc_app_customize e
  where a.id = i.app_id and a.org_id = b.org_no and b.area_no = c.area_no and a.id = e.app_id
      and a.status = '1' and i.working_status = '01' and e.IDCARD_NO = :idcardNo
      and a.sys_type = :sys_type and e.status = '1'

--------------------------------------------
--addCustomize
INSERT INTO bc_app_customize(id,IDCARD_NO,APP_ID,TOP_DATE,STATUS,CREATE_DATE,SORT)
VALUES(:id,:idcardNo,:appId,sysdate,'1',sysdate,1);

--------------------------------------------
--existsCustomizeApp
SELECT COUNT(0) from bc_app_customize WHERE IDCARD_NO=:idcardNo and APP_ID=:appId and status = '1' ;

-----------------------------------------------------------
--updateCustomize
update bc_app_customize set
  TOP_DATE = sysdate,
  update_date = sysdate
   where  IDCARD_NO=:idcardNo and APP_ID=:appId and status = '1';

--------------------------------------------
--queryApplicationsWithAreaNo
   select a.id as app_id,
          a.sys_type,
          a.app_name,
          c.area_name,
          d.qr_code_img_url,
          d.qr_code_url,
          i.memo,
          i.guide_addr,
          i.online_addr,
          i.online_qaq_addr,
          i.icon_url
 from bc_declare_app a join bc_declare_app_info i on a.id = i.app_id join bc_org b on a.org_id = b.org_no
 join (select * from bc_area start with area_no=:areaNo connect by prior area_no=p_area_no) c on b.area_no = c.area_no
 left join bc_qrcode d on a.id = d.app_id
 where a.status = '1' and i.status = '1' and i.working_status = '01' and a.sys_type = :sysType

 --------------------------------------------
--queryApplications
   select a.id as app_id,
          a.sys_type,
          a.app_name,
          c.area_name,
          d.qr_code_img_url,
          d.qr_code_url,
          i.memo,
          i.guide_addr,
          i.online_addr,
          i.online_qaq_addr,
          i.icon_url
 from bc_declare_app a join bc_declare_app_info i on a.id = i.app_id join bc_org b on a.org_id = b.org_no
 join bc_area c on b.area_no = c.area_no left join bc_qrcode d on a.id = d.app_id
 where a.status = '1' and i.status = '1' and i.working_status = '01' and a.sys_type = :sysType