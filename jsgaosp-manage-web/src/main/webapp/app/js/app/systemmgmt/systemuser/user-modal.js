'use strict';

/* Controllers */
// hospital_people_modal controller

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
//		console.log(_branch);
		$scope.area_no = _branch.area_no;

		$scope.flag=1;




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
		$scope.is_focus=false;
		//$scope.area_name_show =angular.copy($scope.areaItem.area_name);
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

		$http.get('/area/list_area').success(function(data){
			if (data.code=="10000") {
				tmp=data.data;
				$scope.area = tmp;
				$scope.doing_async = false;
			}
		})
	};


}])
;
