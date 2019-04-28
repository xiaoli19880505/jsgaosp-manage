'use strict';

app.controller('OrgController',function($scope,$http,$state,$timeout,modalServ,OrgService){


	//选中的区划或者要新添区划的对象属性
	$scope.orgItem= {
		"org_name":"",
		"p_org_no":"",
		"status":"",
		"children":"",
		"officeTel":"",
		"mobileTel":"",
		"chargePerson":""
	};

	//0:新增组织  1：修改组织属性
	//初始化为1
	$scope.flag=1;

	$scope.operate_type="org";


	//表示新增组织的父节点
	$scope.parentId=0;


	//因无法改写树结构控件，额外定义以下三个变量来控制表单是否显示

    //控制表单是否显示
    $scope.form_is_show=false;

	//表示提交（增改删）成功后，刷新树结构过程前后的临时状态变量
	$scope.tmp_flag=false;

	//表示树结构是否手动选中过，提交过（增改删）刷新树结构会触发选中
	$scope.tree_selected=false;



	var _branch = "";
	$scope.keyword="";

	$scope.getOrg = function(branch) {

		$scope.operate_type="org";
		_branch = branch;
//		console.log(_branch);
		$scope.org_no = _branch.org_no;


		if(_branch.org_no==null){
			$scope.flag=0;
		}else{
			$scope.flag=1;
		}
		is_show_form();
		if (branch.org_no!= 0) {
			$scope.output=_branch.org_name;
			$scope.orgItem.orgName=_branch.org_name;
			$scope.orgItem.orgNo=_branch.org_no;
			$scope.orgItem.pOrgNo=_branch.p_org_no;
			$scope.orgItem.children=_branch.children;
			$scope.orgItem.address=_branch.address;
			$scope.orgItem.memo=_branch.memo;
			$scope.orgItem.officeTel=_branch.office_tel;
			$scope.orgItem.mobileTel=_branch.mobile_tel;
			$scope.orgItem.chargePerson=_branch.charge_person;
			$scope.orgItem.orgType=_branch.orgType;
			if(tree.get_parent_branch(branch)!=null){

				$scope.p_org_no = tree.get_parent_branch(branch).org_no;
				$scope.parentId=$scope.p_org_no;
			}_branch





		}
	};

	$scope.departmentList=[];
	$scope.loadDepartmentList=function(orgNo){
		OrgService.loadDepartmentList(orgNo).then(function (data) {
			if (data.code=="10000"){
				$scope.departmentList=data.data;
			}
		})
	}


	$scope.$watch('org_no', function (newVal, oldVal) {
		if ($scope.org_no != null) {
			$scope.loadDepartmentList(newVal);
		}
	})






	$scope.org = [];
	var tree = $scope.my_tree = {};

	var tmp=[];
	/**
	 * 加载组织树形结构
	 */
	$scope.loadOrg = function() {
		$scope.doing_async = true;
		OrgService.loadOrg().then(function(data) {
			if (data.code=="10000") {
				tmp=data.data;
				$scope.org = tmp;
				$scope.doing_async = false;
			}
		})
	};

	$scope.loadOrg();

	/**
	 * 增加一个节点
	 */
	$scope.add_a_org = function() {
		$scope.operate_type="org";
		var b;
		$scope.tree_selected=false;
		b = tree.get_selected_branch();

		if(b==null){
			toastr.error('请先选择一个组织！');
			return;
		}



		$scope.flag=0;


		$scope.orgItem.org_name='未命名';
		$scope.orgItem.pOrgNo=$scope.parentId;


		return tree.add_branch(b, {
			org_name : '未命名',
			data : {
				// something : 42,
				// "else" : 43
			}
		});

	};


	$scope.selectDepartment=function(item){
		$scope.orgItem=item;
		$scope.operate_type="department";
	}

	$scope.deleteDepartment=function(item){
		if(item.orgNo==null){
			toastr.warning('请先选择组织!');
			return;
		}

		OrgService.deleteOrg(item).then(function (data) {
			if(data.code=="10000"){
				toastr.success('删除成功！');
			}else{
				toastr.error('删除失败！');
			}
		})
	}
	/**
	 * 删除一个节点
	 */
	$scope.delete_a_org = function() {
		toastr.remove();
		if (_branch == "") {
			toastr.error('请选择组织！');
		} else {
			var b = tree.get_children(_branch);
			if (b != "") {
				toastr.warning('该组织下有子节点，不能删除!');
			} else {
				var modalOptions = {
					bodyText : '确认删除' + $scope.output + '?'
				};
				modalServ.show(true, modalOptions).then(function() {
					OrgService.deleteOrg($scope.orgItem).then(function(data) {
						if (data.code=="10000") {
							$scope.tmp_flag=true;
							$scope.loadOrg();
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


	$scope.addDepartment=function(){
		var b;
		$scope.tree_selected=false;
		b = tree.get_selected_branch();

		if(b==null){
			toastr.error('请先选择一个组织！');
			return;
		}
		$scope.orgItem={};
		$scope.orgItem.pOrgNo=$scope.org_no;
		$scope.operate_type="department";
	}

	$scope.updateOrg=function(){

		if($scope.operate_type=="org"){

			if($scope.flag==1){
				OrgService.updateOrg($scope.orgItem).then(function(data){
					if(data.code=="10000"){
						$scope.tmp_flag=true;
						toastr.success('更新组织成功！');
						$scope.loadOrg();
						$timeout(function() {
							tree.expand_all();
							tree.select_firstChild_branch();
							set_flag_false();
						}, 0);


					}else{
						toastr.error('更新组织失败！');

					}
				})
			}else{
				$scope.orgItem.pOrgNo=$scope.parentId;
				OrgService.addOrg($scope.orgItem).then(function(data){
					if(data.code=="10000"){
						$scope.form_is_show=false;
						$scope.tmp_flag=true;
						toastr.success('新建组织成功！');
						$scope.loadOrg();
						$timeout(function() {
							tree.expand_all();
							tree.select_firstChild_branch();
							set_flag_false();
						}, 0);
					}else{
						toastr.error('新建组织失败！');

					}
				})
			}
		}else{


		}


	}

	$scope.submitForm = function (isValid) {

		$scope.submitted = false;
		if(isValid){
			// alert("run");
			$scope.updateOrg();
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