/**
 * Created by DK on 2015/8/10.
 */
angular.module('app')
    .controller('appCtrl',function ($scope, $translate, $localStorage, $window, GG, $state, $http, $modal,$sessionStorage) {
        //http 认证权限
        $scope.pFilter = function (data,usertype) {
            data = data.toString();
            var group;
            //如果用户的类型为ADMIN拥有最高权限
            var current_role_type = $window.sessionStorage.getItem("user_current_roleType");
            if(GG.user == current_role_type){//普通用户



                group = angular.copy(GG.user_role_fun);
            }else if(GG.sysadmin = current_role_type){//拥有本公司最高权限有所有权限
                group = angular.copy(GG.sysadmin_role_fun);
            }
            group = angular.copy(GG.user_role_fun.concat(GG.sysadmin_role_fun));
            //获取当前角色，加载不同的菜单
            var l = group.length;
            for (var i = l - 1; i >= 0; i--) {
                if(!group[i].funcs){
                    if (data.indexOf(i + "-" + "0") < 0) {
                        group.splice(i, 1);
                    }
                    continue;
                }
                var fl = group[i].funcs.length;
                for (var j = fl - 1; j >= 0; j--) {
                    if (data.indexOf(group[i].funcs[j].id) < 0) {
                        //权限控制
                        group[i].funcs.splice(j, 1);
                    }
                }
                if (group[i].funcs.length == 0) {
                    group.splice(i, 1);
                }
            }
            return group;
        };


        //获取一些全局参数
        $http.get("/global/info")
            .success(function (data) {
                var perm = "";
                angular.forEach(data.roles,function(item){
                    perm += item.perm;
                })
                $scope.app.groups = $scope.pFilter(perm,data.user.userType);
                $scope.app.user = data.user;
                $scope.app.company = data.company;
                $scope.app.role = data.role;
            })
            .error(function () {
                $scope.app.groups = null;
                toastr.error("获取权限失败!请重试");
            });


        //获取数据字典列表
        // var  codeKeyList=['sys_type','xz_type','yw_type'];
        // var  codeList={};
        // angular.forEach(codeKeyList,function (item) {
        //     $http.get('/common/list_code?codeSortKey='+item)
        //         .success(function(data) {
        //             alert(data)
        //             codeList[item]=(data.data);
        //         })
        //         .error(function(){
        //             alert("获取数据字典失败")
        //
        //         });
        // })

        // $scope.app.codeList = data.codeList;




        $scope.update = function () {
            var modalInstance = $modal.open({
                templateUrl: 'tpl/blocks/user_modal_form.html',
                controller: 'ModalInfoInstanceCtrl',
                size: "",
                backdrop: 'static',
                resolve: {
                    items: function () {
                        return [$scope.app.user];
                    }
                }
            });
            modalInstance.result.then(function (items) {
                if (items[0]) {
                    $scope.app.user = items[0];
                }
            }, function () {

            });
        };

    });

app.controller('ModalInfoInstanceCtrl', function ($scope, $modalInstance, items, $http) {

    $scope.user = items[0];
    console.log($scope.user);
    $scope.confirm_password = $scope.user.userPwd;

    $scope.ok = function () {
        $scope.user.createDate = null;
        $scope.user.createBy = null;
        $scope.user.updateBy = null;
        $scope.user.updateDate = null;
        $http({
            method: 'put',
            url: '/global/user',
            data: $scope.user
        }).then(function () {
            toastr.success("保存成功!");
            $modalInstance.close([$scope.user]);
        })
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});
