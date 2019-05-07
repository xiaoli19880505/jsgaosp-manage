'use strict';

/* Controllers */
// hospital_people_modal controller

app.controller('ModalPayConfInstanceCtrl', ['$scope', '$modalInstance','$http', 'items','PayAppConfService',  function ($scope, $modalInstance,$http, items,PayAppConfService) {


	$scope.modal_with="ul_lw";

	$scope.user=items[1];
	$scope.flag=items[0]=="add";
	// $scope.userTypes = [{id:'ADMIN',name:'公司管理员'},{id:'USER',name:' 普通用户'}];
	// $scope.isUniqueMail = true;
	// $scope.user.companyId = items[2];
	if ($scope.flag) {
        $scope.title = '新增';
        $scope.user.userType = "USER";
    } else {
        $scope.title = '编辑';
    }
	

	$scope.addPayConf=function(){
		PayAppConfService.createPayConf($scope.user).then(function(data){
			if(data.result){
				 toastr.success('添加成功！');
	             $modalInstance.close([true]);
			}else{
				toastr.error('添加失败！');
				$modalInstance.dismiss('cancel');
			}
		})
	}
	
	$scope.updatePayConf=function(){
		PayAppConfService.updatePayConf($scope.user).then(function(data){
			if(data.result){
				 toastr.success('更新成功！');
	             $modalInstance.close([true]);
			}else{
				toastr.error('更新失败！');
				$modalInstance.dismiss('cancel');
			}
		})
	}

    $scope.submitForm = function (isValid) {
    	$scope.submitted = false;
    	if(isValid){
    		if ($scope.flag) {
                $scope.addPayConf();
            } else {
            	$scope.submitted = false;
                $scope.updatePayConf();
            }
    	}else{
    		$scope.submitted = true;
    	}
        
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
    $scope.is_focus=false;
}])
;
