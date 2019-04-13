'use strict';

/* Controllers */
// hospital_people_modal controller

app.controller('ModalCodeSortInstanceCtrl', ['$scope', '$modalInstance','$http', 'items','BcCodeSortService',  function ($scope, $modalInstance,$http, items,BcSysArgsService) {

	$scope.codesort=items[1];
	$scope.flag=items[0]=="add";
	if ($scope.flag) {
        $scope.title = '新增系统参数';
    } else {
        $scope.title = '编辑系统参数';
    }
	

	$scope.addCodeSort=function(){
		BcCodeSortService.createCodeSort($scope.codesort).then(function(data){
			if(data.code == "10000"){
				 toastr.success('添加成功！');
	             $modalInstance.close([true]);
			}else{
				toastr.error('添加失败！');
				$modalInstance.dismiss('cancel');
			}
		})
	}
	
	$scope.updateCodeSort=function(){
		BcCodeSortService.updateCodeSort($scope.codesort).then(function(data){
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
                $scope.addCodeSort();
            } else {
            	$scope.submitted = false;
                $scope.updateCodeSort();
            }
    	}else{
    		$scope.submitted = true;
    	}
        
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
	};
	
}]);
