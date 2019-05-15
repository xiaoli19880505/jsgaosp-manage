/**
 * 标准指标树
 * <st-targettree></st-targettree>
 */
angular
    .module('app')
    .directive(
        'stTargettree',
        [
            'GG',
            'res',
            '$modal', '$timeout','$sessionStorage','BcRoleService',
            function (GG, res, $modal, $timeout,$sessionStorage,BcRoleService) {
                /** ******modal ********* */

                function createTree(data) {

                    $('#stTarTree').tree({
                        data: data,
                        animate: true,
                        checkbox: true,
                        cascadeCheck: true,
                        id: 'id',
                        formatter: function (node) {
                            console.log(node)
                            return node.name;
                        }
                    });
                }



                var modal = {
                    open: function (size) {
                        var modalInstance = $modal
                            .open({
                                templateUrl: 'tpl/sttarget_tree_modal.html',
                                controller: function ($scope,
                                                      $modalInstance) {
                                    var tarRes = res(GG.BASE
                                        + '/system/role/getRoleOpList');
                                    $scope.stTargetData = [];
                                    $scope.stTarIds = [];
                                    var tree = $scope.my_tree = {};
                                    $scope.loadList = function () {
                                        tarRes.query({
                                            roleId:$sessionStorage.chooseRole.roleId
                                        }).$promise
                                            .then(function (data) {
                                                var tree =angular.copy(GG.allPerms);
                                                angular.forEach(data, function (item) {
                                                    angular.forEach(tree, function (item1) {
                                                        angular.forEach(item1.children, function (item2) {
                                                            if(item2.id==item.perm){
                                                                angular.forEach(item2.children, function (item3) {
                                                                    if(item.opCode==item3.code){
                                                                        item3.checked=true;
                                                                    }
                                                                });
                                                            }

                                                        });
                                                    });
                                                });
                                                createTree(tree);
                                            })

                                        // $timeout(function () {
                                        //     createTree(GG.allPerms)
                                        // });

                                    }
                                    loadList= $scope.loadList();
                                    $scope.loadList();


                                    $scope.ok = function () {
                                        var nodes = $('#stTarTree').tree('getChecked');
                                        var s = [];
                                        var codes = [];
                                        var len = nodes.length;
                                        for (var i = 0; i < len; i++) {
                                            s.push(nodes[i].id);
                                            codes.push(nodes[i].code);

                                        }

                                        BcRoleService.saveRoleOp(JSON.stringify(nodes),JSON.stringify($sessionStorage.chooseRole))
                                            .then(function () {

                                            })


                                        $modalInstance.close([
                                            true,
                                            s.join(","), codes.join(",")],nodes.join(","));
                                    };

                                    $scope.cancel = function () {
                                        $modalInstance
                                            .dismiss('cancel');
                                    };

                                },
                                size: size,
                                backdrop: 'static',
                            });

                        return modalInstance.result.then(function (
                            items) {
                            if (items[0]) {
                                return items;
                            }
                        }, function () {
                            return null;
                        });
                    }
                }
                /** ******modal end ********* */

                return {
                    restrict: 'EA',
                    scope: false,
                    replace: true,
                    template: '<button class="btn btn-sm btn-primary" >' +
                        '权限' +
                        '</button>',
                    link: function (scope, el, attr) {

                        el.on('click', function () {
                            modal.open('sm').then(function (data) {
                                if (data) {
                                    scope.importIds = data[1];
                                    scope.importCodes = data[2];

                                }
                                // loadList();

                            });
                        });
                    }
                }
            }]);