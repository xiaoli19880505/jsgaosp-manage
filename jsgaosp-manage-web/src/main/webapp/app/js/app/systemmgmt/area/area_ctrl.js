'use strict';

app.controller('AreaController',function($scope,$http,$state,$timeout,modalServ,AreaService){
	/*
	 * 初始化
	 */

	//选中的区划或者要新添区划的对象属性
	$scope.areaItem= {
		"areaName":"",
		"areaNo":"",
		"pareaId":"",
		"id":"",
		"status":""

	};

	$scope.totalItems = 1;
    $scope.currentPage = 1;
	$scope.getAreaInfoById=function(){
		AreaService.getAreaInfo($scope.app.user.companyId).then(function(data){
			var arr = new Array();
			arr.push(data);
			console.log(arr);
			$scope.area=arr;
		})
	}

	$scope.getAreaInfo=function(){
		AreaService.loadArea().then(function(data){
			var arr = new Array();
			arr.push(data);
			console.log(arr);
			console.log(arr.length)
			$scope.area=data;
		})
	}
	$scope.submitForm=function(isValid){
		$scope.submitted = false;
		if(isValid){
			var data = {
				companyId : $scope.app.user.companyId,
				name : $scope.company.name,
				description : $scope.company.description
			};
			CompanyService.saveCompany(data).then(function(data){
				if(data.result){
					toastr.success('保存成功！');
				}
			});
		}else{
			$scope.submitted = true;
		}
	}

	//获取区划列表
	//$scope.getAreaInfo();

	var _branch = "";
	$scope.keyword="";

	$scope.getArea = function(branch) {
		_branch = branch;
//		console.log(_branch);
		$scope.orgId = _branch.orgId;
		if (branch.orgId!= -1) {
			$scope.output=_branch.label;

			$scope.areaItem.areaName=_branch.label;
			$scope.areaItem.areaNo=_branch.orgId;
			if(tree.get_parent_branch(branch)!=null){
				$scope.pId = tree.get_parent_branch(branch).orgId;
				$scope.pCode=tree.get_parent_branch(branch).code;
				$scope._pCode=$scope.pCode+"_";
			}_branch
			// $scope.getAreaInfoById();
		}
	};
	$scope.orgList = [];
	var tree = $scope.my_tree = {};

	/**
	 * 加载地区树形结构
	 */
	$scope.loadArea = function() {
		$scope.doing_async = true;
		AreaService.loadArea();
		// var parent=[];
		// var areaList=AreaService.loadArea();
		// for(var len=areaList.length;i--; ){
		// 	var item=areaList[len];
		// 	if(item.)
		// }
		var tmp = [{
			"label":"江苏省",
			"orgId":"-1",
			"pId":"-1",
			"code":"",
			"address":"",
			"children":[{"label":"南京市","children":[
					{
						"orgId":"11",
						"pId":"0",
						"label":"栖霞区",
						"code":""
					},{
						"orgId":"12",
						"pId":"0",
						"label":"玄武区",
						"code":""
					},{
						"orgId":"13",
						"pId":"0",
						"label":"鼓楼区",
						"code":""
					}
				],"orgId":"0","pId":"-1","code":""},
				{"label":"无锡市","children":[],"orgId":"1","pId":"-1","code":""},
				{"label":"扬州市","children":[],"orgId":"2","pId":"-1","code":""}]
		}];
		$scope.area = tmp;
		$scope.doing_async = false;


	};

	$scope.loadArea();

	/**
	 * 初始化
	 */
	$scope.getAreaInfoById = function() {
		AreaService.getAreaInfo($scope.orgId).then(function(data) {
			$scope.area = data;

		})

	}

	/**
	 * 增加一个节点
	 */
	$scope.add_a_Area = function() {
		var b;
		b = tree.get_selected_branch();
		if(b==null){
			toastr.error('请先选择一个区划！');
			return;
		}
		return tree.add_branch(b, {
			label : '未命名',
			data : {
				something : 42,
				"else" : 43
			}
		});

	};

	/**
	 * 删除一个节点
	 */
	$scope.delete_a_Area = function() {
		toastr.remove();
		if (_branch == "") {
			toastr.error('请选择区划！');
		} else if(_branch.orgId == 0){
			toastr.error('江苏省不能被删除！');
		}else {
			var b = tree.get_children(_branch);
			if (b != "") {
				toastr.warning('该机构下有子节点，不能删除!');
			} else {
				var modalOptions = {
					bodyText : '确认删除' + $scope.output + '?'
				};
				modalServ.show(true, modalOptions).then(function() {
					AreaService.deleteArea($scope.orgId).then(function(data) {
						if (data.code=="10000") {
							$scope.loadArea();
							$timeout(function() {
								tree.expand_all();
								tree.select_firstChild_branch();
							}, 0);
							toastr.success('删除成功！');
						}
					})
				});
			}
		}

	}

})