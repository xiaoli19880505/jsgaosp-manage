
/**
 * res 为自定义的$resource
 */
angular.module('app').factory('BcSysArgsService', ['res', 'GG',
    function (res, GG) {
        var res = res(GG.BASE + '/sysargs/:type');
        var service = {
            listSysArgs: function (currentPage,keyword) {
                return res.get({
                	currentPage:currentPage,
                	keyword:keyword,
                	type:'list_sysargs'
                }).$promise;
            }
            ,
            createArgs: function (args) {
                return res.save(args).$promise;
            },
            updateArgs: function (args) {
                return res.update(args).$promise;
            },
            deleteArgs: function (argsId) {
                return res.delete({
                	id:argsId
                }).$promise;
            },
            /*
            getUserInfo: function (id) {
                return res.get({
                	userId: id,
                    type:'getInfo'
                }).$promise;
            },
            resetPwd: function (userId) {
                return res.get({
                	userId:userId,
                	type:"resetPwd"
                }).$promise;
            }*/
        };
        return service;
    }
]);