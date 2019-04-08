angular.module('app').factory('nameService',['res','GG',
                                             function(res,GG){
	var res = res(GG.BASE + '/nameValidate/:type');
	var service = {
			nameExist:function(sign,name){
				return res.get({
					sign:sign,
					name:name,
					type:'nameExist'
				}).$promise;
			}
	};
	return service;
}                                       
]);