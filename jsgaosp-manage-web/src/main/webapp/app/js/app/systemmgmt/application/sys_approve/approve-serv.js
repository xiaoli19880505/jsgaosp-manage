
/**
 * res 为自定义的$resource
 */
angular.module('app').factory('SysApproveService', ['res', 'GG',
    function (res, GG) {
        var res = res(GG.BASE + '/thirdPartySysApplicant/:type');
        var service = {
            listApplicantforApprova: function (currentPage,sysName,status,createUserId) {
                return res.get({
                	currentPage:currentPage,
                	sysName:sysName,
                    status:status,
                    createUserId:createUserId,
                	type:'listThirdPartySysForApproval'
                }).$promise;
            }
            ,
            sysApprove: function (status,approvalOpinion,approvalUserId,id) {
                return res.get({
                    status:status,
                    approvalOpinion:approvalOpinion,
                    approvalUserId:approvalUserId,
                    id:id,
                    type:'approveSysApplicant'
                }
            ).$promise;
            },

        };
        return service;
    }
]);