'use strict';

/* Controllers */
// hospital_people_modal controller

app.controller('ModalOrgInstanceCtrl', ['$scope', '$modalInstance', '$http', '$sessionStorage', 'items', 'OrgService', 'commonServ','$timeout',
    function ($scope, $modalInstance, $http, $sessionStorage, items, OrgService, commonServ,$timeout) {

        $scope.orgItem = items[1];
        $scope.operateType = items[0];
        $scope.parentId=$scope.orgItem.orgNo;
        $scope.isUpdate=false;
        $scope.orgType=$scope.orgItem.orgType;
        $scope.title = '新增';


        if ($scope.operateType == "update_org") {
            $scope.title = '编辑公安组织';
            $scope.isUpdate=true;
        }else if ($scope.operateType == "update_department") {
            $scope.title = '编辑支队';
            $scope.orgItem.pOrgNo=$scope.orgItem.porgNo;
            $scope.isUpdate=true;
        }else{
            $scope.orgItem={};
            $scope.orgItem.pOrgNo=$scope.orgItem.porgNo;
        }

        $scope.$watch('orgItem.orgType', function (newVal, oldVal) {
            if ($scope.orgItem.orgType=='01' && $scope.operateType != "update_org") {
                $scope.title = '新增公安组织';
                $scope.operateType="add_org";
            }else if($scope.orgItem.orgType=='02' && $scope.operateType == "update_department"){
                $scope.title = '新增支队';
                $scope.operateType="add_department";
            }
        })


        $scope.updateOrg = function () {
            if ($scope.operateType == "update_org") {
                OrgService.updateOrg($scope.orgItem).then(function (data) {
                    if (data.code == "10000") {
                        toastr.success('更新组织成功！');
                        $modalInstance.close([true]);
                        // $timeout(function () {
                        //     tree.expand_all();
                        //     tree.select_firstChild_branch();
                        // }, 0);


                    } else {
                        toastr.error('更新组织失败！');
                        $modalInstance.close([true]);

                    }
                })
            } else if ($scope.operateType == "add_org") {
                $scope.orgItem.pOrgNo = $scope.parentId;
                OrgService.addOrg($scope.orgItem).then(function (data) {
                    if (data.code == "10000") {
                        $scope.form_is_show = false;
                        $scope.tmp_flag = true;
                        toastr.success('新建组织成功！');
                        $modalInstance.close([true]);
                    } else if (data.code=="1002") {
                        toastr.error(data.msg);
                        $modalInstance.close([true]);

                    }else{
                        toastr.error('新建组织失败！');
                        $modalInstance.close([true]);
                    }
                })
            } else if ($scope.operateType =="update_department") {
                OrgService.updateOrg($scope.orgItem).then(function (data) {
                    if (data.code == "10000") {
                        $scope.form_is_show = false;
                        $scope.tmp_flag = true;
                        toastr.success('编辑部门成功！');
                        $modalInstance.close([true]);
                    } else {
                        toastr.error('编辑部门失败！');
                        $modalInstance.close([true]);

                    }
                })
            } else if ($scope.operateType == "add_department") {
                $scope.orgItem.pOrgNo = $scope.parentId;
                OrgService.addDepatemnt($scope.orgItem).then(function (data) {
                    if (data.code == "10000") {
                        toastr.success('新建部门成功！');
                        $modalInstance.close([true]);
                    } else {
                        toastr.error('新建部门失败！');
                        $modalInstance.close([true]);
                    }
                })
            }
        }

        $scope.submitForm = function (isValid) {
            $scope.submitted = false;
            if (isValid) {
                $scope.updateOrg();
            } else {
                $scope.submitted = true;
            }
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };


    }])
;
