/**
 * res 为自定义的$resource
 */
angular.module('app').factory('PayAppConfService', ['res', 'GG',
    function (res, GG) {
        var res = res(GG.BASE + '/pay/app/:type');
        var service = {
            listPayConf: function (currentPage) {
                return res.get({
                    currentPage: currentPage,
                    type: 'list'
                }).$promise;
            },
            createPayConf: function (user) {
                var orgId = user.orgId;
                return res.save(user, orgId).$promise;
            },
            updatePayConf: function (user) {
                return res.update(user).$promise;
            },
            deletePayConf: function (id) {
                return res.delete({
                    id: id
                }).$promise;
            },
            getInfo: function (id) {
                return res.get({
                    id: id,
                    type: 'getInfo'
                }).$promise;
            }
        };
        return service;
    }
]);