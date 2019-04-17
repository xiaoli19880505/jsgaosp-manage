
/**
 * res 为自定义的$resource
 */
angular.module('app').factory('BcSysApproveService', ['res', 'GG',
    function (res, GG) {
        var res = res(GG.BASE + '/approves/:type');
        var service = {
            listApproves: function (currentPage,keyword) {
                return res.get({
                	currentPage:currentPage,
                	keyword:keyword,
                	type:'list_approves'
                }).$promise;
            }
            ,
            updateApprove: function (approves,userId) {
            	approves.approvalUserId=userId;
                return res.update(approves).$promise;
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