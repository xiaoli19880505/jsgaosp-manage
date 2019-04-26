'use strict';

/* Controllers */
// hospital_people_modal controller

app.controller('ModalSysApplicationsInstanceCtrl', ['$scope', '$modalInstance','$http','$sessionStorage','items','BcSysApplicationService','commonServ',
	function ($scope, $modalInstance,$http,$sessionStorage, items,BcSysApplicationService,commonServ) {

		$scope.applications=items[1];
		$scope.flag=items[0]=="add";
		if ($scope.flag) {
			$scope.title = '新增应用申报';
		} else {
			$scope.title = '编辑应用申报';
		}

		commonServ.getCodeListByColName("xz_type").then(function(data){
            $scope.xzTypeList=data.data;
        });

		commonServ.getCodeListByColName("yw_type").then(function(data){
			$scope.ywTypeList=data.data;
		});

		$scope.xzTypeList="";
		$scope.ywTypeList="";


		$scope.addApplications=function(){
			BcSysApplicationService.createApplications($scope.applications,$sessionStorage.user.userId).then(function(data){
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
			BcSysApplicationService.updateApplications($scope.applications,$sessionStorage.user).then(function(data){
				if(data.code == "10000"){
					toastr.success('更新应用申报成功！');
					$("#toast-container").css("left", "46%");
					$modalInstance.close([true]);
				}else{
					toastr.error('更新应用申报失败！');
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
			$scope.sysApplicant.areaNo=_branch.area_no;
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


	}]);
