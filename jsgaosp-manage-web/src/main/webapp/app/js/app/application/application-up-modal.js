'use strict';

/* Controllers */
// hospital_people_modal controller

app.controller('ModalUpdateVersionCtrl', ['$scope', '$modalInstance','$http','$sessionStorage','items','ApplicationService','commonServ',
	function ($scope, $modalInstance,$http,$sessionStorage, items,ApplicationService,commonServ) {
	 
	$scope.applicationStatusList= $sessionStorage.codeList.approves_status;
	$scope.sysTypeList= $sessionStorage.codeList.sys_type;
	$scope.ywTypeList= $sessionStorage.codeList.yw_type;
	$scope.accessTypeList= $sessionStorage.codeList.access_type;
	$scope.xzTypeList= $sessionStorage.codeList.xz_type;
	$scope.serverTypeList= $sessionStorage.codeList.server_type;
	$scope.blTypeList= $sessionStorage.codeList.bl_type;
	
		$scope.application=items[1];
		$scope.title = '版本管理';
		//取消按钮
		$scope.cancel = function () {
	        $modalInstance.dismiss('cancel');
		};
		
		
//		//表单提交
		$scope.submitForm = function (isValid) {
			ApplicationService.updateVersionApplication($scope.application).then(function(data){
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

		};
		
		//更新
			
		
	    
	}]);
