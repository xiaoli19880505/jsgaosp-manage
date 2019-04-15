'use strict';

/* Controllers */
// hospital_people_modal controller

app.controller('ModalApproveCtrl', ['$scope', '$modalInstance','$http', 'items','BcSysApproveService',  
	function ($scope, $modalInstance,$http, items,BcSysApproveService) {

	$scope.approves=items[1];
	$scope.flag=items[0]=="add";
	if ($scope.flag) {
        $scope.title = '新增应用申请';
    } else {
        $scope.title = '编辑应用申请';
    }
	

	
	$scope.updateApprove=function(){
		BcSysApplicationService.updateApprove($scope.approve).then(function(data){
			if(data.code == "10000"){
				toastr.success('审批成功！');
				 $("#toast-container").css("left", "46%");
	             $modalInstance.close([true]);
			}else{
				toastr.error('审批失败！');
				$modalInstance.dismiss('cancel');
			}
		})
	}

	//表单提交
    $scope.submitForm = function (isValid) {
		console.log("isValid:"+isValid);
    	$scope.submitted = false;
    	if(isValid){
            	$scope.submitted = false;
                $scope.updateApprove();
    	}else{
    		$scope.submitted = true;
    	}
        
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
	};
	
}]);
