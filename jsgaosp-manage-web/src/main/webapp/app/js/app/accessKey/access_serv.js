angular.module('app').factory('AccessKeyService',['res','GG',
                                                  function(res,GG){
	var res = res(GG.BASE + '/accessKey/:type');

	var service={
			getAllAccessKey:function(query,page,size){
				return res.get({
					query:query,
					page:page-1,
					size:size,
					type:'getAllAccessKey'
				}).$promise;
			},
			save:function(accessKey){
				return res.save(accessKey).$promise;
			},
			delete:function(ids){
				return res.delete({
					ids: ids
				}).$promise;
			},
			update:function(accessKey){
				return res.update(accessKey).$promise;
			}
	}
	return service;
}                                       
]);