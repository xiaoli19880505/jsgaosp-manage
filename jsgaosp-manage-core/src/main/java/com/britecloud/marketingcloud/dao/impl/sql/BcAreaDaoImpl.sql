--------------------------------------------
--listArea
SELECT * FROM bc_area where P_AREA_NO=:pAreaNo;

--------------------------------------------
--saveArea
INSERT INTO bc_area(id,area_no,area_name,p_area_no,status)
VALUES(:id,:areaNo,:areaName,:pAreaNo,:status);

--------------------------------------------
--updateArea
update bc_area set area_no=:areaNo,area_name=:areaName,p_area_no=:pAreaNo,status=:status where id=:id

--------------------------------------------
--deleteArea
delete from bc_area where id=:id;

--------------------------------------------
--getAreaById
SELECT * from bc_area WHERE  ID = :id;

--------------------------------------------
--existsAreaNo
SELECT COUNT(1) from bc_area WHERE area_no=:areaNo;

--------------------------------------------
--getAreaByAreaNo
SELECT * from bc_area WHERE  area_no = :areaNo;

