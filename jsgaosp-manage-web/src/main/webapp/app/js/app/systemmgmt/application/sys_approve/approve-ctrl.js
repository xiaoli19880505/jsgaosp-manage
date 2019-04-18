'use strict';

/* Controllers */
// hospital_people controller

app.controller('SysApproveController',
		['$scope', '$http', '$state', '$modal', '$stateParams', '$timeout', 'modalServ','SysApproveService', 'GG','$sessionStorage',
           function ($scope, $http, $state, $modal, $stateParams,$timeout, modalServ,SysApproveService,GG,$sessionStorage) {
	
	$scope.totalItems = 100;
    $scope.currentPage = 1;
    $scope.maxSize = 5;
    $scope.sysNameKey="";
    $scope.sysApplicantStatusKey="";
    
    $scope.GGuser = GG.user;
    $scope.GGsysadmin = GG.sysadmin;
    
    $scope.loadSysApplications=function(){
        SysApproveService.listApplicantforApprova($scope.currentPage,$scope.sysNameKey,$scope.sysApplicantStatusKey).then(function(res){
    		$scope.sysapplicants=res.data.list;
    		$scope.totalItems=res.data.totalCount;
    		$scope.currentPage=res.data.page;
    		$scope.chooseArgs=[];
    	})
    }
	
	//弹出新增窗口
    $scope.open = function (size, type,index) {
        var modalInstance = $modal.open({
            templateUrl: 'tpl/applicationmgmt/sys_approve/sys_approve_form.html',
            controller: 'ModalSysApproveInstanceCtrl',
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
	$scope.sysApplicantStatusList=[];
   $scope.getAccessType=function () {
	   $http.get('/common/list_code?codeSortKey=app_status').success(function(data){
		   if (data.code=="10000") {
			   $scope.sysApplicantStatusList=data.data;

		   }
	   })
   }
   $scope.getAccessType();

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




