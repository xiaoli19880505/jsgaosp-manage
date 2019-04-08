angular.module('app').factory('categoryService',['res','GG',
                                               function(res,GG){
	var res = res(GG.BASE + '/global/category/:type');
	var service = {
			save: function (category) {			
				return res.save(category).$promise;
			},
			delete: function (catId) {	
				return res.delete({
					catId:catId	
				}).$promise;
			},
			loadCategoryListByType:function(categoryType,queryName){
				return res.query({
					categoryType : categoryType,
					queryName :queryName,
					type:'loadCategoryListByType'
				}).$promise;
			},
			getCode:function(tableName,header,columnName){
				return res.get({
					tableName:tableName,
					header:header,
					columnName:columnName,
					type:'getCode'
				}).$promise;
			},
			updateBranchName:function(catId,label){
				return res.get({
					catId:catId,
					label:label,
					type:'updateBranchName'
				}).$promise;
			}
	};
	return service;
}                                       
]);