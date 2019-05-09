'use strict';

/* Controllers */
// hospital_people_modal controller

app.controller('ModalRoleInfoInstanceCtrl', function ($scope, $modalInstance, items, BcRoleService) {


    $scope.role=items[1];
    $scope.flag=items[0]=="add";
    if ($scope.flag) {
        $scope.role=new Object();
        $scope.title = '新增角色';
        $scope.role.orgId=items[2];
    } else {
        $scope.title = '编辑角色';
    }


    $scope.addRole=function(){
        BcRoleService.save($scope.role).then(function(data){
            if(data.code=="10000"){
                toastr.success('添加角色成功！');
                $modalInstance.close([true,items[0]]);
            }else{
                toastr.error('添加角色失败！');
                $modalInstance.dismiss('cancel');
            }
        })
    }

    $scope.updateRole = function () {
        BcRoleService.update($scope.role).then(function (data) {

            console.log(toastr)
            if (data.code == "10000") {
                toastr.success('更新角色成功！');
                $("#toast-container").css("left", "46%");
                $modalInstance.close([true,items[0]]);
            } else {
                toastr.error('更新角色失败！');
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
                $scope.addRole();
            } else {
                $scope.submitted = false;
                $scope.updateRole();
            }
        } else {
            $scope.submitted = true;
        }

    };


    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});