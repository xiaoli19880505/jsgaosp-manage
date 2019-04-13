'use strict';

/* Controllers */
// hospital_people_modal controller

app.controller('ModalSysApplicationsInstanceCtrl', ['$scope', '$modalInstance','$http', 'items','BcSysApplicationService',  
	function ($scope, $modalInstance,$http, items,BcSysApplicationService) {

	$scope.applications=items[1];
	$scope.flag=items[0]=="add";
	if ($scope.flag) {
        $scope.title = '新增应用申请';
    } else {
        $scope.title = '编辑应用申请';
    }
	

	$scope.addApplications=function(){
		BcSysApplicationService.createApplications($scope.applications).then(function(data){
			if(data.code == "10000"){
				 toastr.success('添加成功！');
				 $("#toast-container").css("left", "46%");
				 $modalInstance.close([true]);
			}else{
				 toastr.success('添加失败！');
				 $("#toast-container").css("left", "46%");
				 $modalInstance.close([true]);
			}
		})
	}
	
	$scope.updateApplications=function(){
		BcSysApplicationService.updateApplications($scope.applications).then(function(data){
			if(data.code == "10000"){
				toastr.success('更新应用申请成功！');
				 $("#toast-container").css("left", "46%");
	             $modalInstance.close([true]);
			}else{
				toastr.error('更新应用申请失败！');
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
