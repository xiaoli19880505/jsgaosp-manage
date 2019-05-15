
/**
 * res 为自定义的$resource
 */
angular.module('app').factory('OrgService', ['res', 'GG',
    function (res, GG) {
        var res = res(GG.BASE + '/org/:type');
        var service = {
            loadOrg: function () {
                return res.get({
                    orgNo:"111",
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
                orgItem.type="update_org";
                return res.get(
                    orgItem
                ).$promise;
            },
            addOrg: function (orgItem) {
                orgItem.type="save_org";
                orgItem.orgType="01";
                orgItem.status="1";
                return res.get(orgItem).$promise;
            },
            addOrg: function (orgItem) {
                orgItem.type="save_org";
                orgItem.orgType="02";
                orgItem.status="1";
                return res.get(orgItem).$promise;
            },
            loadDepartmentList:function(currentPage,orgNo){
                return res.get ( {
                    currentPage:currentPage,
                    pOrgNo:orgNo,
                    type:'listByOrgId'
                }).$promise;
            },
            addDepatemnt: function (orgItem) {
                orgItem.type="save_org";
                orgItem.orgType="02";
                orgItem.status="1";
                return res.get(orgItem).$promise;
            },
            deleteOrg: function (orgItem) {
                return res.get({
                    orgNo: orgItem.orgNo,
                    type:"delete_org"
                }).$promise;
            }

        };
        return service;
    }
]);