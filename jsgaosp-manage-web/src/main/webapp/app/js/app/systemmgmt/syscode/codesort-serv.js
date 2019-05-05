
/**
 * res 为自定义的$resource
 */
angular.module('app').factory('BcCodeSortService', ['res', 'GG',
    function (res, GG) {
        var res = res(GG.BASE + '/code_sort/:type');
        var service = {
            listCodeSort: function (currentPage,keyword,orgId) {
                return res.get({
                	currentPage:currentPage,
                	keyword:keyword,
                	type:'list_code_sort',
                    orgNo:orgId
                }).$promise;
            },
            getCodeSortById:function(codeSortId){
                return res.get({
                    pCodeSortId:codeSortId,
                    type:'list_code_detail'
                }).$promise;
            },
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
angular.module('app').factory('BcCodeSortDetailService', ['res', 'GG',
    function (res, GG) {
        var res = res(GG.BASE + '/code_sort_detail/:type');
        var service = {
            listCode: function (currentPage,codeSortId) {
                return res.get({
                	currentPage:currentPage,
                	pCodeSortId:codeSortId,
                	type:'list_code_detail'
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
                	codeSortId:codeId
                }).$promise;
            }
        };
        return service;
    }
]);
