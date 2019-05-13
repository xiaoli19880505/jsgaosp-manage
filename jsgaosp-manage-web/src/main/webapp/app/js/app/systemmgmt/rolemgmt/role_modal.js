'use strict';

/* Controllers */
// hospital_people_modal controller

app.controller('ModalSystemUserViewInstanceCtrl', function ($scope, $modalInstance, items, SystemRoleUserService) {

    $scope.totalSelectedIds = [];
    $scope.items = [];

    $scope.roleId = items[0];
    $scope.totalItems = 0;
    $scope.currentPage = 1;
    $scope.maxSize = 5;

    $scope.title = '添加人员';

    $scope.pageChanged = function () {
        $scope.loadList();
    };

    $scope.checkCurrentPageUserIsSelected = function () {
        $scope.selectAll = false;
        var len = $scope.items.size;
        var count = 0;
        angular.forEach($scope.items, function (item) {
            if ($scope.totalSelectedIds.indexOf(item.userId) > -1) {
                item.selected = true;
                count++;
            }
        });
        if (count = len) {
            $scope.selectAll = true;
        } else {
            $scope.selectAll = false;
        }
    }


    $scope.checkAll = function () {
        angular.forEach($scope.items, function (item) {
            item.selected = $scope.selectedAll;
            var index = $scope.totalSelectedIds.indexOf(item.userId);
            if ($scope.selectedAll) {
                if (index == -1) {
                    $scope.totalSelectedIds.push(item.userId);
                }
            } else {
                if (index > -1) {
                    $scope.totalSelectedIds.splice(index, 1);
                }
            }
        });
    };

    $scope.check = function (id) {
        var index = $scope.totalSelectedIds.indexOf(id);
        if (index > -1) {
            $scope.totalSelectedIds.splice(index, 1);
        } else {
            $scope.totalSelectedIds.push(id);
        }
    };

    $scope.loadList = function () {
        // $scope.getIds();
        SystemRoleUserService.getrUserListNotInThisRole($scope.roleId, "", $scope.currentPage)
            .then(function (data) {
                $scope.totalItems = data.totalCount;
                $scope.items = data.list;
                $scope.checkCurrentPageUserIsSelected();
            });

    };

    $scope.loadList();

    $scope.add = function () {

        console.log($scope.totalSelectedIds);
        SystemRoleUserService.save($scope.roleId, $scope.totalSelectedIds.join(','))
            .then(function () {
                toastr.success('添加用户成功');
                $modalInstance.close([true, items[0]]);
            });


    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});