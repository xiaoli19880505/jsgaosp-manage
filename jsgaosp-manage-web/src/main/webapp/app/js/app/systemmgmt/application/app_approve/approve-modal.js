'use strict';

/* Controllers */
// hospital_people_modal controller

app.controller('ModalApproveCtrl', ['$scope', '$modalInstance','$http','$sessionStorage','items','ApproveService','commonServ','Upload',
	function ($scope, $modalInstance,$http,$sessionStorage, items,ApproveService,commonServ,Upload) {
		//获得新增下拉框数据
		$scope.applicationStatusList= $sessionStorage.codeList.approves_status;
		$scope.sysTypeList= $sessionStorage.codeList.sys_type;
		$scope.ywTypeList= $sessionStorage.codeList.yw_type;
		$scope.accessTypeList= $sessionStorage.codeList.access_type;
		$scope.xzTypeList= $sessionStorage.codeList.xz_type;
		$scope.serverTypeList= $sessionStorage.codeList.server_type;
		$scope.blTypeList= $sessionStorage.codeList.bl_type;
		
		$scope.application=items[1];
		$scope.flag=items[0]=="add";
		if ($scope.flag) {
			$scope.title = '新增应用';
			$scope.application.approval_status="" ;
		} else if(items[0] =="audit"){
			$scope.title = '审核应用';
		}else {
			$scope.title = '编辑应用';
		}

		
		//取消按钮
		$scope.cancel = function () {
	        $modalInstance.dismiss('cancel');
		};
		
		
		/*//表单提交
		
		$scope.submitForm = function (isValid) {
			ApproveService.updateVersionApplication($scope.approve).then(function(data){
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

		};*/
		
		//更新
		$scope.updateApprove=function(){
			ApproveService.updateApprove($scope.approve).then(function(data){
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
		
		
		
	    
	}]);
