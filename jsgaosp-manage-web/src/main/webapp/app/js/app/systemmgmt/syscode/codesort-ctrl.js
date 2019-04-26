'use strict';



/* Controllers */
app.controller('SystemCodeSortController',
		['$scope', '$http', '$state', '$modal', '$stateParams', '$timeout', 'modalServ','BcCodeSortService', 'GG','$rootScope',
           function ($scope, $http, $state, $modal, $stateParams,$timeout, modalServ,BcCodeSortService,GG,$rootScope) {
	
	$scope.totalItems = 100;
    $scope.currentPage = 1;
    $scope.maxSize = 5;
    $scope.keyword="";
    
    $scope.GGuser = GG.user;
    $scope.GGsysadmin = GG.sysadmin;
    
    $scope.loadCodeSort=function(){
        BcCodeSortService.listCodeSort($scope.currentPage,$scope.keyword).then(function(res){
			console.log(res)
    		$scope.codesorts=res.data.list;
    		$scope.totalItems=res.data.totalCount;
    		$scope.currentPage=res.data.page;
    		$scope.chooseCodeSort=[];
    	})
	}
	
	//搜索
    $scope.search=function(){
    	$scope.loadCodeSort();
    }
	
	//弹出新增窗口
    $scope.open = function (size, type,index) {
        var modalInstance = $modal.open({
            templateUrl: 'tpl/systemmgmt/syscode/codesort_form.html',
            controller: 'ModalCodeSortInstanceCtrl',
            size: size,
            backdrop: 'static',
            resolve: {
                items: function () {
					var codesort = {};
					if(index != null){
						codesort = $scope.codesorts[index];
					}
					$scope.items = [type,codesort];
                    return $scope.items;
                }
            }
        });

        modalInstance.result.then(function (items) {
            if (items[0]) {
                $scope.loadCodeSort();
            }
        }, function () {
            
        });
    };

    $scope.loadCodeSort();
	
	//删除数据字典
	$scope.deleteCodeSort=function(codeSortId,codeSortKey){
    	modalServ.showModal({}, {
			bodyText: "确定要删除数据字典【"+codeSortKey+"】?"
		}).then(function(result) {
			BcCodeSortService.deleteCodeSort(codeSortId).then(function(data) {
				if(data.code == "10000"){
					 toastr.success('删除成功！');
					 $("#toast-container").css("left", "46%");
					 $scope.loadCodeSort();
				}else{
					toastr.error('删除失败！');
					$("#toast-container").css("left", "46%");
				}
			});
		});
    }
	
	$scope.pageChanged = function () {
		$scope.loadCodeSort();
	};

	/**
	 * 点击数据字典行，加载数据字典明细
	 */
	$scope.loadCodeDetails=function(item){
		// console.log(item);
		$rootScope.codeSortId=item.codeSortId
		$scope.codeSortId = item.codeSortId;
		$rootScope.codeSortText = item.codeSortText;

	}
}])
app.controller('SystemCodeController',
['$scope', '$http', '$state', '$modal', '$stateParams', '$timeout', 'modalServ','BcCodeSortDetailService', 'GG','$rootScope','BcCodeSortService',
function ($scope, $http, $state, $modal, $stateParams,$timeout, modalServ,BcCodeSortDetailService,GG,$rootScope,BcCodeSortService) {
	/**
	 * 数据字典明细Controller 
	 */
	$scope.totalItems = 100;
    $scope.currentPage = 1;
    $scope.maxSize = 5;
    $scope.keyword="";
    
    $scope.GGuser = GG.user;
    $scope.GGsysadmin = GG.sysadmin;


    console.log($rootScope);
    var codeSortId=$rootScope.codeSortId;

   //监控codeSortId
   $scope.$watch('codeSortId', function (newValue, oldValue) {
	   $scope.loadCodeDetails(newValue);
	   
   });

    $scope.loadCodeDetails=function(codeSortId){
		BcCodeSortDetailService.listCode($scope.currentPage,codeSortId).then(function(res){
    		$scope.codedetails=res.data.list;
    		$scope.totalItems=res.data.totalCount;
    		$scope.currentPage=res.data.page;

		});
		//获取标题
		$scope.codeSortText=	$rootScope.codeSortText;
		// BcCodeSortService.getCodeSortById(codeSortId).then(function(res){
		// 	$scope.codeSortText = res.data.codeSortText;
		// });
	}
	
	//搜索
    $scope.search=function(){
    	$scope.loadCodeDetails($rootScope.codeSortId);
    }
	
	//弹出新增窗口
    $scope.open = function (size, type,index) {
        var modalInstance = $modal.open({
            templateUrl: 'tpl/systemmgmt/syscode/code_form.html',
            controller: 'ModalCodeInstanceCtrl',
            size: size,
            backdrop: 'static',
            resolve: {
                items: function () {
					var code = {};
					if(index != null){
						code = $scope.codedetails[index];
					}else{
						code.codeSortId = $rootScope.codeSortId;
					}
					$scope.items = [type,code];
                    return $scope.items;
                }
            }
        });

        modalInstance.result.then(function (items) {
            if (items[0]) {
                $scope.loadCodeDetails($rootScope.codeSortId);
            }
        }, function () {
            
        });
    };
	
	//删除数据字典
	$scope.deleteCode=function(codeId){
    	modalServ.showModal({}, {
			bodyText: "确定要删除吗?"
		}).then(function(result) {
			BcCodeSortDetailService.deleteCode(codeId).then(function(data) {
				if(data.code == "10000"){
					 toastr.success('删除成功！');
					 $("#toast-container").css("left", "46%");
					 $scope.loadCodeDetails($rootScope.codeSortId);
				}else{
					toastr.error('删除失败！');
					$("#toast-container").css("left", "46%");
				}
			});
		});
    }
	
	$scope.pageChanged = function () {
		$scope.loadCodeDetails($rootScope.codeSortId);
	};

}]);
