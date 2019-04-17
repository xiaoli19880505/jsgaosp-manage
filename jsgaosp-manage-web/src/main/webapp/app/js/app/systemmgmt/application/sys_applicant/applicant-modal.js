'use strict';

/* Controllers */
// hospital_people_modal controller

app.controller('ModalSysApplicantInstanceCtrl', ['$scope', '$modalInstance','$http', 'items','ThirdPartySysService','$sessionStorage',
	function ($scope, $modalInstance,$http, items,ThirdPartySysService,$sessionStorage) {

	$scope.sysApplicant={
		id:"",
		sysName:"",
		areaNo:"",
		sysType:"",
		sysUrl:"",
		memo:"",
		qrCode:"",
		status:"",
		approvalOpinion:"",
		createDate:"",
		createUserId:"",
		approvalDate:"",
		approvalUserId:"",
		accessType:""
	}

	$scope.applications=items[1];
	$scope.flag=items[0]=="add";
	if ($scope.flag) {
        $scope.title = '新增系统申报';
    } else {
        $scope.title = '编辑系统申报';
    }
	

	$scope.addApplications=function(){
		ThirdPartySysService.createApplications($scope.applications).then(function(data){
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
		ThirdPartySysService.updateApplications($scope.applications).then(function(data){
			if(data.code == "10000"){
				toastr.success('更新系统申报成功！');
				 $("#toast-container").css("left", "46%");
	             $modalInstance.close([true]);
			}else{
				toastr.error('更新系统申报失败！');
				$modalInstance.dismiss('cancel');
			}
		})
	}

	//表单提交
    $scope.submitForm = function (isValid) {

	    alert($scope.selectedSysTypeId);
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

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
	};



		$scope.is_focus=false;

		//以下用于操作区划数结构


		//选中的区划的对象属性
		$scope.areaItem= {
			"area_name":"",
			"area_no":"",
			"p_area_no":"",
			"id":"",
			"status":"",
			"children":""
		};

		var _branch = "";
		$scope.keyword="";

		$scope.getArea = function(branch) {
			_branch = branch;

			$scope.area_no = _branch.area_no;






			if (branch.area_no!= 0) {
				$scope.output=_branch.area_name;
				$scope.areaItem.area_name=_branch.area_name;
				$scope.areaItem.area_no=_branch.area_no;
				$scope.areaItem.id=_branch.id;
				$scope.areaItem.status=_branch.status;
				$scope.areaItem.p_area_no=_branch.p_area_no;
				$scope.areaItem.children=_branch.children;
				if(tree.get_parent_branch(branch)!=null){

					$scope.p_area_no = tree.get_parent_branch(branch).area_no;

				}_branch


				$scope.parentId=branch.area_no;


			}
		};


		$scope.area_name_show ="";

		$scope.ensureArea=function(){
			if($scope.areaItem.area_name==""){
				toastr.error('请选择区划！');
				return;
			}
			$scope.is_focus=false;
			$scope.area_name_show =$scope.areaItem.area_name;
		}

		$scope.showAreaList=function(){

			$scope.loadArea();
			$scope.is_focus=true;

		}

		$scope.area = [];
		var tree = $scope.my_tree = {};

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
