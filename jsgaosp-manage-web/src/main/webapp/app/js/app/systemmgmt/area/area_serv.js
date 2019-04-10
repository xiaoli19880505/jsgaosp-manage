
/**
 * res 为自定义的$resource
 */
angular.module('app').factory('AreaService', ['res', 'GG',
    function (res, GG) {
        var res = res(GG.BASE + '/area/:type');
        var service = {
        	loadArea: function () {
                return res.query({
                	type:'list_area'
                }).$promise;
            },
            getAreaInfo: function (id) {
                return res.get({
                	id: "",
                    type:'get_area'
                }).$promise;
            },
            addCompany: function (name) {
                return res.save({
                	name: name
                }).$promise;
            },
            deleteArea: function (id) {
                return res.get({
                    type:"delete_area",
                	id: id

                }).$promise;
            },
            saveCompany: function (company) {
            	console.info(company);
                return res.update(company).$promise;
            },
            query: function () {
                return res.query().$promise;
            },
            getOrgList: function () {
                return res.query({
                	type:"getOrgList"
                }).$promise;
            },
            checkCode: function (code,orgId) {
                return res.get({
                	code:code,
                	orgId:orgId,
                	type:'checkCode'
                }).$promise;
            }
        };
        return service;
    }
]);