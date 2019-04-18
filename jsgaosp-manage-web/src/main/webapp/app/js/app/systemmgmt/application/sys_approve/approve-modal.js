'use strict';

/* Controllers */
// hospital_people_modal controller

app.controller('ModalSysApproveInstanceCtrl', ['$scope', '$modalInstance','$http', 'items','SysApproveService','$sessionStorage',
	function ($scope, $modalInstance,$http, items,SysApproveService,$sessionStorage) {


	$scope.isPass=true;


		//回显区划
		$scope.getAreaName=function () {
			$http.get('/common/list_area_json?pAreaNo='+$sessionStorage.user.areaNo).success(function(data){
				if (data.code=="10000") {

					$scope.area_name_show=data.data.area_name;
				}
			})
		}
        $scope.title = '系统审核';
		$scope.getAreaName();
		$scope.sysApplicant=items[1];

	$scope.approve=function(flag){

		if(flag){
			$scope.sysApplicant.status="04";
			$scope.sysApprove();
		}else{
			$scope.sysApplicant.status="03";
			$scope.isPass=false;
		}

	}

	$scope.sysApprove=function(){

		SysApproveService.sysApprove($scope.sysApplicant.status,$scope.sysApplicant.approvalOpinion,$sessionStorage.user.userId,$scope.sysApplicant.id).then(function(data){

			if(data.code == "10000"){
				 toastr.success('审核成功！');
				 $("#toast-container").css("left", "46%");
				 $modalInstance.close([true]);
			}else{
				 toastr.success('审核失败！');
				 $("#toast-container").css("left", "46%");
				 $modalInstance.close([true]);
			}
		})
	}

	//表单提交
    $scope.submitForm = function (isValid) {

		console.log($scope.sysApplicant);
    	$scope.submitted = false;
    	if(isValid){

    		$scope.sysApprove();

    	}else{
    		$scope.submitted = true;
    	}
        
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
	};


		$scope.area_name_show ="";
		$scope.showAreaList=function(){

			$scope.loadArea();
			$scope.is_focus=true;

		}

		$scope.area = [];




		var tmp=[];
		/**
		 * 加载地区树形结构
		 */
		$scope.loadArea = function() {
			$scope.doing_async = true;
			$http.get('/common/list_area_json?pAreaNo='+$sessionStorage.user.areaNo).success(function(data){
				if (data.code=="10000") {
					var arr=new Array();
					arr.push(data.data);
					$scope.area = arr;
					$scope.doing_async = false;
				}
			})
		};

		$scope.accessTypeList=[];
		//$scope.sysTypeList=[{"codeId":"0c065b92f2384ea79596511df12362a1","codeSortId":"2fe76c5e247f4af7b0caef846d425492","codeKey":"02","codeText":"微警务","status":"1"},{"codeId":"e1c1a30e8e3a471894ba57d4b6b9d4b9","codeSortId":"2fe76c5e247f4af7b0caef846d425492","codeKey":"01","codeText":"江苏公安旗舰店","status":"1"},{"codeId":"a26cd2de8ee54ea2a088a19992020626","codeSortId":"2fe76c5e247f4af7b0caef846d425492","codeKey":"99","codeText":"其它系统","status":"1"}];
		$scope.selectedAccessTypeId="";
		$scope.selectedSysTypeId="";
		/**
		 * 获得接入类型
		 */

		$scope.getAccessType=function () {
			$http.get('/common/list_code?codeSortKey=access_type').success(function(data){
				if (data.code=="10000") {
					$scope.accessTypeList=data.data;

				}
			})
		}


		/**
		 * 获得系统类型
		 */

		$scope.getSysType=function () {
			$http.get('/common/list_code?codeSortKey=sys_type').success(function(data){
				if (data.code=="10000") {
					$scope.sysTypeList=data.data;
				}
			})
		}


		$scope.getSysType();
		$scope.getAccessType();


		var tmp=$scope.selectedSysType;
		$scope.$watch('tmp', function (newValue, oldValue) {
			console.log(newValue)

		});

	}]);




