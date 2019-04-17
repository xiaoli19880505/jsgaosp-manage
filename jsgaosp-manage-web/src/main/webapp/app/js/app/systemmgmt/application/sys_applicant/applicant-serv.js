
/**
 * res 为自定义的$resource
 */
angular.module('app').factory('ThirdPartySysService', ['res', 'GG',
    function (res, GG) {
        var res = res(GG.BASE + '/thirdPartySysApplicant/:type');
        var service = {
            listApplications: function (currentPage,keyword) {
                return res.get({
                	currentPage:currentPage,
                	keyword:keyword,
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