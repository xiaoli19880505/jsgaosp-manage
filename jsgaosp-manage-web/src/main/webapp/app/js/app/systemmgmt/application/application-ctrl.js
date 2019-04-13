'use strict';

/* Controllers */
// hospital_people controller
app.controller('SystemSysApplicationManagerController',
		['$scope', '$http', '$state', '$modal', '$stateParams', '$timeout', 'modalServ','BcSysApplicationService', 'GG',
           function ($scope, $http, $state, $modal, $stateParams,$timeout, modalServ,BcSysApplicationService,GG) {
	
	$scope.totalItems = 100;
    $scope.currentPage = 1;
    $scope.maxSize = 5;
    $scope.keyword="";
    
    $scope.GGuser = GG.user;
    $scope.GGsysadmin = GG.sysadmin;
    
    $scope.loadSysApplications=function(){
    	BcSysApplicationService.listApplications($scope.currentPage,$scope.keyword).then(function(res){
			console.log(res)
    		$scope.sysapplications=res.data.list;
    		$scope.totalItems=res.data.totalCount;
    		$scope.currentPage=res.data.page;
    		$scope.chooseArgs=[];
    	})
    }
	
	//弹出新增窗口
    $scope.open = function (size, type) {
        var modalInstance = $modal.open({
            templateUrl: 'tpl/systemmgmt/application/applications_form.html',
            controller: 'ModalSysApplicationsInstanceCtrl',
            size: size,
            backdrop: 'static',
            resolve: {
                items: function () {
                	var applications = {};
					$scope.items = [type,applications];
					console.log($scope.items)
                    return $scope.items;
                }
            }
        });

        modalInstance.result.then(function (items) {
            if (items[0]) {
                $scope.loadSysApplications();
            }
        }, function () {
            
        });
    };

    $scope.loadSysApplications();
	
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
    		$scope.chooseArgs=angularjs.copy($scope.sysargs);
    	}else {
    		$scope.chooseArgs=[];
    	}
    }

    //删除modal
    $scope.deleteModal=function(){
    	if($scope.chooseArgs.length!=0){
        	modalServ.showModal({}, {
        		bodyText: '       确定要删除这些记录吗?     '
    		}).then(function(result) {
    			var result = true;
    			angular.forEach($scope.chooseArgs,function(item){
    				BcSysArgsService.deleteArgs(item.id).then(function(data) {
	    				if(data.code != "10000"){
	    					result = false;
	    				}
	    			});
    			});
    			if(result){
					 toastr.success('删除成功！');
					 $scope.loadSysApplications();
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
    	if($scope.chooseArgs.length==1){
    		var chooseCreate = angular.copy($scope.chooseArgs[0]);
    		var modalInstance = $modal.open({
    			templateUrl: 'tpl/systemmgmt/application/applications_form.html',
                controller: 'ModalSysApplicationsInstanceCtrl',
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
                	 $scope.loadSysApplications();
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
