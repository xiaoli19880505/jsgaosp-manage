
/**
 * res 为自定义的$resource
 */
angular.module('app').factory('AreaService', ['res', 'GG',
    function (res, GG) {
        var res = res(GG.BASE + '/area/:type');
        var service = {
        	loadArea: function () {
                return res.get({
                	type:'list_area'
                }).$promise;
            },
            getAreaInfo: function (id) {
                return res.get({
                	id: "",
                    type:'get_area'
                }).$promise;
            },
            updateArea: function (areaItem) {

                return res.get({

                    areaName:areaItem.area_name,
                    areaNo:areaItem.area_no,
                    pAreaNo:areaItem.p_area_no,
                    id:areaItem.id,
                    status:areaItem.status,
                    type:'update_area'
                }).$promise;
            },
            addArea: function (areaItem) {
                return res.get({
                    areaName:areaItem.area_name,
                    areaNo:areaItem.area_no,
                    pAreaNo:areaItem.p_area_no,
                    id:areaItem.id,
                    status:areaItem.status,
                    type:'save_area'
                }).$promise;
            },
            deleteArea: function (areaItem) {
                return res.get({
                    id: areaItem.id,
                    type:"delete_area"


                }).$promise;
            }

        };
        return service;
    }
]);