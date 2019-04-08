
/**
 * res 为自定义的$resource
 */
angular.module('app').factory('OrgService', ['res', 'GG',
    function (res, GG) {
        var res = res(GG.BASE + '/hospital/org/:type');
        var service = {
            loadOrg: function () {
                return res.query({
                	type:'loadOrg'
                }).$promise;
            },
            getOrgInfo: function (id) {
                return res.get({
                	orgId: id,
                    type:'getInfo'
                }).$promise;
            },
            deleteOrg: function (orgId) {
                return res.delete({
                	orgId: orgId
                }).$promise;
            },
            saveOrg: function (org) {
            	console.info(org);
                return res.save(org).$promise;
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

            },
            synchronous:function(orgId){
            	return res.get({
            		orgId:orgId,
            		type:'synchronous'
            	}).$promise;

            }
        };
        return service;
    }
]);