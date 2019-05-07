'use strict';

/* Controllers */
// hospital_people controller
app.controller('PayConfManagerController',
    ['$scope', '$http', '$state', '$modal', '$stateParams', '$timeout', 'modalServ', 'PayAppConfService', 'GG',
        function ($scope, $http, $state, $modal, $stateParams, $timeout, modalServ, PayAppConfService, GG) {

            $scope.totalItems = 100;
            $scope.currentPage = 1;
            $scope.maxSize = 5;

            $scope.GGuser = GG.user;
            $scope.GGsysadmin = GG.sysadmin;
            //$scope.GGmaintain = GG.maintain;

            $scope.pageChanged = function () {
                $scope.loadPayConfs();
            };

            $scope.loadPayConfs = function () {
                PayAppConfService.listPayConf($scope.currentPage).then(function (data) {
                    $scope.lisPayConf = data.listPayConf;
                    $scope.totalItems = data.totalCount;
                    $scope.currentPage = data.page;
                    $scope.choosePayConfs = [];
                })
            }
            $scope.search = function () {
                $scope.loadPayConfs();
            }
//新增
            $scope.open = function (size, type) {
                var modalInstance = $modal.open({
                    templateUrl: 'tpl/systemmgmt/payconfmgmt/pay_conf_modal.html',
                    controller: 'ModalPayConfInstanceCtrl',
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
                        $scope.loadPayConfs();
                    }
                }, function () {

                });
            };


            $scope.loadPayConfs();


            $scope.choosePayConfs = [];
            $scope.choose = function (chk, item, index) {
                item.indexs = index;
                if (chk) {
                    $scope.choosePayConfs.push(item);
                } else if (!chk) {
                    $scope.choosePayConfs.splice($scope.choosePayConfs.indexOf(item), 1);
                }
            }

            $scope.chooseAll = function (master) {
                if (master) {
                    $scope.choosePayConfs = angularjs.copy($scope.users);
                } else {
                    $scope.choosePayConfs = [];
                }
            }

            //删除modal
            $scope.deleteModal = function () {
                if ($scope.choosePayConfs.length != 0) {
                    modalServ.showModal({}, {
                        bodyText: '       确定要删除吗?     '
                    }).then(function (result) {
                        var result = true;
                        angular.forEach($scope.choosePayConfs, function (item) {
                            PayAppConfService.deletePayConf(item.id).then(function (data) {
                                if (!data.result) {
                                    result = false;
                                }
                            });
                        });
                        if (result) {
                            toastr.success('删除成功！');
                            $scope.loadPayConfs();
                        } else {
                            toastr.error('删除失败！');
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
                        message: '请先选择要操作的数据！',
                        callback: function () {
                        },
                        title: "提示",
                    });
                }
            }
//更新
            $scope.update = function () {
                if ($scope.choosePayConfs.length == 1) {
                    var chooseCreate = angular.copy($scope.choosePayConfs[0]);
                    var modalInstance = $modal.open({
                        templateUrl: 'tpl/systemmgmt/payconfmgmt/pay_conf_modal.html',
                        controller: 'ModalPayConfInstanceCtrl',
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
                            $scope.loadPayConfs();
                            $scope.choosePayConfs = [];
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

