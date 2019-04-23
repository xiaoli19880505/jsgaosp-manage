'use strict';

/* Controllers */
// hospital_people_modal controller

app.controller('ModalSysVersionCtrl', ['$scope', '$modalInstance','$http','$sessionStorage','items','BcSysVersionService',  
	function ($scope, $modalInstance,$http,$sessionStorage, items,BcSysVersionService) {

	$scope.sysversion=items[0];

	

	$scope.addApplications=function(){
		BcSysVersionService.createApplications($scope.applications,$sessionStorage.user.userId).then(function(data){
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
		BcSysVersionService.updateApplications($scope.applications,$sessionStorage.user).then(function(data){
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
    
    $scope.chooseVersion="";
    $scope.choose = function(chk,item,index){
    	item.indexs = index;
    	if(chk){
    		$scope.chooseVersion=item;
    	}else if(!chk){
    		 $scope.chooseVersion="";
    	}
    }
    $scope.submit = function (version) {
		BcSysVersionService.updateVersion(version).then(function(data){
			
			if(data.code == "10000"){
				 toastr.success('更新成功！');
				 $("#toast-container").css("left", "46%");
				 $modalInstance.close([true]);
			}else{
				toastr.error('更新失败！');
				$("#toast-container").css("left", "46%");
			}
		})
	}

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
	};
	
}]);
