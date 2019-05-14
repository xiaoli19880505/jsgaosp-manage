'use strict';

/* Controllers */
// hospital_people_modal controller

app.controller('ModalPayConfInstanceCtrl', ['$scope', '$modalInstance', '$http', 'items', 'PayAccessConfService', function ($scope, $modalInstance, $http, items, PayAccessConfService) {


    $scope.modal_with = "ul_lw";

    $scope.accessConf = items[1];
    $scope.flag = items[0] == "add";
    if ($scope.flag) {
        $scope.title = '新增';
    } else {
        $scope.title = '审批';
    }


    $scope.addPayConf = function () {
        PayAccessConfService.createAccessConf($scope.accessConf).then(function (data) {
            if (data.result) {
                toastr.success('添加成功！');
                $modalInstance.close([true]);
            } else {
                toastr.error('添加失败！');
                $modalInstance.dismiss('cancel');
            }
        })
    }

    $scope.approval = function () {
        PayAccessConfService.approval($scope.accessConf).then(function (data) {
            if (data.result) {
                toastr.success('审批成功！');
                $modalInstance.close([true]);
            } else {
                toastr.error('审批失败！');
                $modalInstance.dismiss('cancel');
            }
        })
    }
    $scope.getPublicKey = function () {

        PayAccessConfService.getPublicKey($scope.accessConf).then(function (data) {
            if (data.result) {
                $modalInstance.close([true]);
            } else {
                $modalInstance.dismiss('cancel');
            }
        })
    }
    $scope.submitForm = function (isValid) {
        $scope.submitted = false;
        if (isValid) {
            if ($scope.flag) {
                $scope.addPayConf();
            } else {
                // $scope.submitted = false;
                $scope.approval();
            }
        } else {
            $scope.submitted = true;
        }

    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
    $scope.is_focus = false;
}])
;
