'use strict';

/* Controllers */
// hospital_people controller
app.controller('SystemUserManagerController',
    ['$scope', '$http', '$state', '$modal', '$stateParams', '$timeout', 'modalServ', 'BcUserService', 'CompanyService', 'GG',
        function ($scope, $http, $state, $modal, $stateParams, $timeout, modalServ, BcUserService, CompanyService, GG) {


            $scope.custom = true;
            $scope.orgFlag=false;
            $scope.orgTreeToggle = function () {
                $scope.custom = !$scope.custom;
                $scope.orgFlag=true;
            }

            $scope.totalItems = 100;
            $scope.currentPage = 1;
            $scope.maxSize = 5;
            $scope.keyword = "";
            //$scope.companyId = $scope.app.user.companyId
            var user_height = $(window).height() - 330;
            $("#SystemUserDiv").parent().css("overflow", "hidden");
            $("#usersDiv").css("height", user_height);



            $scope.GGuser = GG.user;
            $scope.GGsysadmin = GG.sysadmin;
            //$scope.GGmaintain = GG.maintain;

            $scope.pageChanged = function () {
                $scope.loadUsers();
            };

            $scope.loadUsers = function () {
                var tmp;
                if($scope.orgFlag){
                    tmp=$scope.orgNo;
                }
                BcUserService.listUser($scope.currentPage, $scope.keyword, $scope.companyId,tmp).then(function (data) {
                    $scope.users = data.sysUsers;
                    $scope.totalItems = data.totalCount;
                    $scope.currentPage = data.page;
                    $scope.chooseUsers = [];
                })
            }

            $scope.$watch('orgNo', function (newVal, oldVal) {
                if ($scope.orgNo && $scope.orgFlag) {

                    $scope.loadUsers();
                }
            })
            $scope.searchByName = function () {
                $scope.loadUsers();
            }

            $scope.deleteOrgNo=function(){
                $scope.orgNo=null;
                $scope.loadUsers();
            }

            $scope.open = function (size, type) {
                var modalInstance = $modal.open({
                    templateUrl: 'tpl/systemmgmt/usermgmt/user_modal_form.html',
                    controller: 'ModalSystemUserInstanceCtrl',
                    size: size,
                    backdrop: 'static',
                    resolve: {
                        items: function () {
                            var user = {};
                            $scope.items = [type, user, $scope.companyId];
                            return $scope.items;
                        }
                    }
                });

                modalInstance.result.then(function (items) {
                    if (items[0]) {
                        $scope.loadUsers();
                    }
                }, function () {

                });
            };


            $scope.resetPwd = function (userId) {
                if ($scope.chooseUsers.length != 0) {
                    modalServ.showModal({}, {
                        bodyText: "确定要重置密码？"
                    }).then(function (result) {
                        var result = true;
                        angular.forEach($scope.chooseUsers, function (item) {
                            BcUserService.resetPwd(item.userId).then(function (data) {
                                if (!data.result) {
                                    result = false;
                                }
                            });
                        });
                        if (result) {
                            toastr.success('重置成功！');
                            $scope.loadUsers();
                        } else {
                            toastr.error('重置失败！');
                        }
                    });
                } else {
                    bootbox.alert({
                        buttons: {
                            ok: {
                                label: '确定',
                                className: 'btn-info btn-dark'
                            }
                        },
                        message: '请先选择操作的数据！',
                        callback: function () {
                        },
                        title: "提示",
                    });
                }
            }
            $scope.loadUsers();


            $scope.chooseUsers = [];
            $scope.choose = function (chk, item, index) {
                item.indexs = index;
                if (chk) {
                    $scope.chooseUsers.push(item);
                } else if (!chk) {
                    $scope.chooseUsers.splice($scope.chooseUsers.indexOf(item), 1);
                }
            }

            $scope.chooseAll = function (master) {
                if (master) {
                    $scope.chooseUsers = angularjs.copy($scope.users);
                } else {
                    $scope.chooseUsers = [];
                }
            }

            //删除modal
            $scope.deleteModal = function () {
                if ($scope.chooseUsers.length != 0) {
                    modalServ.showModal({}, {
                        bodyText: '       确定要删除这些用户吗?     '
                    }).then(function (result) {
                        var result = true;
                        angular.forEach($scope.chooseUsers, function (item) {
                            BcUserService.deleteUser(item.userId).then(function (data) {
                                if (!data.result) {
                                    result = false;
                                }
                            });
                        });
                        if (result) {
                            toastr.success('删除用户成功！');
                            $scope.loadUsers();
                        } else {
                            toastr.error('删除用户失败！');
                        }
                    });
                } else {
                    bootbox.alert({
                        buttons: {
                            ok: {
                                label: '确定',
                                className: 'btn-info btn-dark'
                            }
                        },
                        message: '请先选择操作的数据！',
                        callback: function () {
                        },
                        title: "提示",
                    });
                }
            }

            $scope.update = function () {
                if ($scope.chooseUsers.length == 1) {
                    var chooseCreate = angular.copy($scope.chooseUsers[0]);
                    var modalInstance = $modal.open({
                        templateUrl: 'tpl/systemmgmt/usermgmt/user_modal_form.html',
                        controller: 'ModalSystemUserInstanceCtrl',
                        size: '',
                        backdrop: 'static',
                        resolve: {
                            items: function () {
                                return ['update', chooseCreate, $scope.companyId];
                            }
                        }
                    });

                    modalInstance.result.then(function (items) {
                        if (items[0]) {//如果modal返回成功的话
                            $scope.loadUsers();
                            $scope.chooseUsers = [];
                        }
                    }, function () {
                        //取消
                    });
                } else {
                    bootbox.alert({
                        buttons: {
                            ok: {
                                label: '确定',
                                className: 'btn-info btn-dark'
                            }
                        },
                        message: '请先选择一个操作的数据！',
                        callback: function () {
                        },
                        title: "提示",
                    });
                }
            }


        }]);

app.filter("hidePasswordFilter", function () {
    return function (input) {
        if (input == "123456" || input == "" || input == null) {
            return input;
        } else {
            input = input.substring(0, input.length - 4) + "****";
        }
        return input;
    }
});

