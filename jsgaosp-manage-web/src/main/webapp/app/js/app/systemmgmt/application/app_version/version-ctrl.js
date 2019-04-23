'use strict';

/* Controllers */
// hospital_people controller
app.controller('SysVersionController',
		['$scope', '$http', '$state', '$modal', '$stateParams', '$timeout', 'modalServ','BcSysVersionService', 'GG','$sessionStorage',
           function ($scope, $http, $state, $modal, $stateParams,$timeout, modalServ,BcSysVersionService,GG,$sessionStorage) {
	
	$scope.totalItems = 100;
    $scope.currentPage = 1;
    $scope.maxSize = 5;
    $scope.keyword="";
    
    $scope.GGuser = GG.user;
    $scope.GGsysadmin = GG.sysadmin;
    
    $scope.loadSysVersion=function(){
    	BcSysVersionService.listVersion($scope.currentPage,$scope.keyword,$sessionStorage.user.userId).then(function(res){
			console.log(res)
    		$scope.sysversion=res.data.list;
    		$scope.totalItems=res.data.totalCount;
    		$scope.currentPage=res.data.page;
    		$scope.chooseArgs=[];
    	})
    }
  
 
	//弹出新增窗口
    $scope.open = function () {
    	$scope.sysversions={};
    	$scope.sysversions.Id = $scope.sysversion[0].id;
    	$scope.sysversions.appName = $scope.sysversion[0].appName;
    	$scope.sysversions.sysId = $scope.sysversion[0].sysId;
    	$scope.sysversions.areaNo = $scope.sysversion[0].areaNo;
    	$scope.sysversions.sysType = $scope.sysversion[0].sysType;
    	$scope.sysversions.version = $scope.sysversion[0].version;
    	BcSysVersionService.listHisVersion($scope.currentPage,$scope.sysversions).then(function(res){
			console.log(res)
    		$scope.sysversion=res.data.list;
    		$scope.totalItems=res.data.totalCount;
    		$scope.currentPage=res.data.page;
    		$scope.chooseArgs=[];
    	})
        var modalInstance = $modal.open({
            templateUrl: 'tpl/applicationmgmt/app_version/app_version_form.html',
            controller: 'ModalSysVersionCtrl',
            backdrop: 'static',
            resolve: {
                items: function () {
					var applications = {};
					applications=$scope.sysversion;
					 $scope.title = '版本管理';
					$scope.items = [applications];
                    return $scope.items;
                }
            }
       
        });

    	
        /*modalInstance.result.then(function (items) {
            if (items[0]) {
                $scope.loadSysVersion();
            }
        }, function () {
            
        });*/
    };

    $scope.loadSysVersion();
	

  //删除数据字典
	$scope.deleteApplication=function(Id,appName){
    	modalServ.showModal({}, {
			bodyText: "确定要删除应用申请【"+appName+"】?"
		}).then(function(result) {
			BcSysApplicationService.deleteApplications(Id).then(function(data) {
				if(data.code == "10000"){
					 toastr.success('删除成功！');
					 $("#toast-container").css("left", "46%");
					 $scope.loadSysVersion();
				}else{
					toastr.error('删除失败！');
					$("#toast-container").css("left", "46%");
				}
			});
		});
    
    }
	//搜索
    $scope.search=function(){
    	$scope.loadSysVersion();
    }

    $scope.pageChanged = function () {
		$scope.loadSysVersion();
	};
	//获取状态
	$scope.sysApplicantStatusList=[];
	   $scope.getAccessType=function () {
		   $http.get('/common/list_code?codeSortKey=app_status').success(function(data){
			   if (data.code=="10000") {
				   $scope.sysApplicantStatusList=data.data;

			   }
		   })
	   }
	   $scope.getAccessType();

	   $scope.$watch('$scope.applications.status', function (newValue, oldValue) {
		   $scope.loadSysVersion();

	   });
	   
	   //系统类型
	   $scope.sysApplicantTypeList=[];
	   $scope.getAccessType=function () {
		   $http.get('/common/list_code?codeSortKey=sys_type').success(function(data){
			   if (data.code=="10000") {
				   $scope.sysApplicantTypeList=data.data;

			   }
		   })
	   }
	   $scope.getAccessType();

	   $scope.$watch('$scope.applications.type', function (newValue, oldValue) {
		   $scope.loadSysVersion();

	   });


	/*//修改
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
                	 $scope.loadSysVersion();
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
    }*/
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

