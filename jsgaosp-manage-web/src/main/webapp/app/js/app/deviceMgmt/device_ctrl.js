app.controller('deviceMgmtCtrl', function($scope, $timeout, $http,$modal,modalServ, OrgService/*, treeUtils*/) {
	var _branch = "";
	$scope.keyword="";
	
	$scope.getOrg = function(branch) {
		_branch = branch;
//		console.log(_branch);
		$scope.orgId = _branch.orgId;
		if (branch.orgId!= -1) {
			$scope.output=_branch.label;
			if(tree.get_parent_branch(branch)!=null){
				$scope.pId = tree.get_parent_branch(branch).orgId;
				$scope.pCode=tree.get_parent_branch(branch).code;
				$scope._pCode=$scope.pCode+"_";
			}_branch
			$scope.getOrgInfoById();
		}
	};
	$scope.orgList = [];
	var tree = $scope.my_tree = {};

	/**
	 * 加载机构树形结构
	 */
	$scope.loadOrg = function() {
		$scope.doing_async = true;
		var tmp = [{
			"label":"机房",
			"orgId":"-1",
			"pId":"-1",
			"code":"",
			"address":"",
			"children":[{"label":"新城大厦机房","children":[],"orgId":"0","pId":"-1","code":""},
			            {"label":"二长电信机房","children":[],"orgId":"1","pId":"-1","code":""},
			            {"label":"龙江机房","children":[],"orgId":"2","pId":"-1","code":""}]
		}];
		$scope.orgList = tmp;
		$scope.doing_async = false;
		
		
		/*$scope.doing_async = true;
		OrgService.query().then(function(data) {
			var tmp = [{
				"label":"所有机构",
				"orgId":"-1",
				"pId":"-1",
				"code":"",
				"address":"",
				"children":[{"label":"医疗机构","children":[],"orgId":"-1","pId":"-1","code":""},
				            {"label":"监管机构","children":[],"orgId":"-1","pId":"-1","code":""}]
			}];
			angular.forEach(data,function(item){
				if(item.type=="01"){
					tmp[0]['children'][0]['children'].push(item);
				}else{
					tmp[0]['children'][1]['children'].push(item);
				}
			})
			//tmp[0]['children']=data;
			$scope.orgList = tmp;
			$scope.orgList=treeUtils.treeFilter(tmp, $scope.keyword);
			$scope.doing_async = false;
			$timeout(function() {
				//tree.expand_all();
				tree.select_firstChild_branch();
			}, 0);
		})*/

	};

	/**
	 * 初始化
	 */
	$scope.getOrgInfoById = function() {
		OrgService.getOrgInfo($scope.orgId).then(function(data) {
			$scope.org = data;

		})

	}

	/**
	 * 增加一个节点
	 */
	$scope.add_a_org = function() {
		var b;
		b = tree.get_selected_branch();
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
	$scope.delete_a_org = function() {
		toastr.remove();
		if (_branch == "") {
			toastr.error('请选择机构！');
		} else if(_branch.orgId == 0){
			toastr.error('0号医院不能被删除！');
		}else {
			var b = tree.get_children(_branch);
			if (b != "") {
				toastr.warning('该机构下有子节点，不能删除!');
			} else {
				var modalOptions = {
					bodyText : '确认删除' + $scope.output + '?'
				};
				modalServ.show(true, modalOptions).then(function() {
					OrgService.deleteOrg($scope.orgId).then(function(data) {
						if (data.result) {
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

	/**
	 * 增加或修改一个机构
	 */
	$scope.submitForm = function(isValid) {
		$scope.submitted = false;
		if(isValid){
			var data = {
				orgId : $scope.org.orgId,
				pId : $scope.pId,
				note : $scope.org.note,
				name : $scope.org.name,
				code : (($scope.pCode=="")?$scope.pCode:$scope.pCode+'_')+$scope.org.code,
				address : $scope.org.address,
				type : $scope.org.type,
				frontAddress : $scope.org.frontAddress
			};
			if($scope.org.orgId==null){
				showProgress(data);
			}else{
				OrgService.saveOrg(data).then(function(data){
					if(data.result){
						$scope.loadOrg();
						$timeout(function() {
							tree.expand_all();
							tree.select_firstChild_branch();
						}, 0);
						toastr.success('保存成功！');
					}
				});
			}
		}else{
			$scope.submitted = true;
		}
	}
	$scope.search = function() {
		$scope.loadOrg();
	}

	var showProgress=function(data) {
        var modalInstance = $modal.open({
            templateUrl: 'tpl/hospitalmgmt/orgmgmt/show_progress_form.html',
            controller: 'ModalShowProgressInstanceCtrl',
            size: '',
            backdrop: 'static',
            resolve: {
                items: function () {
                    return [data];
                }
            }
        });

        modalInstance.result.then(function (items) {
            if (items[0]) {//如果modal返回成功的话
            	$scope.loadOrg();
				$timeout(function() {
					tree.expand_all();
					tree.select_firstChild_branch();
				}, 0);
				toastr.success('保存成功！');
            }
        }, function () {
            //取消
        });
    };
	
	$scope.loadOrg();
	
	$scope.synchronous_a_org=function(){
		modalServ.showModal({}, {
			bodyText: "确定同步"+$scope.org.name+"下的用户信息?"
		}).then(function(result) {
			OrgService.synchronous($scope.orgId).then(function(data){
				if(data.result){
					toastr.success('同步成功！');
				}
			})
		});
	}

    
    
});



app.directive('codeValidation', function($http,OrgService) {
    return {
        require : 'ngModel',
        link : function(scope, elm, attrs, ctrl) {
            elm.bind('blur', function() {
            	var code=((scope.pCode=="")?scope.pCode:scope.pCode+'_')+scope.orgForm.code.$modelValue;
            	OrgService.checkCode(code,scope.orgId).then(function(data){
                  ctrl.$setValidity('code',data.flag);
          	})
            });
        }
    };
});

app.controller('ModalShowProgressInstanceCtrl', ['$scope', '$modalInstance','$interval', 'items', 'OrgService', function ($scope, $modalInstance, $interval,items, OrgService) {
	$scope.dynamic = 0;
    $scope.type = "info";
    
    var progressJob=$interval(function(){
    	if($scope.dynamic>=98)return;
    	$scope.dynamic=$scope.dynamic+2;
    },1000);
    
    var save=function(){
    	OrgService.saveOrg(items[0]).then(function(data){
    		if(data.result){
    			$scope.dynamic=100;
    			$modalInstance.close([true]);
    		}
    	});
    }
    save();
    
}]);
