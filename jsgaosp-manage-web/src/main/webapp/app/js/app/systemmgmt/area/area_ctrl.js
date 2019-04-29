'use strict';

app.controller('AreaController',function($scope,$http,$state,$timeout,modalServ,AreaService){


	//选中的区划或者要新添区划的对象属性
	$scope.areaItem= {
		"area_name":"",
		"area_no":"",
		"p_area_no":"",
		"id":"",
		"status":"",
		"children":""
	};

	//0:新增区划  1：修改区划属性
	//初始化为1
	$scope.flag=1;

	//表示新增区划的父节点area_no
	$scope.parentId=0;


	//因无法改写树结构控件，额外定义以下三个变量来控制表单是否显示

    //控制区划表单是否显示
    $scope.form_is_show=false;

	//表示提交（增改删）成功后，刷新树结构过程前后的临时状态变量
	$scope.tmp_flag=false;

	//表示树结构是否手动选中过，提交过（增改删）刷新树结构会触发选中
	$scope.tree_selected=false;




	// $scope.getAreaInfo=function(){
	// 	AreaService.loadArea().then(function(data){
	// 		var arr = new Array();
	// 		arr.push(data);
	// 		console.log(arr);
	// 		console.log(arr.length)
	// 		$scope.area=data.data;
	// 	})
	// }



	//获取区划列表
	//$scope.getAreaInfo();

	var _branch = "";
	$scope.keyword="";

	$scope.getArea = function(branch) {
		_branch = branch;
//		console.log(_branch);
		$scope.area_no = _branch.area_no;

		$scope.flag=1;



		is_show_form();


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


	// $scope.area = [];
	// var tree = $scope.my_tree = {};
	//
	// var tmp=[];
	// /**
	//  * 加载地区树形结构
	//  */
	// $scope.loadArea = function() {
	// 	$scope.doing_async = true;
	// 	AreaService.loadArea().then(function(data) {
	// 		if (data.code=="10000") {
	// 			tmp=data.data;
	// 			$scope.area = tmp;
	// 			$scope.doing_async = false;
	// 		}
	// 	})
	// };

	// $scope.loadArea();

	/**
	 * 增加一个节点
	 */
	$scope.add_a_Area = function() {
		var b;
		$scope.tree_selected=false;
		b = tree.get_selected_branch();

		if(b==null){
			toastr.error('请先选择一个区划！');
			return;
		}

		if(b.children==""){
			toastr.error('不能在市区下增加子节点！');
			return;
		}

		$scope.flag=0;


		$scope.areaItem.area_name='未命名';
		$scope.areaItem.area_no="";
		$scope.areaItem.id="";
		$scope.areaItem.status="1";
		$scope.areaItem.p_area_no=$scope.parentId;


		return tree.add_branch(b, {
			area_name : '未命名',
			data : {
				// something : 42,
				// "else" : 43
			}
		});

	};

	$scope.$watch('orgNo', function (newVal, oldVal) {
		if ($scope.orgNo != null) {
			console.log($scope.orgNo);

		}
	})
	/**
	 * 删除一个节点
	 */
	$scope.delete_a_Area = function() {
		toastr.remove();
		if (_branch == "") {
			toastr.error('请选择区划！');
		} else if(_branch.area_no == 32100){
			toastr.error('江苏省不能被删除！');
		}else {
			var b = tree.get_children(_branch);
			if (b != "") {
				toastr.warning('该区划下有子节点，不能删除!');
			} else {
				var modalOptions = {
					bodyText : '确认删除' + $scope.output + '?'
				};
				modalServ.show(true, modalOptions).then(function() {
					AreaService.deleteArea($scope.areaItem).then(function(data) {
						if (data.code=="10000") {
							$scope.tmp_flag=true;
							$scope.loadArea();
							$timeout(function() {
								tree.expand_all();
								tree.select_firstChild_branch();
								set_flag_false();
							}, 0);


							toastr.success('删除成功！');
						}
					})
				});
			}
		}

	}


	$scope.updateArea=function(){

		if($scope.flag==1){
			AreaService.updateArea($scope.areaItem).then(function(data){
				if(data.code=="10000"){
					$scope.tmp_flag=true;
					toastr.success('更新区划成功！');
					$scope.loadArea();
					$timeout(function() {
						tree.expand_all();
						tree.select_firstChild_branch();
						set_flag_false();
					}, 0);


				}else{
					toastr.error('更新区划失败！');

				}
			})
		}else{
			AreaService.addArea($scope.areaItem).then(function(data){

				if(data.code=="10000"){
					$scope.form_is_show=false;
					$scope.tmp_flag=true;
					toastr.success('新建区划成功！');
					$scope.loadArea();
					$timeout(function() {
						tree.expand_all();
						tree.select_firstChild_branch();
						set_flag_false();
					}, 0);
				}else{
					toastr.error('新建区划失败！');

				}
			})
		}

	}

	$scope.submitForm = function (isValid) {

		$scope.submitted = false;
		if(isValid){
			// alert("run");
			$scope.updateArea();
		}else{
			$scope.submitted = true;
		}





	};
	

	
	
	function set_flag_false() {
		$timeout(function() {
			$scope.tmp_flag=false;

		}, 1000);
	}

	function  is_show_form() {
		$scope.tree_selected=true;
		if($scope.tmp_flag){
			$scope.tree_selected=false;
		}

		if($scope.tree_selected ){
			$scope.form_is_show=true;
		}else{
			$scope.form_is_show=false;
		}


	}

})