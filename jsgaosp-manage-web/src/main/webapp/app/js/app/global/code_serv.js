angular.module('app').factory('codeService',['res','GG',
                                             function(res,GG){
	var res = res(GG.BASE + '/code/:type');
	var service = {
			getCode:function(sign){
				return res.get({
					sign:sign,
					type:'getCode'
				}).$promise;
			}
	
};
return service;
}                                       
]);