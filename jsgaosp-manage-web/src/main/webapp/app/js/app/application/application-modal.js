'use strict';

/* Controllers */
// hospital_people_modal controller

app.controller('ModalApplicationsCtrl', ['$scope', '$modalInstance','$http','$sessionStorage','items','ApplicationService','commonServ',
	function ($scope, $modalInstance,$http,$sessionStorage, items,ApplicationService,commonServ) {
		//获得应用审核状态
		$scope.applicationStatusList= $sessionStorage.codeList.approves_status;
		
		
		$scope.application=items[1];
		$scope.flag=items[0]=="add";
		if ($scope.flag) {
			$scope.title = '新增应用';
			$scope.application.approval_status="" ;
		} else if(items[0] =="audit"){
			$scope.title = '审核应用';
		}
		else {
			$scope.title = '编辑应用';
		}

		
		//取消按钮
		$scope.cancel = function () {
	        $modalInstance.dismiss('cancel');
		};
		

		
		//表单提交
		$scope.submitForm = function (isValid) {
			console.log("isValid:"+isValid);
			$scope.submitted = false;
			if(isValid){
				if ($scope.flag) {
					$scope.addApplication();
				} else {
					$scope.submitted = false;
					$scope.updateApplication();
				}
			}else{
				$scope.submitted = true;
			}

		};
		//添加
		$scope.addApplication=function(){
			ApplicationService.addApplication($scope.application,items[2]).then(function(data){
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
		};
		//更新
		$scope.updateApplication=function(){
			ApplicationService.updateApplication($scope.application).then(function(data){
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
		
	

	/*	//审核
		$scope.audit=function(){
			ApplicationService.audit($scope.application).then(function(data){
				if(data.code == "10000"){
					 toastr.success('审核成功！');
					 $("#toast-container").css("left", "46%");
		             $modalInstance.close([true]);
				}else{
					toastr.error('审核失败！');
					$("#toast-container").css("left", "46%");
					$modalInstance.dismiss('cancel');
				}
			})
		}*/
	    
	}]);
