
/**
 * res 为自定义的$resource
 */
angular.module('app').factory('ThirdPartySysService', ['res', 'GG',
    function (res, GG) {
        var res = res(GG.BASE + '/thirdPartySysApplicant/:type');
        var service = {
            listApplications: function (currentPage,sysName,status,createUserId) {
                return res.get({
                	currentPage:currentPage,
                	sysName:sysName,
                    status:status,
                    createUserId:createUserId,
                	type:'listThirdPartySys'
                }).$promise;
            }
            ,
            createApplications: function (applications) {
                return res.save(
                    applications
            ).$promise;
            },
            updateApplications: function (applications) {
                delete applications.createDate;
                return res.update(applications).$promise;
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