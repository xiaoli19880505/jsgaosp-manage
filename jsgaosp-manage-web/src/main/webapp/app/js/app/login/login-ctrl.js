'use strict';

/* Controllers */
// signin controller
app.controller('LoginController', ['$scope', '$http', '$state',
    '$window','$sessionStorage', 'GG', 'loginServ','commonServ',
    function ($scope, $http, $state, $window,$sessionStorage, GG, loginServ,commonServ) {
        var homeState = GG.states.home;
        $scope.user = {};
        $scope.authError = null;
        $scope.login = function () {
            loginServ.login({
            	email:$scope.user.email,
            	password:hex_md5($scope.user.password)
            }).success(function (data) {
                if (!data.auth) {
                    $scope.authError = '帐号或密码错误!';
                } else {

                   // 获取数据字典列表
                    var  codeKeyList=['sys_type','xz_type','yw_type'];
                    var  codeList={};
                    angular.forEach(codeKeyList,function (item) {
                        commonServ.getCodeListByColName(item).then(function(data){
                            codeList[item]=data.data;
                        });
                    })
                    $sessionStorage.codeList=codeList;





                    $sessionStorage.user = data.user;
                    $sessionStorage.company = data.company;
                    $sessionStorage.role = data.role;
                	
                    $window.sessionStorage.token = data.token;
                    //存储角色session
                    $window.sessionStorage.setItem("user_current_roleType",data.role.roleType);
                    //
                    $state.go(homeState);
                }
            }).error(function (x) {
                $scope.authError = '服务器错误！';
            });
        };

    }
]);