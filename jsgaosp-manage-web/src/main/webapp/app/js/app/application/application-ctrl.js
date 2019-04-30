'use strict';

app.controller('ApplicationController',['$scope','$http','$state','$modal','$timeout','modalServ','ApplicationService',
	function($scope,$http,$state,$modal,$timeout,modalServ,ApplicationService){
	$scope.totalItems = 100;
    $scope.currentPage = 1;
    $scope.maxSize = 5;
	
	$scope.$watch('orgNo', function (newVal, oldVal) {
		if ($scope.orgNo != null) {
			console.log($scope.orgNo);
			$scope.loadApplications();
		}
	})
	
	
	$scope.loadApplications=function(){
		ApplicationService.listApplications($scope.currentPage,$scope.orgNo).then(function(res){
            console.log(res)
            $scope.applicationList=res.data.list;
            $scope.totalItems=res.data.totalCount;
            $scope.currentPage=res.data.page;
            $scope.chooseArgs=[];
        })
    }
	
	
	//弹出新增窗口
    $scope.open = function (size, type,index) {
        var modalInstance = $modal.open({
            templateUrl: 'tpl/application/application_form.html',
            controller: 'ModalApplicationsCtrl',
            size: size,
            backdrop: 'static',
            resolve: {
                items: function () {
                    var application = {};
                    if(index != null){
                        applications = $scope.applicationList[index];
                    }
                    $scope.items = [type,application,$scope.orgNo];
                    return $scope.items;
                }
            }
        });

        modalInstance.result.then(function (items) {
            if (items[0]) {
                $scope.loadApplications();
            }
        }, function () {

        });
    }
    
  //多选
    $scope.chooseArgs = [];
    $scope.choose = function(chk,item,index){
    	item.indexs = index;
    	if(chk){
    		$scope.chooseArgs.push(item);
    	}else if(!chk){
    		$scope.chooseArgs.splice($scope.chooseArgs.indexOf(item),1);
    	}
    }
    	
    $scope.chooseAll = function(master){
    	if(master){
    		$scope.chooseArgs=angularjs.copy($scope.applicationList);
    	}else {
    		$scope.chooseArgs=[];
    	}
    }
    
  //修改
    $scope.update = function(){
    	if($scope.chooseArgs.length==1){
    		var chooseCreate = $scope.chooseArgs[0];
    		var modalInstance = $modal.open({
    			  templateUrl: 'tpl/application/application_form.html',
    	            controller: 'ModalApplicationsCtrl',
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
                	 $scope.loadApplications();
                	 $scope.chooseArgs=[];
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
    //////
    
    
    //////
    
}])