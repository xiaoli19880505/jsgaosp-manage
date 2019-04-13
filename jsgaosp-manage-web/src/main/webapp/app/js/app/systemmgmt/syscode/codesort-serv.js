
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
            }
        };
        return service;
    }
]);

/**
 * res 为自定义的$resource
 */
angular.module('app').factory('BcCodeService', ['res', 'GG',
    function (res, GG) {
        var res = res(GG.BASE + '/code/:type');
        var service = {
            listCode: function (currentPage,codeSortId) {
                return res.get({
                	currentPage:currentPage,
                	codeSortId:codeSortId,
                	type:'list_code'
                }).$promise;
            }
            ,
            createCode: function (codedetail) {
                return res.save(codedetail).$promise;
            },
            updateCode: function (codedetail) {
                return res.update(codedetail).$promise;
            },
            deleteCode: function (codeId) {
                return res.delete({
                	codeId:codeId
                }).$promise;
            }
        };
        return service;
    }
]);
