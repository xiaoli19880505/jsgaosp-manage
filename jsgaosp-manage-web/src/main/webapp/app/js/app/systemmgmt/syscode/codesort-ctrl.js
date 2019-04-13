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
        BcSysArgsService.listCodeSort($scope.currentPage,$scope.keyword).then(function(res){
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
    $scope.open = function (size, type) {
        var modalInstance = $modal.open({
            templateUrl: 'tpl/systemmgmt/syscode/codesort_form.html',
            controller: 'ModalCodeSortInstanceCtrl',
            size: size,
            backdrop: 'static',
            resolve: {
                items: function () {
                	var codesort = {};
					$scope.items = [type,codesort];
					console.log($scope.items)
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
    		$scope.chooseCodeSort=angularjs.copy($scope.sysargs);
    	}else {
    		$scope.chooseCodeSort=[];
    	}
    }

    //删除modal
    $scope.deleteModal=function(){
    	if($scope.chooseCodeSort.length!=0){
        	modalServ.showModal({}, {
        		bodyText: '       确定要删除这些记录吗?     '
    		}).then(function(result) {
    			var result = true;
    			angular.forEach($scope.chooseCodeSort,function(item){
    				BcSysArgsService.deleteCodeSort(item.codeSortId).then(function(data) {
	    				if(data.code != "10000"){
	    					result = false;
	    				}
	    			});
    			});
    			if(result){
					 toastr.success('删除成功！');
					 $scope.loadCodeSort();
				}else{
					toastr.error('删除失败！');
				}
    		});
    		}else{
    			bootbox.alert({  
    				buttons: {  
    					ok: {  
    						label: '确定',  
    						className: 'btn-info btn-dark'  
    					}  
    				},  
    				message: '请先选择操作的数据！',  
    				callback: function() {  
    				},  
    				title: "提示",  
    			}); 
    		}			
    }

	//修改
    $scope.update = function(){
    	if($scope.chooseCodeSort.length==1){
    		var chooseCreate = angular.copy($scope.chooseCodeSort[0]);
    		var modalInstance = $modal.open({
    			templateUrl: 'tpl/systemmgmt/syscode/codesort_form.html',
                controller: 'ModalCodeSortInstanceCtrl',
                size: '',
                backdrop: 'static',
                resolve: {
                    items: function () {
                           return ['update',chooseCreate];
                    }
                }
            });

            modalInstance.result.then(function (items) {
                if (items[0]) {//如果modal返回成功的话
                	 $scope.loadCodeSort();
                	 $scope.chooseCodeSort=[];
                }
            }, function () {
                //取消
            });
    	}else{
    		bootbox.alert({  
    			buttons: {  
    				ok: {  
    					label: '确定',  
    					className: 'btn-info btn-dark'  
    				}  
    			},  
    			message: '请先选择一个操作的数据！',  
    			callback: function() {  
    			},  
    			title: "提示",  
    		}); 
    	}	
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

