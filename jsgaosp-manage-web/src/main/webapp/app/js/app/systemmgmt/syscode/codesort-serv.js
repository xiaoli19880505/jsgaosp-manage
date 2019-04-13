
/**
 * res 为自定义的$resource
 */
angular.module('app').factory('BcCodeSortService', ['res', 'GG',
    function (res, GG) {
        var res = res(GG.BASE + '/codesort/:type');
        var service = {
            listCodeSort: function (currentPage,keyword) {
                return res.get({
                	currentPage:currentPage,
                	keyword:keyword,
                	type:'list_codesort'
                }).$promise;
            }
            ,
            createCodeSort: function (codesort) {
                return res.save(codesort).$promise;
            },
            updateCodeSort: function (codesort) {
                return res.update(codesort).$promise;
            },
            deleteCodeSort: function (codeSortId) {
                return res.delete({
                	codeSortId:codeSortId
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