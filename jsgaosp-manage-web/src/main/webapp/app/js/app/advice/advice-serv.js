/**
 * res 为自定义的$resource
 */
angular.module('app').factory('AdviceService',
		[ 'res', 'GG', function(res, GG) {
			var res = res(GG.BASE + '/advice/:type');
			var service = {
					listAdvice : function(currentPage,keyword) {
					return res.get({
						keyword : keyword,
						currentPage : currentPage,
						type : 'list_advices'
					}).$promise;
				}
			
			};
			return service;
		} ]);