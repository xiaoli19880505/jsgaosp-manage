'use strict';

/* Controllers */
// hospital_people controller

app.controller('SysApplicantController',
		['$scope', '$http', '$state', '$modal', '$stateParams', '$timeout', 'modalServ','ThirdPartySysService', 'GG','$sessionStorage',
           function ($scope, $http, $state, $modal, $stateParams,$timeout, modalServ,ThirdPartySysService,GG,$sessionStorage) {
	
	$scope.totalItems = 100;
    $scope.currentPage = 1;
    $scope.maxSize = 5;
    $scope.sysNameKey="";
    $scope.sysApplicantStatusKey="";
    $scope.sysTypeKey="";

    $scope.GGuser = GG.user;
    $scope.GGsysadmin = GG.sysadmin;
    
    $scope.loadSysApplications=function(){
		ThirdPartySysService.listApplications($scope.currentPage,$scope.sysNameKey,$scope.sysApplicantStatusKey,$sessionStorage.user.userId).then(function(res){
    		$scope.sysapplicants=res.data.list;
    		$scope.totalItems=res.data.totalCount;
    		$scope.currentPage=res.data.page;
    		$scope.chooseArgs=[];
    	})
    }
	
	//弹出新增窗口
    $scope.open = function (size, type,index) {
        var modalInstance = $modal.open({
            templateUrl: 'tpl/applicationmgmt/sys_applicant/sys_applicant_form.html',
            controller: 'ModalSysApplicantInstanceCtrl',
            size: size,
            backdrop: 'static',
            resolve: {
                items: function () {
					var applicant = {};
					if(index != null){
						applicant = $scope.sysapplicants[index];
					}
					$scope.items = [type,applicant];
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
	

    	



	$scope.deleteApplication=function(Id,sysName){
    	modalServ.showModal({}, {
			bodyText: "确定要删除系统申报【"+sysName+"】?"
		}).then(function(result) {
			ThirdPartySysService.deleteApplications(Id).then(function(data) {
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

   /**
	* 获得业务状态列表
	*/
	$scope.sysApplicantStatusList=$sessionStorage.codeList.sys_status;
   // $scope.getAccessType=function () {
	//    $http.get('/common/list_code?codeSortKey=app_status').success(function(data){
	// 	   if (data.code=="10000") {
	// 		   $scope.sysApplicantStatusList=data.data;
	// 		   console.log($scope.sysApplicantStatusList);
	// 	   }
	//    })
   // }
   // $scope.getAccessType();




   $scope.$watch('$scope.sysApplicantStatusKey', function (newValue, oldValue) {
	   $scope.loadSysApplications();

   });
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




