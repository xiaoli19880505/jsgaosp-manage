
/**
 * res 为自定义的$resource
 */
angular.module('app').factory('CompanyService', ['res', 'GG',
    function (res, GG) {
        var res = res(GG.BASE + '/compaign/company/:type');
        var service = {
        	loadCompany: function () {
                return res.query({
                	type:'loadCompany'
                }).$promise;
            },
            getCompanyInfo: function (id) {
                return res.get({
                	companyId: id,
                    type:'getInfo'
                }).$promise;
            },
            addCompany: function (name) {
                return res.save({
                	name: name
                }).$promise;
            },
            deleteCompany: function (companyId) {
                return res.delete({
                	companyId: companyId
                }).$promise;
            },
            saveCompany: function (company) {
            	console.info(company);
                return res.update(company).$promise;
            },
            query: function () {
                return res.query().$promise;
            },
            getOrgList: function () {
                return res.query({
                	type:"getOrgList"
                }).$promise;
            },
            checkCode: function (code,orgId) {
                return res.get({
                	code:code,
                	orgId:orgId,
                	type:'checkCode'
                }).$promise;
            }
        };
        return service;
    }
]);