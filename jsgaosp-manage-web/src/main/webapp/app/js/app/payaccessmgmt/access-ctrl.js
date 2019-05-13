'use strict';

/* Controllers */
// hospital_people controller
app.controller('PayConfManagerController',
    ['$scope', '$http', '$state', '$modal', '$stateParams', '$timeout', 'modalServ', 'PayAccessConfService', 'GG',
        function ($scope, $http, $state, $modal, $stateParams, $timeout, modalServ, PayAccessConfService, GG) {

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
                PayAccessConfService.listAccessConf($scope.currentPage,$scope.businessName).then(function (data) {
                    $scope.lisPayConf = data.list;
                    $scope.totalItems = data.totalCount;
                    $scope.currentPage = data.page;
                    $scope.choosePayConfs = [];
                })
            }
//新增
            $scope.open = function (size, type) {
                var modalInstance = $modal.open({
                    templateUrl: 'tpl/payaccessmgmt/access_conf_modal.html',
                    controller: 'ModalPayConfInstanceCtrl',
                    size: size,
                    backdrop: 'static',
                    resolve: {
                        items: function () {
                            $scope.items = [type];
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
            //系统类型
            $scope.accessStatusList=[];
            $scope.getAccessStatus=function () {
                $http.get('/common/list_code?codeSortKey=pay_access_status').success(function(data){
                    if (data.code=="10000") {
                        $scope.accessStatusList=data.data;

                    }
                })
            }
            $scope.getAccessStatus();

            $scope.$watch('$scope.lisPayConf.status', function (newValue, oldValue) {
                $scope.loadPayConfs();

            });
            $scope.searchByName = function () {
                $scope.loadPayConfs();
            }
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
                            PayAccessConfService.delete(item.id).then(function (data) {
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
//审批
            $scope.approval = function () {
                if ($scope.choosePayConfs.length == 1) {
                    var approvalPO = angular.copy($scope.choosePayConfs[0]);
                    var modalInstance = $modal.open({
                        templateUrl: 'tpl/payaccessmgmt/access_approval.html',
                        controller: 'ModalPayConfInstanceCtrl',
                        size: '',
                        backdrop: 'static',
                        resolve: {
                            items: function () {
                                approvalPO.applyId = approvalPO.id;
                                approvalPO.applyInfoId = approvalPO.accessId;
                                return ['approval', approvalPO];
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
            //查看公钥
            $scope.getPublicKey = function (size, type) {
                if ($scope.choosePayConfs.length == 1) {
                    var chooseCreate = angular.copy($scope.choosePayConfs[0]);

                    var modalInstance = $modal.open({
                        templateUrl: 'tpl/payaccessmgmt/access_public_key.html',
                        controller: 'ModalPayConfInstanceCtrl',
                        size: size,
                        backdrop: 'static',
                        resolve: {
                            items: function () {
                                return ['getPublicKey', chooseCreate];
                            }
                        }
                    });

                    modalInstance.result.then(function (items) {
                        if (items[0]) {
                            $scope.loadPayConfs();
                        }
                    }, function () {

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

            };
        }])
;

