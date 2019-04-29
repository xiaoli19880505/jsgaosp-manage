'use strict';

app.controller('OrgController',function($scope,$http,$state,$modal,$timeout,modalServ,OrgService){

	$scope.totalItems = 100;
	$scope.currentPage = 1;
	$scope.maxSize = 5;


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


	var _branch = "";
	$scope.keyword="";

	$scope.getOrg = function(branch) {
		$scope.operate_type="org";
		_branch = branch;
		$scope.org_no = _branch.org_no;
		if(_branch.org_no==null){
			$scope.flag=0;
		}else{
			$scope.flag=1;
		}
		if (branch.org_no!= 0) {
			$scope.output=_branch.org_name;
			$scope.orgItem.orgName=_branch.org_name;
			$scope.orgItem.orgNo=_branch.org_no;
			$scope.orgItem.pOrgNo=_branch.p_org_no;
			$scope.orgItem.children=_branch.children;
			$scope.orgItem.address=_branch.address;
			$scope.orgItem.memo=_branch.memo;
			$scope.orgItem.status=_branch.status;
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

	$scope.departmentsList=[];
	$scope.loadDepartmentList=function(){
		OrgService.loadDepartmentList($scope.currentPage,$scope.org_no).then(function (res) {
			if (res.code=="10000"){
				$scope.departmentsList=res.data.list;
				$scope.totalItems=res.data.totalCount;
				$scope.currentPage=res.data.page;
				$scope.chooseArgs=[];
			}
		})
	}


	$scope.$watch('org_no', function (newVal, oldVal) {
		if ($scope.org_no != null) {
			$scope.loadDepartmentList();
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
	 * 增加组织
	 */
	$scope.add_a_org = function() {
		$scope.operate_type="add_org";
		var b;
		$scope.tree_selected=false;
		b = tree.get_selected_branch();

		if(b==null){
			toastr.error('请先选择一个组织！');
			return;
		}
		$scope.flag=0;
		$scope.open("","add_org",null);
	};

	/**
	 * 修改组织
	 */
	$scope.update_a_org = function() {
		$scope.operate_type="update_org";
		var b;
		$scope.tree_selected=false;
		b = tree.get_selected_branch();

		if(b==null){
			toastr.error('请先选择一个组织！');
			return;
		}
		$scope.flag=0;
		$scope.open("","update_org",null);
	};


	/**
	 * 修改部门
	 */
	$scope.update_department = function(department) {
		$scope.orgItem=department;
		$scope.operate_type="update_department";
		$scope.open("","update_department",null);
	};


	/**
	 * 删除组织
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
							}, 0);
							toastr.success('删除成功！');
						}
					})
				});
			}
		}
	}

	//弹出新增窗口
	$scope.open = function (size, type,index) {
		var modalInstance = $modal.open({
			templateUrl: 'tpl/systemmgmt/orgmgmt/org_form.html',
			controller: 'ModalOrgInstanceCtrl',
			size: size,
			backdrop: 'static',
			resolve: {
				items: function () {
					var org = {};
					if(index != null){
						org = $scope.orgList[index];
					}else{
						org=$scope.orgItem;
					}
					$scope.items = [type,org];
					return $scope.items;
				}
			}
		});

		modalInstance.result.then(function (items) {

				$timeout(function () {
					$scope.loadOrg();
					tree.expand_all();
					tree.select_firstChild_branch();
				}, 0);
				$scope.loadDepartmentList($scope.orgNo);

		}, function () {

		});
	};


})