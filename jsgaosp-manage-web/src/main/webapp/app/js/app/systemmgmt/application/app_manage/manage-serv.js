
/**
 * res 为自定义的$resource
 */
angular.module('app').factory('BcSysApplicationService', ['res', 'GG',
    function (res, GG) {
        var res = res(GG.BASE + '/applications/:type');
        var service = {
            listApplications: function (currentPage,keyword,userId) {
                return res.get({
                	currentPage:currentPage,
                	keyword:keyword,
                	userId:userId,
                	type:'list_applications'
                }).$promise;
            }
            ,
            createApplications: function (applications,userId) {
            	alert(userId);
            	applications.createUserId=userId;
                return res.save(applications,userId).$promise;
            },
            updateApplications: function (applications,userId) {
            	applications.approvalUserId=userId;
                return res.update(applications,userId).$promise;
            },
            deleteApplications: function (Id,userId) {
                return res.delete({
                	Id:Id,
                	userId:userId
                }).$promise;
            },
        };
        return service;
    }
]);