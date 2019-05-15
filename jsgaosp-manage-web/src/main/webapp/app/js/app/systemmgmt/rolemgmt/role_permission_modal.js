'use strict';

/* Controllers */
// hospital_people_modal controller

app.controller('ModalRolePermissionInstanceCtrl', function ($scope, $modalInstance, items, SystemRoleUserService) {

    $scope.totalIds = [];

    $scope.totalItems = 0;
    $scope.currentPage = 1;
    $scope.maxSize = 5;
    $scope.pageChanged = function () {
        $scope.loadList();
    };

    $scope.isUser = items[0] == "user";

    $scope.checkAll = function () {
        angular.forEach($scope.items, function (item) {
            item.selected = $scope.selectedAll;
        });
    };

    $scope.check = function (item) {
        if (!item.selected) {
            $scope.selectedAll = false;
        }
    };

    if ($scope.isUser) {
        $scope.title = '添加人员';
    } else {
        $scope.title = '关联机构';
    }

    $scope.getIds = function () {
        var str = $scope.totalIds.join(",");
        angular.forEach($scope.items, function (item) {
            if (item.selected) {
                if ($scope.isUser) {
                    if (str.indexOf(item.userId) < 0)
                        $scope.totalIds.push(item.userId);
                } else {
                }
            }
        });
    };

    $scope.setIds = function () {
        var str = $scope.totalIds.join(",");
        angular.forEach($scope.items, function (item) {
            if ($scope.isUser) {
                if (str.indexOf(item.userId) >= 0)
                    item.selected = true;
            } else {
            }
        });
    };

    $scope.loadList = function () {
        $scope.getIds();
        if ($scope.isUser) {
            SystemRoleUserService.get(items[2],items[1], $scope.query, "0", $scope.currentPage, 10)
                .then(function (data) {
                    $scope.totalItems = data.totalItems;
                    $scope.items = data.content;
                    $scope.setIds();
                });
        } else {
        }
    };
    $scope.loadList();

    $scope.add = function () {
        $scope.getIds();
        if ($scope.isUser) {
            SystemRoleUserService.save(items[1], $scope.totalIds.join(","))
                .then(function () {
                    toastr.success('添加用户成功');
                    $modalInstance.close([true, items[0]]);
                });
        } else {
        }
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});