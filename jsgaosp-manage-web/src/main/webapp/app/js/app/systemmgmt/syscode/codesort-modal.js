'use strict';

/* Controllers */
// hospital_people_modal controller

app.controller('ModalCodeSortInstanceCtrl', ['$scope', '$modalInstance','$http', 'items','BcCodeSortService',
	function ($scope, $modalInstance,$http, items,BcCodeSortService) {



	$scope.codesort=items[1];
	$scope.flag=items[0]=="add";
	if ($scope.flag) {
        $scope.title = '新增数据字典';
    } else {
        $scope.title = '编辑数据字典';
    }
	

	$scope.addCodeSort=function(){
		BcCodeSortService.createCodeSort($scope.codesort).then(function(data){
			if(data.code == "10000"){
				 toastr.success('添加成功！');
				 $("#toast-container").css("left", "46%");
				 $modalInstance.close([true]);

			}else{
				toastr.error('添加失败！');
				$("#toast-container").css("left", "46%");
				$modalInstance.dismiss('cancel');
			}
		})
	}
	
	$scope.updateCodeSort=function(){
		BcCodeSortService.updateCodeSort($scope.codesort).then(function(data){

			console.log(toastr)
			if(data.code == "10000"){
				 toastr.success('更新数据字典成功！');
				 $("#toast-container").css("left", "46%");
	             $modalInstance.close([true]);
			}else{
				toastr.error('更新数据字典失败！');
				$("#toast-container").css("left", "46%");
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

/**
 * 加载和编辑字典明细
 */
app.controller('ModalCodeInstanceCtrl', ['$scope', '$modalInstance','$http', 'items','BcCodeService',  
	function ($scope, $modalInstance,$http, items,BcCodeService) {

	$scope.codedetail=items[1];
	$scope.flag=items[0]=="add";
	if ($scope.flag) {
        $scope.title = '新增';
    } else {
        $scope.title = '编辑';
    }
	

	$scope.addCode=function(){
		BcCodeService.createCode($scope.codedetail).then(function(data){
			if(data.code == "10000"){
				 toastr.success('添加成功！');
				 $("#toast-container").css("left", "46%");
				 $modalInstance.close([true]);

			}else{
				toastr.error('添加失败！');
				$("#toast-container").css("left", "46%");
				$modalInstance.dismiss('cancel');
			}
		})
	}
	
	$scope.updateCode=function(){
		BcCodeService.updateCode($scope.codedetail).then(function(data){
			if(data.code == "10000"){
				 toastr.success('更新成功！');
				 $("#toast-container").css("left", "46%");
	             $modalInstance.close([true]);
			}else{
				toastr.error('更新失败！');
				$("#toast-container").css("left", "46%");
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
                $scope.addCode();
            } else {
            	$scope.submitted = false;
                $scope.updateCode();
            }
    	}else{
    		$scope.submitted = true;
    	}
        
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
	};
	
}]);
