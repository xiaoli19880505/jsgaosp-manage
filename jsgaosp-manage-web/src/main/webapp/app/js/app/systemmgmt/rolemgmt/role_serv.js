/**
 * res 为自定义的$resource
 */
angular.module('app')
    .factory('BcRoleService', ['res', 'GG', function (res, GG) {
        var res = res(GG.BASE + '/system/role/:type');
        var service = {
            get: function (id) {
                return res.get({
                    id: id
                }).$promise;
            },
            getLitst: function (orgNo,keyword,currentPage) {
                return res.get({
                    orgNo:orgNo,
                    keyword:keyword,
                    currentPage:currentPage,
                    type:"getRoleList"
                }).$promise;
            },
            saveRoleOp: function (data,role) {
                return res.save({
                    data:data,
                    role:role,
                    type:"setRoleOp"
                }).$promise;
            },
            delete: function (roleId) {
                return res.delete({
                    roleId: roleId
                }).$promise;
            },
            save: function (role) {
                return res.save(role).$promise;
            },
            update: function (role) {
                return res.update(role).$promise;
            }
        };
        return service;
    }]);

angular.module('app')
    .factory('SystemRoleUserService', ['res', 'GG', function (res, GG) {
        var res = res(GG.BASE + '/system/role_user/:type');
        var service = {
            get: function (roleId, keyword, page) {
                return res.get({
                    roleId: roleId,
                    keyword:keyword,
                    page: page
                }).$promise;
            },
            getrUserListNotInThisRole: function (roleId, keyword,  page) {
                return res.get({
                    roleId: roleId,
                    keyword:keyword,
                    page: page,
                    type:"getrUserListNotInThisRole"
                }).$promise;
            },
            countUserByRoleId:function (roleId) {
                return res.get({
                    roleId: roleId,
                    type:"countUserByRoleId"
                }).$promise;
            },
            delete: function (roleId, userId) {
                return res.delete({
                    roleId: roleId,
                    userId: userId
                }).$promise;
            },
            save: function (roleId, ids) {
                return res.save({
                    roleId: roleId,
                    ids: ids
                }).$promise;
            }
        };
        return service;
    }]);


angular.module('app')
    .factory('SystemRoleFunService', ['res', 'GG', function (res, GG) {
        var res = res(GG.BASE + '/system/role_fun');
        var service = {
            setSelected: function (role_fun, selected) {
                var data = angular.copy(role_fun);
                if (selected == "" || selected == null)
                    return data;
                var l = data.length;
                for(var i = l-1; i>=0;i--){
                	if(!data[i].funcs){
                		if(selected.indexOf(data[i].id)>= 0){
                			data[i].selected = true;
                		}
                		continue;
                	}
                	var fl = data[i].funcs.length;
                    for (var j = fl - 1; j >= 0; j--) {
                        if (selected.indexOf(data[i].funcs[j].id) >= 0) {
                        	//存在则选中
                        	data[i].funcs[j].selected = true;
                        }
                    }
                }
//                var selectedArray = selected.toString().split(",");
//				  var firstNumber = selectedArray[0].split("-")[0];
//                for (var n = 0; n < selectedArray.length; n++) {
//                    var temp = selectedArray[n].split("-");
//                    if (temp.length == 2) {
//                        if (data.length > Number(temp[0])-firstNumber) {
//                        	if(!data[Number(temp[0])-firstNumber].funcs){
//                        		data[Number(temp[0])-firstNumber].selected = true;
//                        		continue;
//                        	}
//                            if (data[Number(temp[0])-firstNumber].funcs.length > Number(temp[1])) {
//                                data[Number(temp[0])-firstNumber].funcs[Number(temp[1])].selected = true;
//                            } else {
//                                toastr.error("获取权限出错data[Number(temp[0])].funcs.length > Number(temp[1])");
//                            }
//                        } else {
//                            toastr.error("获取权限出错data.length > Number(temp[0])");
//                        }
//                    } else {
//                        toastr.error("获取权限出错temp.length == 2");
//                    }
//                }

                var ii = data.length;
                for (var i = 0; i < ii; i++) {
                	if(!data[i].funcs){
                		continue;
                	}
                    var jj = data[i].funcs.length;
                    var num = 0;
                    for (var j = 0; j < jj; j++) {
                        if (data[i].funcs[j].selected) {
                            num++;
                        }
                    }
                    if (num == jj) {
                        data[i].selected = true;
                    }
                }
                return data;
            },
            getSelected: function (data) {
                var result = [];
                var ii = data.length;
                for (var i = 0; i < ii; i++) {
                	if(!data[i].funcs){
                		if (data[i].selected) {
                			result.push(data[i].id);
                		}
                		continue;
                	}
                    var jj = data[i].funcs.length;
                    for (var j = 0; j < jj; j++) {
                        if (data[i].funcs[j].selected) {
                            result.push(data[i].funcs[j].id);
                        }
                    }
                }
                return result.join(",");
            }
        };
        return service;
    }]);