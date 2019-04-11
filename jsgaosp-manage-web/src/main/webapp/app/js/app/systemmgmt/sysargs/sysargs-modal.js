'use strict';

/* Controllers */
// hospital_people_modal controller
/*
app.controller('ModalSystemUserInstanceCtrl', ['$scope', '$modalInstance','$http', 'items','BcUserService',  function ($scope, $modalInstance,$http, items,BcUserService) {

	$scope.user=items[1];
	$scope.flag=items[0]=="add";
	$scope.userTypes = [{id:'ADMIN',name:'公司管理员'},{id:'USER',name:' 普通用户'}];
	$scope.isUniqueMail = true;
	$scope.user.companyId = items[2];
	if ($scope.flag) {
        $scope.title = '新增用户';
        $scope.user.userType = "USER";
    } else {
        $scope.title = '编辑用户';
    }
	

	$scope.addUser=function(){
		BcUserService.createUser($scope.user).then(function(data){
			if(data.result){
				 toastr.success('添加用户成功！');
	             $modalInstance.close([true]);
			}else{
				toastr.error('添加用户失败！');
				$modalInstance.dismiss('cancel');
			}
		})
	}
	
	$scope.updateUser=function(){
		BcUserService.updateUser($scope.user).then(function(data){
			if(data.result){
				 toastr.success('更新用户成功！');
	             $modalInstance.close([true]);
			}else{
				toastr.error('更新用户失败！');
				$modalInstance.dismiss('cancel');
			}
		})
	}

    $scope.submitForm = function (isValid) {
    	$scope.submitted = false;
    	if(isValid){
    		if ($scope.flag) {
                $scope.addUser();
            } else {
            	$scope.submitted = false;
                $scope.updateUser();
            }
    	}else{
    		$scope.submitted = true;
    	}
        
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
    
    //验证邮箱唯一性
    $scope.checkEmail = function(){
    	if($scope.userEmail != ""){
    		$http.get('/system/bcuser/checkEmail',{params: {
    			email:$scope.user.userEmail
			}}).success(function(data){
				if(data.count >0){
					$scope.isUniqueMail = false;
				}else{
					$scope.isUniqueMail = true;
				}
			})
    	}
    	
    }
}])
;*/
