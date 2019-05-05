'use strict';

/* Controllers */
// hospital_people_modal controller

app.controller('ModalCodeSortInstanceCtrl', ['$scope', '$modalInstance', '$http', 'items', 'BcCodeSortService',
    function ($scope, $modalInstance, $http, items, BcCodeSortService) {

        $scope.toSelectOrg=false;
        $scope.$watch('orgNo', function (newVal, oldVal) {
            if ($scope.orgNo != null && $scope.codesort.isPublic == 0) {
                if($scope.toSelectOrg){
                    $scope.codesort.orgName = $scope.orgName;
                    $scope.codesort.orgNo = $scope.orgNo;
                }
                $scope.toSelectOrg=false;
            }
        })

        $scope.toSelectOrg=function(){
            $scope.toSelectOrg=true;
        }

        $scope.codesort = items[1];
        $scope.flag = items[0] == "add";
        if ($scope.flag) {
            $scope.title = '新增数据字典';
        } else {
            $scope.title = '编辑数据字典';
        }


        $scope.addCodeSort = function () {
            $scope.codesort.hasChild = "1";
            BcCodeSortService.createCodeSort($scope.codesort).then(function (data) {
                if (data.code == "10000") {
                    toastr.success('添加成功！');
                    $("#toast-container").css("left", "46%");
                    $modalInstance.close([true]);

                } else {
                    toastr.error('添加失败！');
                    $("#toast-container").css("left", "46%");
                    $modalInstance.dismiss('cancel');
                }
            })
        }

        $scope.updateCodeSort = function () {
            BcCodeSortService.updateCodeSort($scope.codesort).then(function (data) {

                console.log(toastr)
                if (data.code == "10000") {
                    toastr.success('更新数据字典成功！');
                    $("#toast-container").css("left", "46%");
                    $modalInstance.close([true]);
                } else {
                    toastr.error('更新数据字典失败！');
                    $("#toast-container").css("left", "46%");
                    $modalInstance.dismiss('cancel');
                }
            })
        }

        //表单提交
        $scope.submitForm = function (isValid) {
            console.log("isValid:" + isValid);
            $scope.submitted = false;
            if (isValid) {
                if( $scope.codesort.isPublic==0){
                    $scope.codesort.orgId=$scope.orgNo;
                }

                if ($scope.flag) {
                    $scope.addCodeSort();
                } else {
                    $scope.submitted = false;
                    $scope.updateCodeSort();
                }
            } else {
                $scope.submitted = true;
            }

        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

    }]);

/**
 * 加载和编辑字典明细
 */
app.controller('ModalCodeInstanceCtrl', ['$scope', '$modalInstance', '$http', 'items', 'BcCodeSortDetailService', '$rootScope',
    function ($scope, $modalInstance, $http, items, BcCodeSortDetailService, $rootScope) {

        $scope.codedetail = items[1];
        $scope.flag = items[0] == "add";
        if ($scope.flag) {
            $scope.title = '新增';
        } else {
            $scope.title = '编辑';
        }


        $scope.addCode = function () {
            $scope.codedetail.pCodeSortId = $rootScope.codeSortId;
            $scope.codedetail.hasChild = "0";
            BcCodeSortDetailService.createCode($scope.codedetail).then(function (data) {
                if (data.code == "10000") {
                    toastr.success('添加成功！');
                    $("#toast-container").css("left", "46%");
                    $modalInstance.close([true]);

                } else {
                    toastr.error('添加失败！');
                    $("#toast-container").css("left", "46%");
                    $modalInstance.dismiss('cancel');
                }
            })
        }

        $scope.updateCode = function () {
            BcCodeSortDetailService.updateCode($scope.codedetail).then(function (data) {
                if (data.code == "10000") {
                    toastr.success('更新成功！');
                    $("#toast-container").css("left", "46%");
                    $modalInstance.close([true]);
                } else {
                    toastr.error('更新失败！');
                    $("#toast-container").css("left", "46%");
                    $modalInstance.dismiss('cancel');
                }
            })
        }

        //表单提交
        $scope.submitForm = function (isValid) {
            console.log("isValid:" + isValid);
            $scope.submitted = false;
            if (isValid) {
                if ($scope.flag) {
                    $scope.addCode();
                } else {
                    $scope.submitted = false;
                    $scope.updateCode();
                }
            } else {
                $scope.submitted = true;
            }

        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

    }]);
