'use strict';

/* Controllers */
// hospital_people controller
app.controller('SystemCodeSortController',
		['$scope', '$http', '$state', '$modal', '$stateParams', '$timeout', 'modalServ','BcCodeSortService', 'GG',
           function ($scope, $http, $state, $modal, $stateParams,$timeout, modalServ,BcCodeSortService,GG) {
	
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
    /*$scope.search=function(){
    	$scope.loadCodeSort();
    }*/
	
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
	
	//多选
    $scope.chooseCodeSort = [];
    $scope.choose = function(chk,item,index){
    	item.indexs = index;
    	if(chk){
    		$scope.chooseCodeSort.push(item);
    	}else if(!chk){
    		$scope.chooseCodeSort.splice($scope.chooseCodeSort.indexOf(item),1);
    	}
    }
    	
    $scope.chooseAll = function(master){
    	if(master){
    		$scope.chooseCodeSort=angularjs.copy($scope.codesort);
    	}else {
    		$scope.chooseCodeSort=[];
    	}
	}
	
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
}]);

app.filter("hidePasswordFilter",function(){
	return function(input){
		if(input=="123456"||input==""||input==null){
			return input;
		}else{
			input=input.substring(0,input.length-4)+"****";
		}
		return input;
	}
});

