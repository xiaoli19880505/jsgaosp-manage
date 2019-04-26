
/**
 * res 为自定义的$resource
 */
angular.module('app').factory('OrgService', ['res', 'GG',
    function (res, GG) {
        var res = res(GG.BASE + '/org/:type');
        var service = {
        	loadOrg: function () {
                return res.get({
                	type:'list_org'
                }).$promise;
            },
            getOrgInfo: function (id) {
                return res.get({
                	id: "",
                    type:'get_org'
                }).$promise;
            },
            updateOrg: function (orgItem) {

                return res.get({

                    orgName:orgItem.org_name,
                    orgNo:orgItem.org_no,
                    pOrgNo:orgItem.p_org_no,
                    type:'update_org'
                }).$promise;
            },
            addOrg: function (orgItem) {
                return res.get({
                    orgName:orgItem.org_name,
                    orgNo:orgItem.org_no,
                    pOrgNo:orgItem.p_org_no,
                    type:'save_org'
                }).$promise;
            },
            deleteOrg: function (orgItem) {
                return res.get({
                    orgId: orgItem.orgNo,
                    type:"delete_org"


                }).$promise;
            }

        };
        return service;
    }
]);