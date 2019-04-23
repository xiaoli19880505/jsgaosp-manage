--------------------------------------------
--getSysList
SELECT s.id,s.sys_name,s.area_no from bc_declare_sys s WHERE s.area_no LIKE :areaNo||'%'