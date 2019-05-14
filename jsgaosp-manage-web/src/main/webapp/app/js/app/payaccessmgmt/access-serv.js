/**
 * res 为自定义的$resource
 */
angular.module('app').factory('PayAccessConfService', ['res', 'GG',
    function (res, GG) {
        var res = res(GG.BASE + '/pay/app/:type');
        var service = {
            listAccessConf: function (currentPage,businessName) {
                return res.get({
                    currentPage: currentPage,
                    businessName: businessName,
                    type: 'list'
                }).$promise;
            },
            createAccessConf: function (payAccess) {
                return res.save(payAccess).$promise;
            },
            approval: function (approval) {
                console.log('approval:'+approval);

                return res.get({
                    applyId:approval.applyId,
                    applyInfoId:approval.applyInfoId,
                    approvalStatus:approval.approvalStatus,
                    approvalOpinion:approval.approvalOpinion,
                    type:'approval'
                }).$promise;
            },
            delete: function (id) {
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