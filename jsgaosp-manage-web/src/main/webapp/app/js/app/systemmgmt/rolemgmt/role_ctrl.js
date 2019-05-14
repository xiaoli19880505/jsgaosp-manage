'use strict';

/* Controllers */
// hospital_role controller
app.controller('SystemRoleController', function ($scope, GG, $timeout, $http, $state, $modal, $stateParams, BcRoleService, modalServ, SystemRoleUserService, SystemRoleFunService) {

    $scope.totalItems = 0;
    $scope.currentPage = 1;
    $scope.maxSize = 5;
    $scope.pageChanged = function (page) {
        $scope.currentPage = page;
        $scope.loadUserList();
    };
    $scope.roleId = null;
    $scope.userId = null;
    $scope.roleType = null;
    $scope.tab = [true, false, false];

    
    $scope.GGuser = GG.user;
    $scope.GGsysadmin = GG.sysadmin;
    $scope.GGmaintain = GG.maintain;
    /***
     * 角色人员
     */
    $scope.loadUserList = function () {
        $scope.role_user = [];
        SystemRoleUserService.get($scope.roleType,$scope.roleId, null, "1", $scope.currentPage, 10)
            .then(function (data) {
                $scope.totalItems = data.totalItems;
                $scope.role_user = data.content;
                $scope.userId = null;
                $scope.role_org = null;
            })
    };

    $scope.deleteUser = function (index) {
        modalServ.showModal({}, {
            bodyText: '确定要将此用户从本角色中移除吗?'
        }).then(function (data) {
            SystemRoleUserService.delete($scope.roleId, $scope.role_user[index].userId)
                .then(function (data) {
                    toastr.success('用户移除成功!');
                    $scope.loadUserList();
                });
        });
    };

    $scope.selectUser = function (item) {
        angular.forEach($scope.role_user, function (item) {
            item.selected = false;
        });
        item.selected = true;
        $scope.userId = item.userId;

    };



    $scope.open = function (size, type) {
        var isUser = type == 'user';
        var validate = false;
        if (isUser) {
            validate = $scope.roleId != null;
        } else {
            validate = $scope.userId != null;
        }
        if (validate) {
            var id = "";
            if (isUser) {
                id = $scope.roleId;
            } else {
                id = $scope.userId;
            }
            var modalInstance = $modal.open({
                templateUrl: 'tpl/systemmgmt/rolemgmt/role_modal_form.html',
                controller: 'ModalSystemUserViewInstanceCtrl',
                size: size,
                backdrop: 'static',
                resolve: {
                    items: function () {
                        return [type, id,$scope.roleType];
                    }
                }
            });
            modalInstance.result.then(function (items) {
                if (items[0]) {//如果modal返回成功的话
                    if (isUser) {
                        $scope.loadUserList();
                    }
                }
            }, function () {
                //取消
            });
        } else {
            if (isUser) {
                toastr.error("未选择任何角色！");
            } else {
                toastr.error("未选择任何用户！");
            }
        }
    };

    /***
     * 角色功能
     */
    $scope.saveRoleFun = function () {
        $scope.role.perm = SystemRoleFunService.getSelected($scope.roleFunction);
        BcRoleService.update($scope.role)
            .then(function () {
                toastr.success('保存角色功能成功!');
                $scope.loadRoleList();
            });
    };

    $scope.selectAll = function (items) {
        angular.forEach(items.funcs, function (item) {
            item.selected = items.selected;
        });
    };

    $scope.select = function (item, items) {
        if (!item.selected) {
            items.selected = false;
        }
    };


    /***
     * 角色
     */

    $scope.addRole = function () {
        $scope.role = {};
        angular.forEach($scope.roles, function (item) {
            item.selected = false;
        });
        $scope.roleId = null;
        $scope.role_user = null;
        $scope.role_view = null;
        $scope.tab = [true, false, false];
    };

    $scope.role = {};
    $scope.saveRole = function () {
        if ($scope.roleId == null) {
        	BcRoleService.save($scope.role)
                .then(function () {
                    toastr.success('添加角色成功!');
                    $scope.loadRoleList();
                });
        } else {
        	BcRoleService.update($scope.role)
                .then(function () {
                    toastr.success('更新角色成功!');
                    $scope.loadRoleList();
                });
        }
    };

    $scope.selectRole = function (item) {
        angular.forEach($scope.roles, function (item) {
            item.selected = false;
        });
        item.selected = true;
        $scope.roleId = item.roleId;
        $scope.roleType = item.roleType;
        $scope.loadUserList();
        var selectedRolesFun = null;
        if(item.roleType == GG.user){
        	selectedRolesFun = GG.user_role_fun;
        }else{
        	selectedRolesFun = GG.sysadmin_role_fun;
        }
        $scope.roleFunction = SystemRoleFunService.setSelected(selectedRolesFun, item.perm);
        $scope.role = angular.copy(item);
    };

    $scope.user = GG.user;
    $scope.sysadmin = GG.sysadmin;

    $scope.loadRoleList = function () {
        $scope.roles = {};
        BcRoleService.query()
            .then(function (data) {
                $scope.roles = data;
                if (data.length > 0) {//默认选中第一个
                    $scope.selectRole(data[0]);
                }
            })
    };
    $scope.loadRoleList();

    $scope.deleteRole = function (item) {
    	$timeout(function(){    	//延时0.1秒，等待selectrole加载数据
	    	if($scope.role_user.length != 0){//如果角色下关联了用户，则不能删除
	    		toastr.error('该角色下有'+$scope.role_user.length+'个用户，不能删除！');
	    		return;
	    	}
	        modalServ.showModal({}, {
	            bodyText: '确定要删除这个角色吗?'
	        }).then(function (data) {
	        	BcRoleService.delete(item.roleId)
	                .then(function (data) {
	                    toastr.success('删除角色成功!');
	                    $scope.loadRoleList();
	                });
	        });
	        },100);
    };
    /**
     * 设置高度
     */
    $scope.setHeight = function(){
    	var totalHeight = $('#rightPanl').height();
    	$('#Rolefunctions').height(totalHeight*0.7);
    }
    $scope.setHeight();
    /**
     * 阻止a链接的跳转行为 
     */
	$scope.change=function(){
		 $('#myTab a:first').tab('show');//初始化显示哪个tab 
	        $('#myTab a').click(function (e) { 
	          e.preventDefault();//阻止a链接的跳转行为 
	          $(this).tab('show');//显示当前选中的链接及关联的content 
	          $scope.refreshProcess();
	        }) 
	};
	$scope.change();
});

