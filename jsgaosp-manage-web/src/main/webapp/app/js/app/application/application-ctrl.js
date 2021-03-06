'use strict';

app.controller('ApplicationController',['$scope','$http','$state','$modal','$timeout','modalServ','ApplicationService','$sessionStorage',
	function($scope,$http,$state,$modal,$timeout,modalServ,ApplicationService,$sessionStorage){
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
		ApplicationService.listApplications($scope.currentPage,$scope.orgNo,$scope.keyword).then(function(res){
            console.log(res)
            $scope.applicationList=res.data.list;
            $scope.totalItems=res.data.totalCount;
            $scope.currentPage=res.data.page;
            $scope.chooseArgs=[];
        })
    }
	
	
	 //搜索
    $scope.searchApp=function(){
        $scope.loadApplications();
    }

    $scope.pageChanged = function () {
        $scope.loadApplications();
    };
	
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
    //版本管理
    $scope.rollbackVersion=function(){
    	if($scope.chooseArgs.length==1){
    		if($scope.chooseArgs[0].approval_status =='01'){
    			var chooseCreate = $scope.chooseArgs[0];
            	var modalInstance = $modal.open({
                templateUrl: 'tpl/application/application_list_form.html',
                controller: 'ModalVersionCtrl',
                backdrop: 'static',
                resolve: {
                    items: function () {
    					var applications = {};
    					applications=$scope.verapps;
    					$scope.items =['rollback',chooseCreate];
                        return $scope.items;
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
        			message: '请选择一个审核通过的应用！',  
        			callback: function() {  
        			},  
        			title: "提示",  
        		});
    		}
    		

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
    //版本更新
    $scope.updateVersion=function(){
	if($scope.chooseArgs.length==1){
		if($scope.chooseArgs[0].approval_status =='01'){
			var chooseCreate = $scope.chooseArgs[0];
	    	var modalInstance = $modal.open({
	        templateUrl: 'tpl/application/application_update_form.html',
	        controller: 'ModalUpdateVersionCtrl',
	        backdrop: 'static',
	        resolve: {
	            items: function () {
					var applications = {};
					applications=$scope.verapps;
					$scope.items =['rollback',chooseCreate];
	                return $scope.items;
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
    			message: '请选择一个审核通过的应用！',  
    			callback: function() {  
    			},  
    			title: "提示",  
    		}); 
    	}
		

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
   
    //删除
    $scope.deleteModal=function(){
    	if($scope.chooseArgs.length!=0){
        	modalServ.showModal({}, {
        		bodyText: '       确定要删除这些记录吗?     '
    		}).then(function(result) {
    			var result = true;
    			angular.forEach($scope.chooseArgs,function(item){
    				ApplicationService.deleteApplication(item.id).then(function(data) {
	    				if(data.code != "10000"){
	    					result = false;
	    				}
	    			});
    			});
    			if(result){
					 toastr.success('删除成功！');
					 $scope.loadApplications();
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
    //////
    //审核
    $scope.audit=function(){
    	if($scope.chooseArgs.length!=0){
    		if($scope.chooseArgs[0].approval_status =='00'){
    			var chooseCreate = $scope.chooseArgs[0];
        		var modalInstance = $modal.open({
        			  templateUrl: 'tpl/application/application_audit_form.html',
        	            controller: 'ModalApplicationsCtrl',
                    size: '',
                    backdrop: 'static',
                    resolve: {
                        items: function () {
                               return ['audit',chooseCreate];
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
    				message: '请选择待审核的数据！',  
    				callback: function() {  
    				},  
    				title: "提示",  
    			}); 
    		}
    		
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
    $scope.loadApplications();
    
    
    //--禁用/启用
    $scope.updateWorkStatus=function(info_id,org_id,app_id,sys_type,working_status,approval_status){
    	if(approval_status =='01'){
    		ApplicationService.disableApplication(info_id,org_id,app_id,sys_type,working_status).then(function(res){
        		if(data.code != "10000"){
    				result = false;
    			}
            })
            $scope.loadApplications();
    	}else{
    		bootbox.alert({  
				buttons: {  
					ok: {  
						label: '确定',  
						className: 'btn-info btn-dark'  
					}  
				},  
				message: '请选择审核通过的数据！',  
				callback: function() {  
				},  
				title: "提示",  
			});
    	}
    }
    
    
   

}])