
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
            /*,
            createUser: function (user) {
            	var orgId = user.orgId;
                return res.save(user,orgId).$promise;
            },
            updateUser: function (user) {
                return res.update(user).$promise;
            },
            deleteUser: function (userId) {
                return res.delete({
                	userId:userId
                }).$promise;
            },
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