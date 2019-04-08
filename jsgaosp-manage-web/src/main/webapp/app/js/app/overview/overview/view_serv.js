/**
*
*/
angular.module('app').factory('overviewService',['res','GG',
     function(res,GG){
		var res = res(GG.BASE + '/overview/overview');
		var service = {
			get:function(page,size){
				return res.get({
					 page: page,
	                 size: size
				}).$promise;
			},
		};
		return service;
	}
]);