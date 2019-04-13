'use strict';

/* Controllers */
// hospital_people_modal controller

app.controller('ModalSysApplicationsInstanceCtrl', ['$scope', '$modalInstance','$http', 'items','BcSysApplicationService',  function ($scope, $modalInstance,$http, items,BcSysApplicationService) {

	$scope.applications=items[1];
	$scope.flag=items[0]=="add";
	if ($scope.flag) {
        $scope.title = '新增系统参数';
    } else {
        $scope.title = '编辑系统参数';
    }
	

	$scope.addApplications=function(){
		BcSysApplicationService.createApplications($scope.applications).then(function(data){
			if(data.code == "10000"){
				 toastr.success('添加系统参数成功！');
	             $modalInstance.close([true]);
			}else{
				toastr.error('添加系统参数失败！');
				$modalInstance.dismiss('cancel');
			}
		})
	}
	
	$scope.updateApplications=function(){
		BcSysApplicationService.updateApplications($scope.applications).then(function(data){
			if(data.code == "10000"){
				 toastr.success('更新系统参数成功！');
	             $modalInstance.close([true]);
			}else{
				toastr.error('更新系统参数失败！');
				$modalInstance.dismiss('cancel');
			}
		})
	}

	//表单提交
    $scope.submitForm = function (isValid) {
		console.log("isValid:"+isValid);
    	$scope.submitted = false;
    	if(isValid){
    		if ($scope.flag) {
                $scope.addApplications();
            } else {
            	$scope.submitted = false;
                $scope.updateApplications();
            }
    	}else{
    		$scope.submitted = true;
    	}
        
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
	};
	
}]);
