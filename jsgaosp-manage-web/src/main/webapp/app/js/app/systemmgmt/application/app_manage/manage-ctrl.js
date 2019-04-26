'use strict';

/* Controllers */
// hospital_people controller
app.controller('SystemSysApplicationManagerController',
		['$scope', '$http', '$state', '$modal', '$stateParams', '$timeout', 'modalServ','BcSysApplicationService', 'GG','$sessionStorage',
           function ($scope, $http, $state, $modal, $stateParams,$timeout, modalServ,BcSysApplicationService,GG,$sessionStorage) {
	
	$scope.totalItems = 100;
    $scope.currentPage = 1;
    $scope.maxSize = 5;
    $scope.keyword="";
    
    $scope.GGuser = GG.user;
    $scope.GGsysadmin = GG.sysadmin;
    
    $scope.loadSysApplications=function(){
    	BcSysApplicationService.listApplications($scope.currentPage,$scope.keyword,$sessionStorage.user.userId).then(function(res){
			console.log(res)
    		$scope.sysapplications=res.data.list;
    		$scope.totalItems=res.data.totalCount;
    		$scope.currentPage=res.data.page;
    		$scope.chooseArgs=[];
    	})
    }
	
	//弹出新增窗口
    $scope.open = function (size, type,index) {
        var modalInstance = $modal.open({
            templateUrl: 'tpl/systemmgmt/application/applications_form.html',
            controller: 'ModalSysApplicationsInstanceCtrl',
            size: size,
            backdrop: 'static',
            resolve: {
                items: function () {
					var applications = {};
					if(index != null){
						applications = $scope.sysapplications[index];
					}
					$scope.items = [type,applications];
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

  //删除数据字典
	$scope.deleteApplication=function(Id,appName){
    	modalServ.showModal({}, {
			bodyText: "确定要删除应用申请【"+appName+"】?"
		}).then(function(result) {
			BcSysApplicationService.deleteApplications(Id).then(function(data) {
				if(data.code == "10000"){
					 toastr.success('删除成功！');
					 $("#toast-container").css("left", "46%");
					 $scope.loadSysApplications();
				}else{
					toastr.error('删除失败！');
					$("#toast-container").css("left", "46%");
				}
			});
		});
    
    }
	//搜索
    $scope.search=function(){
    	$scope.loadSysApplications();
    }

    $scope.pageChanged = function () {
		$scope.loadSysApplications();
	};



	   
	   //系统类型
	   $scope.sysApplicantTypeList=[];
	   $scope.getsysTypeList=function () {
		   $http.get('/common/list_code?codeSortKey=sys_type').success(function(data){
			   if (data.code=="10000") {
				   $scope.sysList=data.data;

			   }
		   })
	   }



	   $scope.getsysTypeList();

	   //对应地区的系统列表
	   // $scope.sysApplicantTypeList=[];
	   // $scope.getsysList=function () {
		//    $http.get('/common/list_code?codeSortKey=sys_type').success(function(data){
		// 	   if (data.code=="10000") {
		// 		   $scope.sysList=data.data;
	   //
		// 	   }
		//    })
	   // }
	   // $scope.getsysList();




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

