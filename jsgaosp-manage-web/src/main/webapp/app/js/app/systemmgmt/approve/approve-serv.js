
/**
 * res 为自定义的$resource
 */
angular.module('app').factory('BcSysApproveService', ['res', 'GG',
    function (res, GG) {
        var res = res(GG.BASE + '/applications/:type');
        var service = {
            listApproves: function (currentPage,keyword) {
                return res.get({
                	currentPage:currentPage,
                	keyword:keyword,
                	type:'list_applications'
                }).$promise;
            }
            ,
            updateApprove: function (approve) {
                return res.update(approve).$promise;
            },
            deleteApplications: function (Id) {
                return res.delete({
                	Id:Id
                }).$promise;
            },
        };
        return service;
    }
]);